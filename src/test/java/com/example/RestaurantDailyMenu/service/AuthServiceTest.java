package com.example.RestaurantDailyMenu.service;

import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;
import com.example.RestaurantDailyMenu.repository.GenericRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * AuthServiceTest is a unit test class for testing the AuthService class,
 * specifically the methods for registering and logging in users.
 * The tests use Mockito to mock the dependencies, such as the User repository,
 * to ensure that the methods behave correctly under various scenarios.
 */
class AuthServiceTest {

    // Properties
    private AuthService authService;
    private GenericRepository<User> userRepository;
    private GenericRepository<Restaurant> restaurantRepository;

    // Test methods
    /**
     * This method runs before each test to set up the necessary objects.
     * It mocks the GenericRepository instances to prevent actual database calls
     * and initializes the AuthService for testing.
     */
    @BeforeEach
    void setUp() {
        // Mocking the GenericRepository classes to isolate the tests from the database
        userRepository = mock(GenericRepository.class);
        restaurantRepository = mock(GenericRepository.class);

        // Creating the AuthService instance with the mocked repositories
        authService = new AuthService(userRepository, restaurantRepository);
    }

    /**
     * Test case to check the scenario where a new user is registered successfully.
     * It mocks the behavior of the userRepository to simulate the case where
     * the username does not exist, and the user is successfully saved.
     */
    @Test
    void testRegisterUser_Success() {
        User user = new User("mehmet_yılmaz", "password123", "Mehmet");  // Creating a user object

        // Mocking the behavior: Username does not exist in the repository
        when(userRepository.findByUsername("mehmet_yılmaz", User.class)).thenReturn(null);

        // Mocking the save method to simulate the user being saved to the repository
        when(userRepository.save(user)).thenReturn(user);

        // Calling the method we are testing
        String result = authService.registerUser(user);

        // Asserting that the correct success message is returned
        assertEquals("User registered successfully.", result);

        // Verifying that the save method was called exactly once
        verify(userRepository).save(user);
    }

    /**
     * Test case to check the scenario where the user already exists.
     * It mocks the behavior of the userRepository to simulate the case where
     * the username already exists, and the user is not saved again.
     */
    @Test
    void testRegisterUser_AlreadyExists() {
        User user = new User("mehmet_yılmaz", "password123", "Mehmet");  // Creating a user object

        // Mocking the behavior: Username already exists in the repository
        when(userRepository.findByUsername("mehmet_yılmaz", User.class)).thenReturn(user);

        // Calling the method we are testing
        String result = authService.registerUser(user);

        // Asserting that the correct error message is returned when the user already exists
        assertEquals("User already exists.", result);

        // Verifying that the save method was not called
        verify(userRepository, never()).save(user);
    }

    /**
     * Test case for a successful login attempt.
     * It mocks the behavior where the user exists and the correct password is provided.
     */
    @Test
    void testLoginUser_Success() {
        User user = new User("mehmet_yılmaz", "password123", "Mehmet");  // Creating a user object

        // Mocking the behavior: User with username "mehmet_yılmaz" exists in the repository
        when(userRepository.findByUsername("mehmet_yılmaz", User.class)).thenReturn(user);

        // Calling the method we are testing with correct username and password
        boolean result = authService.loginUser("mehmet_yılmaz", "password123");

        // Asserting that the login is successful
        assertTrue(result);
    }

    /**
     * Test case for a failed login attempt.
     * It mocks the behavior where the user does not exist in the repository.
     */
    @Test
    void testLoginUser_Failure() {
        // Mocking the behavior: User with username "mehmet_yılmaz" does not exist in the repository
        when(userRepository.findByUsername("mehmet_yılmaz", User.class)).thenReturn(null);

        // Calling the method we are testing with a non-existing username
        boolean result = authService.loginUser("mehmet_yılmaz", "password123");

        // Asserting that the login fails
        assertFalse(result);
    }
}
