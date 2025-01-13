package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.dto.MenuDTO;
import com.example.RestaurantDailyMenu.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MenuController handles requests related to retrieving menus.
 * Provides endpoints to fetch menu details for specific days.
 */
@RestController
@RequestMapping("/menus")
public class MenuController {
    // Properties
    private final MenuService menuService;

    // Constructors

    public MenuController(MenuService menuService) {

        this.menuService = menuService;
    }

    // Methods

    /**
     * Endpoint to retrieve today's menu.
     * It uses MenuService to handle creation of menu
     *
     * @return A ResponseEntity containing today's menu as a MenuDTO object.
     */
    @GetMapping("/today")
    public ResponseEntity<MenuDTO> getTodayMenu() {
        MenuDTO menu = menuService.getMenuForDay("today");
        // Return No Content if menu is null
        if (menu == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(menu);
    }

}
