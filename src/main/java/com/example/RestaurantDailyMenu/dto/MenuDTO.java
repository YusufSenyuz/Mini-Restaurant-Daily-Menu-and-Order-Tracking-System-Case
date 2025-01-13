package com.example.RestaurantDailyMenu.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a menu.
 * It contains the day and a list of menu categories for that day.
 */
public class MenuDTO {
    // Properties
    private String day;
    private List<MenuCategoryDTO> categories;

    // Constructors
    public MenuDTO(String day, List<MenuCategoryDTO> categories) {
        this.day = day;
        this.categories = categories;
    }

    // Getters and setters

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<MenuCategoryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<MenuCategoryDTO> categories) {
        this.categories = categories;
    }
}