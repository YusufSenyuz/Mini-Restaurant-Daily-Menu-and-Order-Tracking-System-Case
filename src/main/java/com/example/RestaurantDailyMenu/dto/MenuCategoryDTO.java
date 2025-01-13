package com.example.RestaurantDailyMenu.dto;

import com.example.RestaurantDailyMenu.model.MenuCategoryEnum;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a menu category.
 * It contains the category name and a list of menu items belonging to that category.
 */
public class MenuCategoryDTO {
    // Properties
    private MenuCategoryEnum name; // The name of the category (e.g., "Dishes", "Drinks")
    private List<MenuItemDTO> items; // List of menu item DTOs in this category

    // Constructors
    public MenuCategoryDTO(MenuCategoryEnum name, List<MenuItemDTO> items) {
        this.name = name;
        this.items = items;
    }

    // Getters and setters
    public MenuCategoryEnum getName() {
        return name;
    }

    public void setName(MenuCategoryEnum name) {
        this.name = name;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "MenuCategoryDTO{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
