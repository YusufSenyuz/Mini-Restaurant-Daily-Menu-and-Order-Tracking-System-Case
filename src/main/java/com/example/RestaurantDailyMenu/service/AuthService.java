package com.example.RestaurantDailyMenu.service;

import com.example.RestaurantDailyMenu.interfaces.AuthServiceInterface;
import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;
import com.example.RestaurantDailyMenu.repository.GenericRepository;
import org.springframework.stereotype.Service;

/**
 * AuthService class is responsible for authentication-related operations.
 * Handles user and restaurant registration, login, and retrieval by username.
 */
@Service
public class AuthService implements AuthServiceInterface {
    // Properties
    private final GenericRepository<User> userRepository;
    private final GenericRepository<Restaurant> restaurantRepository;

    // Constructors
    public AuthService(GenericRepository<User> userRepository, GenericRepository<Restaurant> restaurantRepository) {
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    // Methods
    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return A User object if the username exists, null otherwise.
     */
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username, User.class);
    }

    /**
     * Finds a restaurant by its username.
     *
     * @param username The username of the restaurant to find.
     * @return A Restaurant object if the username exists, null otherwise.
     */
    public Restaurant findRestaurantByUsername(String username) {
        return restaurantRepository.findByUsername(username, Restaurant.class);
    }

    /**
     * Registers a new user.
     *
     * @param user The User object to register.
     * @return A string message indicating success or failure.
     */
    public String registerUser(User user) {
        // Check whether the username and password are not empty
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "Username cannot be empty.";
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            return "Password cannot be empty.";
        }

        // Check whether the user with given credentials exists or not

        if (userRepository.findByUsername(user.getUsername(), User.class) != null) {
            return "User already exists.";
        }
        userRepository.save(user); // save the user
        return "User registered successfully.";
    }

    /**
     * Registers a new restaurant.
     *
     * @param restaurant The Restaurant object to register.
     * @return A string message indicating success or failure.
     */
    public String registerRestaurant(Restaurant restaurant) {
        // Check whether the username and password are not empty
        if (restaurant.getUsername() == null || restaurant.getUsername().trim().isEmpty()) {
            return "Username cannot be empty.";
        }
        if (restaurant.getPassword() == null || restaurant.getPassword().trim().isEmpty()) {
            return "Password cannot be empty.";
        }

        // Check whether the restaurant with given credentials exists or not
        if (restaurantRepository.findByUsername(restaurant.getUsername(), Restaurant.class) != null) {
            return "Restaurant already exists.";
        }
        restaurantRepository.save(restaurant); // save the restaurant
        return "Restaurant registered successfully.";
    }


    /**
     * Logs in a user by validating their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if login is successful, false otherwise.
     */
    public boolean loginUser(String username, String password) {
        User user = userRepository.findByUsername(username, User.class);
        if (user == null) {
            return false; // Username not found in the user repository
        }
        return user.getPassword().equals(password); // Password check
    }

    /**
     * Logs in a restaurant by validating its username and password.
     *
     * @param username The username of the restaurant.
     * @param password The password of the restaurant.
     * @return True if login is successful, false otherwise.
     */
    public boolean loginRestaurant(String username, String password) {
        Restaurant restaurant = restaurantRepository.findByUsername(username, Restaurant.class);
        if (restaurant == null) {
            return false; // Username not found in the restaurant repository
        }

        return restaurant.getPassword().equals(password); // Password check
    }

}
