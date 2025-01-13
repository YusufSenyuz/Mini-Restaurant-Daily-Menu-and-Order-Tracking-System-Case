package com.example.RestaurantDailyMenu.interfaces;


import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;

/**
 * AuthServiceInterface defines the contract for authentication-related operations.
 * It includes methods for user and restaurant registration, login, and retrieval by username.
 */
public interface AuthServiceInterface {

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to find.
     * @return A User object if the username exists, null otherwise.
     */
    User findUserByUsername(String username);

    /**
     * Finds a restaurant by its username.
     *
     * @param username The username of the restaurant to find.
     * @return A Restaurant object if the username exists, null otherwise.
     */
    Restaurant findRestaurantByUsername(String username);

    /**
     * Registers a new user.
     *
     * @param user The User object to register.
     * @return A string message indicating success or failure.
     */
    String registerUser(User user);

    /**
     * Registers a new restaurant.
     *
     * @param restaurant The Restaurant object to register.
     * @return A string message indicating success or failure.
     */
    String registerRestaurant(Restaurant restaurant);

    /**
     * Logs in a user by validating their username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if login is successful, false otherwise.
     */
    boolean loginUser(String username, String password);

    /**
     * Logs in a restaurant by validating its username and password.
     *
     * @param username The username of the restaurant.
     * @param password The password of the restaurant.
     * @return True if login is successful, false otherwise.
     */
    boolean loginRestaurant(String username, String password);
}
