package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;
import com.example.RestaurantDailyMenu.model.UserCredentials;
import com.example.RestaurantDailyMenu.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AuthController is responsible for handling authentication and registration related requests.
 * It includes endpoints for user and restaurant registration and login.
 * This controller validates username availability between users and restaurants,
 * and delegates the actual authentication logic to the AuthService.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    // Properties
    private final AuthService authService;

    // Constructors

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Methods

    /**
     * Endpoint for user registration.
     * Firstly, checks if the provided username already exists as a restaurant or user.
     * If not, registers a new user using the provided user details.
     *
     * @param user The user object containing registration details.
     * @return A response indicating success or failure of the registration.
     */
    @PostMapping("/register/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if the username exists as a restaurant first
        if (authService.findRestaurantByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username exists as a restaurant, please log in using the restaurant credentials.");
        }

        // If username does not exist as a restaurant, try to register it as a user
        String result = authService.registerUser(user);
        if (result.equals("User already exists.")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint for restaurant registration.
     * Firstly, checks if the provided username already exists as a user or restaurant.
     * If not, registers a new restaurant using the provided restaurant details.
     *
     * @param restaurant The restaurant object containing registration details.
     * @return A response indicating success or failure of the registration.
     */
    @PostMapping("/register/restaurant")
    public ResponseEntity<String> registerRestaurant(@RequestBody Restaurant restaurant) {
        // Check if the username exists as a user first
        if (authService.findUserByUsername(restaurant.getUsername()) != null) {
            return ResponseEntity.badRequest().body("This username is taken as user. Choose another username.");
        }

        // If username does not exist as a user, try to register it as a restaurant
        String result = authService.registerRestaurant(restaurant);
        if (result.equals("Restaurant already exists.")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    /**
     * Endpoint for user login.
     * Checks if the provided username exists as a restaurant to prevent incorrect login attempts.
     * Authenticates the user with the provided credentials.
     *
     * @param credentials The user credentials containing username and password.
     * @return A response indicating success or failure of the login.
     */
    @PostMapping("/login/user")
    public ResponseEntity<String> loginUser(@RequestBody UserCredentials credentials) {
        // Check if the username exists as a restaurant first
        if (authService.findRestaurantByUsername(credentials.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username exists as a restaurant, please log in using the restaurant credentials.");
        }

        // If username does not exist as a restaurant, try to log in as a user
        boolean success = authService.loginUser(credentials.getUsername(), credentials.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.badRequest().body("Invalid username or password.");
    }

    /**
     * Endpoint for restaurant login.
     * Checks if the provided username exists as a user to prevent incorrect login attempts.
     * Authenticates the restaurant with the provided credentials.
     *
     * @param credentials The user credentials containing username and password.
     * @return A response indicating success or failure of the login.
     */
    @PostMapping("/login/restaurant")
    public ResponseEntity<String> loginRestaurant(@RequestBody UserCredentials credentials) {
        // Check if the username exists as a user first
        if (authService.findUserByUsername(credentials.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username exists as a user, please log in using the user credentials.");
        }

        // If username does not exist as a user, try to log in as a restaurant
        boolean success = authService.loginRestaurant(credentials.getUsername(), credentials.getPassword());
        if (success) {
            return ResponseEntity.ok("Login successful.");
        }
        return ResponseEntity.badRequest().body("Invalid username or password.");
    }
}
