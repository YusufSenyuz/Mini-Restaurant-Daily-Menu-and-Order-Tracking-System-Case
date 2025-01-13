package com.example.RestaurantDailyMenu.model;

/**
 * Model class representing a User, which extends from BaseUser.
 * This class holds information specific to a user, including:
 * - name: The name of the user.
 *
 * It provides constructors for initialization and getters/setters for accessing and modifying the user's name.
 */

public class User extends BaseUser {
    // Properties
    private String name;

    // Constructors
    public User() {}

    public User(String username, String password, String name) {
        super(username, password);
        this.name = name;
    }

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
