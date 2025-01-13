package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;
import com.example.RestaurantDailyMenu.model.UserCredentials;
import com.example.RestaurantDailyMenu.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for AuthController.
 * This class tests various authentication and registration scenarios.
 */
class AuthControllerTest {
    // Properties
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    // Test methods
    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests registering a user when the username already exists as a restaurant.
     * Expects a bad request response.
     */
    @Test
    void registerUser_whenUsernameExistsAsRestaurant_returnBadRequest() {
        User user = new User("user1", "password", "name");
        when(authService.findRestaurantByUsername(user.getUsername())).thenReturn(new Restaurant("restaurant1", "password", "name2"));

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username exists as a restaurant, please log in using the restaurant credentials.", response.getBody());
    }

    /**
     * Tests registering a user when the username does not exist.
     * Expects a successful registration response.
     */
    @Test
    void registerUser_whenUsernameDoesNotExist_registerUserSuccessfully() {
        User user = new User("user2", "password", "name");
        when(authService.findRestaurantByUsername(user.getUsername())).thenReturn(null);
        when(authService.registerUser(user)).thenReturn("User registered successfully.");

        ResponseEntity<String> response = authController.registerUser(user);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("User registered successfully.", response.getBody());
    }

    /**
     * Tests registering a restaurant when the username already exists as a user.
     * Expects a bad request response.
     */
    @Test
    void registerRestaurant_whenUsernameExistsAsUser_returnBadRequest() {
        Restaurant restaurant = new Restaurant("restaurant1", "password", "name");
        when(authService.findUserByUsername(restaurant.getUsername())).thenReturn(new User("user1", "password", "name2"));

        ResponseEntity<String> response = authController.registerRestaurant(restaurant);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("This username is taken as user. Choose another username.", response.getBody());
    }

    /**
     * Tests registering a restaurant when the username does not exist.
     * Expects a successful registration response.
     */
    @Test
    void registerRestaurant_whenUsernameDoesNotExist_registerRestaurantSuccessfully() {
        Restaurant restaurant = new Restaurant("restaurant2", "password", "name");
        when(authService.findUserByUsername(restaurant.getUsername())).thenReturn(null);
        when(authService.registerRestaurant(restaurant)).thenReturn("Restaurant registered successfully.");

        ResponseEntity<String> response = authController.registerRestaurant(restaurant);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Restaurant registered successfully.", response.getBody());
    }

    /**
     * Tests logging in a user when the username exists as a restaurant.
     * Expects a bad request response.
     */
    @Test
    void loginUser_whenUsernameExistsAsRestaurant_returnBadRequest() {
        UserCredentials credentials = new UserCredentials("restaurant1", "password");
        when(authService.findRestaurantByUsername(credentials.getUsername())).thenReturn(new Restaurant("restaurant1", "password", "name"));

        ResponseEntity<String> response = authController.loginUser(credentials);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username exists as a restaurant, please log in using the restaurant credentials.", response.getBody());
    }

    /**
     * Tests logging in a user with valid credentials.
     * Expects a successful login response.
     */
    @Test
    void loginUser_whenCredentialsAreValid_returnLoginSuccessful() {
        UserCredentials credentials = new UserCredentials("user1", "password");
        when(authService.findRestaurantByUsername(credentials.getUsername())).thenReturn(null);
        when(authService.loginUser(credentials.getUsername(), credentials.getPassword())).thenReturn(true);

        ResponseEntity<String> response = authController.loginUser(credentials);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Login successful.", response.getBody());
    }

    /**
     * Tests logging in a user with invalid credentials.
     * Expects a bad request response.
     */
    @Test
    void loginUser_whenCredentialsAreInvalid_returnBadRequest() {
        UserCredentials credentials = new UserCredentials("user1", "wrongpassword");
        when(authService.findRestaurantByUsername(credentials.getUsername())).thenReturn(null);
        when(authService.loginUser(credentials.getUsername(), credentials.getPassword())).thenReturn(false);

        ResponseEntity<String> response = authController.loginUser(credentials);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Invalid username or password.", response.getBody());
    }

    /**
     * Tests logging in a restaurant when the username exists as a user.
     * Expects a bad request response.
     */
    @Test
    void loginRestaurant_whenUsernameExistsAsUser_returnBadRequest() {
        UserCredentials credentials = new UserCredentials("user1", "password");
        when(authService.findUserByUsername(credentials.getUsername())).thenReturn(new User("user1", "password", "name"));

        ResponseEntity<String> response = authController.loginRestaurant(credentials);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Username exists as a user, please log in using the user credentials.", response.getBody());
    }

    /**
     * Tests logging in a restaurant with valid credentials.
     * Expects a successful login response.
     */
    @Test
    void loginRestaurant_whenCredentialsAreValid_returnLoginSuccessful() {
        UserCredentials credentials = new UserCredentials("restaurant1", "password");
        when(authService.findUserByUsername(credentials.getUsername())).thenReturn(null);
        when(authService.loginRestaurant(credentials.getUsername(), credentials.getPassword())).thenReturn(true);

        ResponseEntity<String> response = authController.loginRestaurant(credentials);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Login successful.", response.getBody());
    }

    /**
     * Tests logging in a restaurant with invalid credentials.
     * Expects a bad request response.
     */
    @Test
    void loginRestaurant_whenCredentialsAreInvalid_returnBadRequest() {
        UserCredentials credentials = new UserCredentials("restaurant1", "wrongpassword");
        when(authService.findUserByUsername(credentials.getUsername())).thenReturn(null);
        when(authService.loginRestaurant(credentials.getUsername(), credentials.getPassword())).thenReturn(false);

        ResponseEntity<String> response = authController.loginRestaurant(credentials);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Invalid username or password.", response.getBody());
    }
}
