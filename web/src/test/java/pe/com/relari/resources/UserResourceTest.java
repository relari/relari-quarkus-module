package pe.com.relari.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.com.relari.TestJsonConverter;
import pe.com.relari.dao.ws.JsonPlaceHolderApi;
import pe.com.relari.dao.ws.model.UserResponse;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserResource Tests")
class UserResourceTest {

    @Mock
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @InjectMocks
    private UserResource userResource;

    @BeforeEach
    void setUp() {
        // MockitoExtension handles initialization automatically
    }

    @Test
    @DisplayName("Should return user when found by ID")
    void shouldReturnUserWhenFoundById() {
        // Arrange
        UserResponse expectedUser = TestJsonConverter.readDataFromFileJson("user_1.json", UserResponse.class);
        assertNotNull(expectedUser);
        
        when(jsonPlaceHolderApi.getUser(1)).thenReturn(expectedUser);

        // Act
        UserResponse result = userResource.getUser(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Leanne Graham", result.getName());
        assertEquals("Bret", result.getUsername());
        assertEquals("Sincere@april.biz", result.getEmail());
        assertNotNull(result.getAddress());
        assertEquals("Kulas Light", result.getAddress().getStreet());
        assertNotNull(result.getCompany());
        assertEquals("Romaguera-Crona", result.getCompany().getName());
        
        verify(jsonPlaceHolderApi, times(1)).getUser(1);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("Should return user for valid IDs")
    void shouldReturnUserForValidIds(int userId) {
        // Arrange
        UserResponse user = createTestUser(userId, "Test User " + userId, "testuser" + userId, "test" + userId + "@example.com");
        when(jsonPlaceHolderApi.getUser(userId)).thenReturn(user);

        // Act
        UserResponse result = userResource.getUser(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Test User " + userId, result.getName());
        assertEquals("testuser" + userId, result.getUsername());
        assertEquals("test" + userId + "@example.com", result.getEmail());
        
        verify(jsonPlaceHolderApi, times(1)).getUser(userId);
    }

    @Test
    @DisplayName("Should return all users when service returns user list")
    void shouldReturnAllUsersWhenServiceReturnsUserList() {
        // Arrange
        List<UserResponse> expectedUsers = TestJsonConverter.readDataFromFileJson("users.json", 
                new com.fasterxml.jackson.core.type.TypeReference<List<UserResponse>>() {});
        assertNotNull(expectedUsers);
        assertFalse(expectedUsers.isEmpty());
        
        when(jsonPlaceHolderApi.users()).thenReturn(expectedUsers);

        // Act
        List<UserResponse> result = userResource.users();

        // Assert
        assertNotNull(result);
        assertEquals(expectedUsers.size(), result.size());
        assertEquals(expectedUsers, result);
        
        // Verify first user details
        UserResponse firstUser = result.get(0);
        assertEquals(1, firstUser.getId());
        assertEquals("Leanne Graham", firstUser.getName());
        
        verify(jsonPlaceHolderApi, times(1)).users();
    }

    @Test
    @DisplayName("Should return empty list when no users exist")
    void shouldReturnEmptyListWhenNoUsersExist() {
        // Arrange
        when(jsonPlaceHolderApi.users()).thenReturn(List.of());

        // Act
        List<UserResponse> result = userResource.users();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        
        verify(jsonPlaceHolderApi, times(1)).users();
    }

    @Test
    @DisplayName("Should handle external API errors gracefully")
    void shouldHandleExternalApiErrorsGracefully() {
        // Arrange
        when(jsonPlaceHolderApi.getUser(anyInt()))
                .thenThrow(new RuntimeException("External API error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> userResource.getUser(1));
        
        assertEquals("External API error", exception.getMessage());
        
        verify(jsonPlaceHolderApi, times(1)).getUser(1);
    }

    @Test
    @DisplayName("Should handle null response from external API")
    void shouldHandleNullResponseFromExternalApi() {
        // Arrange
        when(jsonPlaceHolderApi.getUser(1)).thenReturn(null);

        // Act
        UserResponse result = userResource.getUser(1);

        // Assert
        assertNull(result);
        
        verify(jsonPlaceHolderApi, times(1)).getUser(1);
    }

    /**
     * Factory method to create test UserResponse objects
     */
    private UserResponse createTestUser(Integer id, String name, String username, String email) {
        UserResponse user = new UserResponse();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPhone("123-456-7890");
        user.setWebsite("test.com");
        
        // Create address
        UserResponse.Address address = new UserResponse.Address();
        address.setStreet("Test Street");
        address.setSuite("Suite 100");
        address.setCity("Test City");
        address.setZipcode("12345");
        
        UserResponse.Geo geo = new UserResponse.Geo();
        geo.setLat(-37.3159);
        geo.setLng(81.1496);
        address.setGeo(geo);
        
        user.setAddress(address);
        
        // Create company
        UserResponse.Company company = new UserResponse.Company();
        company.setName("Test Company");
        company.setCatchPhrase("Test catchphrase");
        company.setBs("Test bs");
        
        user.setCompany(company);
        
        return user;
    }
}