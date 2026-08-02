//package pe.com.relari;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import pe.com.relari.dao.ws.model.UserResponse;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@DisplayName("TestJsonConverter Tests")
//class TestJsonConverterTest {
//
//    @Test
//    @DisplayName("Should read single user from JSON file successfully")
//    void shouldReadSingleUserFromJsonFileSuccessfully() {
//        // Act
//        UserResponse user = TestJsonConverter.readDataFromFileJson("user_1.json", UserResponse.class);
//
//        // Assert
//        assertNotNull(user);
//        assertEquals(1, user.getId());
//        assertEquals("Leanne Graham", user.getName());
//        assertEquals("Bret", user.getUsername());
//        assertEquals("Sincere@april.biz", user.getEmail());
//        assertNotNull(user.getAddress());
//        assertNotNull(user.getCompany());
//    }
//
//    @Test
//    @DisplayName("Should read list of users from JSON file successfully")
//    void shouldReadListOfUsersFromJsonFileSuccessfully() {
//        // Act
//        List<UserResponse> users = TestJsonConverter.readDataFromFileJson("users.json",
//                new com.fasterxml.jackson.core.type.TypeReference<List<UserResponse>>() {});
//
//        // Assert
//        assertNotNull(users);
//        assertFalse(users.isEmpty());
//        assertEquals(10, users.size());
//
//        // Verify first user
//        UserResponse firstUser = users.get(0);
//        assertEquals(1, firstUser.getId());
//        assertEquals("Leanne Graham", firstUser.getName());
//
//        // Verify last user
//        UserResponse lastUser = users.get(users.size() - 1);
//        assertEquals(10, lastUser.getId());
//        assertEquals("Clementina DuBuque", lastUser.getName());
//    }
//
//    @Test
//    @DisplayName("Should throw IllegalArgumentException when file does not exist")
//    void shouldThrowIllegalArgumentExceptionWhenFileDoesNotExist() {
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
//                () -> TestJsonConverter.readDataFromFileJson("nonexistent.json", UserResponse.class));
//        assertTrue(exception.getMessage().contains("Test data file not found: nonexistent.json"));
//    }
//
//    @Test
//    @DisplayName("Should return null safely when file does not exist")
//    void shouldReturnNullSafelyWhenFileDoesNotExist() {
//        // Act
//        UserResponse user = TestJsonConverter.readDataFromFileJsonSafely("nonexistent.json", UserResponse.class);
//
//        // Assert
//        assertNull(user);
//    }
//
//    @Test
//    @DisplayName("Should check if test data file exists")
//    void shouldCheckIfTestDataFileExists() {
//        // Act & Assert
//        assertTrue(TestJsonConverter.testDataFileExists("user_1.json"));
//        assertTrue(TestJsonConverter.testDataFileExists("users.json"));
//        assertFalse(TestJsonConverter.testDataFileExists("nonexistent.json"));
//    }
//
//    @Test
//    @DisplayName("Should return ObjectMapper instance")
//    void shouldReturnObjectMapperInstance() {
//        // Act
//        var mapper = TestJsonConverter.getObjectMapper();
//
//        // Assert
//        assertNotNull(mapper);
//        assertTrue(mapper instanceof com.fasterxml.jackson.databind.ObjectMapper);
//    }
//
//    @Test
//    @DisplayName("Should handle malformed JSON gracefully")
//    void shouldHandleMalformedJsonGracefully() {
//        // This test would require a malformed JSON file, but demonstrates the pattern
//        // In a real scenario, you would create a malformed JSON file in test resources
//
//        // Act & Assert - This would throw RuntimeException for malformed JSON
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> TestJsonConverter.readDataFromFileJson("malformed.json", UserResponse.class));
//        assertTrue(exception.getMessage().contains("Error reading JSON file: malformed.json"));
//    }
//}
