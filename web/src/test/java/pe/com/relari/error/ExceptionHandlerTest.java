//package pe.com.relari.handler;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.EnumSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import pe.com.relari.config.ErrorProperties;
//import pe.com.relari.model.common.ApiResponse;
//import pe.com.relari.model.common.ErrorResponse;
//import pe.com.relari.model.common.ErrorType;
//import pe.com.relari.model.common.StatusType;
//
//import jakarta.ws.rs.core.Response;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@DisplayName("ExceptionHandler Tests")
//class ExceptionHandlerTest {
//
//    @Mock
//    private ErrorProperties errorProperties;
//
//    @InjectMocks
//    private ExceptionHandler exceptionHandler;
//
//    @BeforeEach
//    void setUp() {
//        // MockitoExtension handles initialization automatically
//    }
//
//    @Test
//    @DisplayName("Should return error response with correct status and message for internal server error")
//    void shouldReturnErrorResponseWithCorrectStatusAndMessageForInternalServerError() {
//        // Arrange
//        String errorMessage = "Internal server error occurred";
//        ApiException exception = new ApiException(ErrorType.INTERNAL_SERVER_ERROR, errorMessage);
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(500, response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertEquals(errorMessage, errorResponse.errorMessage());
//
//        verifyNoInteractions(errorProperties);
//    }
//
//    @Test
//    @DisplayName("Should return error response with correct status and message for not found error")
//    void shouldReturnErrorResponseWithCorrectStatusAndMessageForNotFoundError() {
//        // Arrange
//        String errorMessage = "Resource not found";
//        ApiException exception = new ApiException(ErrorType.NOT_FOUND, errorMessage);
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(404, response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertEquals(errorMessage, errorResponse.errorMessage());
//    }
//
//    @Test
//    @DisplayName("Should return error response with correct status and message for bad request error")
//    void shouldReturnErrorResponseWithCorrectStatusAndMessageForBadRequestError() {
//        // Arrange
//        String errorMessage = "Invalid request parameters";
//        ApiException exception = new ApiException(ErrorType.BAD_REQUEST, errorMessage);
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(400, response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertEquals(errorMessage, errorResponse.errorMessage());
//    }
//
//    @ParameterizedTest
//    @EnumSource(ErrorType.class)
//    @DisplayName("Should return appropriate status code for all error types")
//    void shouldReturnAppropriateStatusCodeForAllErrorTypes(ErrorType errorType) {
//        // Arrange
//        String errorMessage = "Test error message for " + errorType;
//        ApiException exception = new ApiException(errorType, errorMessage);
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertEquals(errorMessage, errorResponse.errorMessage());
//
//        // Verify status code matches expected value
//        int expectedStatusCode = getExpectedStatusCode(errorType);
//        assertEquals(expectedStatusCode, response.getStatus());
//    }
//
//    @Test
//    @DisplayName("Should return error response with null message when exception message is null")
//    void shouldReturnErrorResponseWithNullMessageWhenExceptionMessageIsNull() {
//        // Arrange
//        ApiException exception = new ApiException(ErrorType.INTERNAL_SERVER_ERROR, null);
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(500, response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertNull(errorResponse.errorMessage());
//    }
//
//    @Test
//    @DisplayName("Should return error response with empty message when exception message is empty")
//    void shouldReturnErrorResponseWithEmptyMessageWhenExceptionMessageIsEmpty() {
//        // Arrange
//        ApiException exception = new ApiException(ErrorType.BAD_REQUEST, "");
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(400, response.getStatus());
//
//        ApiResponse<?> apiResponse = (ApiResponse<?>) response.getEntity();
//        assertNotNull(apiResponse);
//        assertEquals(StatusType.ERROR, apiResponse.getStatus());
//
//        ErrorResponse errorResponse = (ErrorResponse) apiResponse.getData();
//        assertNotNull(errorResponse);
//        assertEquals("", errorResponse.errorMessage());
//    }
//
//    @Test
//    @DisplayName("Should return error response with correct content type")
//    void shouldReturnErrorResponseWithCorrectContentType() {
//        // Arrange
//        ApiException exception = new ApiException(ErrorType.INTERNAL_SERVER_ERROR, "Test error");
//
//        // Act
//        Response response = exceptionHandler.toResponse(exception);
//
//        // Assert
//        assertNotNull(response);
//        assertNotNull(response.getMediaType());
//        assertTrue(response.getMediaType().toString().contains("application/json"));
//    }
//
//    /**
//     * Helper method to get expected status code for each error type
//     */
//    private int getExpectedStatusCode(ErrorType errorType) {
//        return switch (errorType) {
//            case BAD_REQUEST -> 400;
//            case UNAUTHORIZED -> 401;
//            case FORBIDDEN -> 403;
//            case NOT_FOUND -> 404;
//            case METHOD_NOT_ALLOWED -> 405;
//            case CONFLICT -> 409;
//            case INTERNAL_SERVER_ERROR -> 500;
//            case BAD_GATEWAY -> 502;
//            case SERVICE_UNAVAILABLE -> 503;
//            default -> 500;
//        };
//    }
//}