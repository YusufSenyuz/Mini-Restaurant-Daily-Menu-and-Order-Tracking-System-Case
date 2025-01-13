package com.example.RestaurantDailyMenu.model;

/**
 * Abstract model class representing a base user with common properties like username and password.
 * This class serves as a parent for different types of users (regular users and restaurants),
 * providing shared functionality and reducing code duplication. It cannot be instantiated directly.
 */

public abstract class BaseUser {
    // Properties
    private String username;
    private String password;

    // Constructors
    public BaseUser() {}

    public BaseUser(String username, String password) {
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
