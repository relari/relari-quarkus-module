package pe.com.relari;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Utility class for reading JSON test data from files.
 * Provides robust error handling and logging for test data loading.
 */
public class TestJsonConverter {

    private static final Logger logger = LoggerFactory.getLogger(TestJsonConverter.class);
    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
    }

    private TestJsonConverter() {
        // Utility class - prevent instantiation
    }

    /**
     * Reads JSON data from a file and converts it to the specified type.
     *
     * @param fileName the name of the JSON file in the test resources
     * @param className the target class type
     * @param <T> the type to convert to
     * @return the converted object, or null if an error occurs
     * @throws IllegalArgumentException if the file is not found
     * @throws RuntimeException if there's an error parsing the JSON
     */
    public static <T> T readDataFromFileJson(String fileName, Class<T> className) {
        try (InputStream inputStream = TestJsonConverter.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            
            if (inputStream == null) {
                String errorMsg = "Test data file not found: " + fileName;
                logger.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
            
            T result = mapper.readValue(inputStream, className);
            logger.debug("Successfully loaded test data from file: {}", fileName);
            return result;
            
        } catch (IOException e) {
            String errorMsg = "Error reading JSON file: " + fileName;
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * Reads JSON data from a file and converts it to a TypeReference.
     * Useful for complex types like List<SomeClass> or Map<String, SomeClass>.
     *
     * @param fileName the name of the JSON file in the test resources
     * @param typeReference the TypeReference for the target type
     * @param <T> the type to convert to
     * @return the converted object, or null if an error occurs
     * @throws IllegalArgumentException if the file is not found
     * @throws RuntimeException if there's an error parsing the JSON
     */
    public static <T> T readDataFromFileJson(String fileName, TypeReference<T> typeReference) {
        try (InputStream inputStream = TestJsonConverter.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            
            if (inputStream == null) {
                String errorMsg = "Test data file not found: " + fileName;
                logger.error(errorMsg);
                throw new IllegalArgumentException(errorMsg);
            }
            
            T result = mapper.readValue(inputStream, typeReference);
            logger.debug("Successfully loaded test data from file: {} with TypeReference", fileName);
            return result;
            
        } catch (IOException e) {
            String errorMsg = "Error reading JSON file: " + fileName;
            logger.error(errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    /**
     * Safely reads JSON data from a file, returning null if an error occurs.
     * This method does not throw exceptions and is useful when the test can handle null values.
     *
     * @param fileName the name of the JSON file in the test resources
     * @param className the target class type
     * @param <T> the type to convert to
     * @return the converted object, or null if an error occurs
     */
    public static <T> T readDataFromFileJsonSafely(String fileName, Class<T> className) {
        try {
            return readDataFromFileJson(fileName, className);
        } catch (Exception e) {
            logger.warn("Failed to load test data from file: {} - returning null", fileName, e);
            return null;
        }
    }

    /**
     * Checks if a test data file exists in the classpath.
     *
     * @param fileName the name of the file to check
     * @return true if the file exists, false otherwise
     */
    public static boolean testDataFileExists(String fileName) {
        try (InputStream inputStream = TestJsonConverter.class.getClassLoader()
                .getResourceAsStream(fileName)) {
            return inputStream != null;
        } catch (IOException e) {
            logger.debug("Error checking if test data file exists: {}", fileName, e);
            return false;
        }
    }

    /**
     * Gets the ObjectMapper instance for custom JSON operations.
     *
     * @return the configured ObjectMapper instance
     */
    public static ObjectMapper getObjectMapper() {
        return mapper;
    }
}
