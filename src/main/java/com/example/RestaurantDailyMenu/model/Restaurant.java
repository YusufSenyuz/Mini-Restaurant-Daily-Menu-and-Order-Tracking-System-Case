package com.example.RestaurantDailyMenu.model;

/**
 * Model class representing a Restaurant, which extends from BaseUser.
 * This class holds information specific to a restaurant, including:
 * - restaurantName: The name of the restaurant.
 *
 * It provides constructors for initialization and getters/setters for accessing and modifying the restaurant's name.
 */
public class Restaurant extends BaseUser {
    // Properties
    private String restaurantName;

    // Constructors
    public Restaurant() {}

    public Restaurant(String username, String password, String restaurantName) {
        super(username, password);
        this.restaurantName = restaurantName;
    }

    // getters and setters
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
