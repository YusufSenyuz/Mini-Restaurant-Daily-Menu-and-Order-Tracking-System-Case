package com.example.RestaurantDailyMenu.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a menu item.
 * It contains details such as the item's name, price, description,
 * parentheses description, whether it is new, and associated colors.
 */
public class MenuItemDTO {
    // Properties
    private String name;
    private double price;
    private String description;
    private String parenthesesDescription; // Optional description in parentheses
    private boolean isNew; // Whether the item is new
    private List<String> colors; // List of colors representing something about the menu item

    // Constructors
    public MenuItemDTO(String name, double price, String description, String parenthesesDescription, boolean isNew, List<String> colors) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.parenthesesDescription = parenthesesDescription;
        this.isNew = isNew;
        this.colors = colors;
    }

    public MenuItemDTO() {

    }

    // Getters and setters
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

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", parenthesesDescription='" + parenthesesDescription + '\'' +
                ", isNew=" + isNew +
                ", colors=" + colors +
                '}';
    }
}
