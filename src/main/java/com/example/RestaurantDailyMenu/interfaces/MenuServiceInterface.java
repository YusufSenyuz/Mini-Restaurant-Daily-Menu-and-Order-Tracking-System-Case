package com.example.RestaurantDailyMenu.interfaces;

import com.example.RestaurantDailyMenu.dto.MenuDTO;

/**
 * Interface defining menu service operations.
 */
public interface MenuServiceInterface {
    /**
     * Retrieves the menu for a specified day and converts it to a DTO format.
     *
     * @param day The day for which the menu is being fetched.
     * @return A MenuDTO object representing the menu for the specified day, or null if initialization fails.
     */
    MenuDTO getMenuForDay(String day);
}

