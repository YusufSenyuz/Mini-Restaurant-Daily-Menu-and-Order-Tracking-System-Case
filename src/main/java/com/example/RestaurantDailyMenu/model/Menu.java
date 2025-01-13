package com.example.RestaurantDailyMenu.model;

import java.util.List;

/**
 * Model class representing a menu for a specific day, consisting of multiple categories (e.g., "Drinks", "Dishes").
 * This class contains the day for which the menu is created and the list of categories within the menu.
 */
public class Menu {
    // Properties
    private String day;  // The day for which the menu is created
    private List<MenuCategory> categories;  // List of categories in the menu ( "Drinks", "Dishes")

    // Constructor
    public Menu(String day, List<MenuCategory> categories) {
        this.day = day;
        this.categories = categories;
    }

    // getters and setters
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategory> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "day='" + day + '\'' +
                ", categories=" + categories +
                '}';
    }
}
