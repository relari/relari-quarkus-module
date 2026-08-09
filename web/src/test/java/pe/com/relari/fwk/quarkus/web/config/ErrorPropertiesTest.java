//package pe.com.relari.config;
//
//import io.quarkus.test.InjectMock;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ErrorPropertiesTest {
//
////    @InjectMock
//    private static ErrorProperties errorProperties;
//
//    @BeforeAll
//    static void init() {
//        errorProperties = new ErrorProperties() {
//            @Override
//            public String defaultMessage() {
//                return "Default error message";
//            }
//
//            @Override
//            public Integer status() {
//                return 500;
//            }
//        };
//    }
//
//    @Test
//    void testDefaultMessage() {
//        assertEquals("Default error message", errorProperties.defaultMessage());
//    }
//}