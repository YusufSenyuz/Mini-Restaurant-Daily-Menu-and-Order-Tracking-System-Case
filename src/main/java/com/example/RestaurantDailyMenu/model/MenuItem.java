package com.example.RestaurantDailyMenu.model;

import java.util.List;

/**
 * Model class representing a menu item in the restaurant.
 * It includes details such as the item name, price, description, optional description in parentheses,
 * a flag indicating whether the item is new, and a list of colors associated with the item.
 */
public class MenuItem {
    // Properties
    private String name;
    private double price;
    private String description;
    private String parenthesesDescription; // Optional description inside parentheses
    private boolean isNew; // Boolean for whether the item is new
    private List<String> colors; // List of colors representing something about the item

    // Constructors
    public MenuItem(String name, double price, String description, String parenthesesDescription, boolean isNew, List<String> colors) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.parenthesesDescription = parenthesesDescription;
        this.isNew = isNew;
        this.colors = colors;
    }

    public MenuItem() {
    }

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParenthesesDescription() {
        return parenthesesDescription;
    }

    public void setParenthesesDescription(String parenthesesDescription) {
        this.parenthesesDescription = parenthesesDescription;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean isNew) {
        this.isNew = isNew;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }


}
