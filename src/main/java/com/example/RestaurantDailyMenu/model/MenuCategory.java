package com.example.RestaurantDailyMenu.model;


import java.util.List;

/**
 * Model class representing a category in the menu (e.g., "Dishes", "Drinks").
 * This class contains the name of the category and a list of menu items that belong to that category.
 */
public class MenuCategory {
    // Properties
    private MenuCategoryEnum name;  // The name of the category (e.g., "Dishes", "Drinks")
    private List<MenuItem> items;  // List of menu items in this category

    // Constructors
    public MenuCategory(MenuCategoryEnum name, List<MenuItem> items) {
        this.name = name;
        this.items = items;
    }

    // getters and setters
    public MenuCategoryEnum getName() {
        return name;
    }

    public void setName(MenuCategoryEnum name) {
        this.name = name;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", items=" + items +
                '}';
    }

    public MenuCategoryEnum getCategoryEnum() {
        return name;
    }
}
