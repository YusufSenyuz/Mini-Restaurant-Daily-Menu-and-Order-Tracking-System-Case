package com.example.RestaurantDailyMenu.model;

/**
 * Model class representing the credentials of a user.
 * This class is used by AuthController, in loginUser and loginRestaurant methods
 * This class holds:
 * - username: The username of the user.
 * - password: The password of the user.
 *
 * It provides a default constructor and getters/setters for accessing and modifying the user's credentials.
 */
public class UserCredentials {
    // Properties
    private String username;
    private String password;

    // Constructors
    public UserCredentials() {}

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
