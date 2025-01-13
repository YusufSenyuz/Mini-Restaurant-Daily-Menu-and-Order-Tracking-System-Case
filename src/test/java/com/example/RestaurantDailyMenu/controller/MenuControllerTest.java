package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.dto.MenuCategoryDTO;
import com.example.RestaurantDailyMenu.dto.MenuDTO;
import com.example.RestaurantDailyMenu.dto.MenuItemDTO;
import com.example.RestaurantDailyMenu.model.MenuCategoryEnum;
import com.example.RestaurantDailyMenu.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit test class for MenuController.
 * This class tests the MenuController methods to ensure proper behavior when fetching the daily menu.
 */
class MenuControllerTest {

    // Properties
    @Mock
    private MenuService menuService; // Mocking the MenuService to simulate the service layer behavior

    @InjectMocks
    private MenuController menuController; // Injecting the mocked MenuService into MenuController

    // Test methods
    /**
     * Initializes mocks before each test.
     */
    @BeforeEach
    void setUp() {
        // Initialize the mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests fetching today's menu from the controller.
     * Expects a successful response with today's menu data.
     */
    @Test
    void getTodayMenu_shouldReturnTodayMenu() {
        // Create menu items for different categories
        MenuItemDTO pizza = new MenuItemDTO("Pizza", 10.99,"Delicious cheese pizza", "hot", true, null );
        MenuItemDTO pasta = new MenuItemDTO("Pasta", 5.5,"Delicious pasta", "cold", false, null );
        MenuItemDTO salad = new MenuItemDTO("Salad", 18.45,"Delicious salad", "healthy", false, null );

        // Create a MenuCategoryDTO for "Today's Special" category
        MenuCategoryDTO specialCategory = new MenuCategoryDTO(MenuCategoryEnum.DISHES_AND_APPETIZERS,
                Arrays.asList(pizza, pasta, salad));

        // Create the MenuDTO with today's special category
        MenuDTO menuDTO = new MenuDTO("Today’s Special", Arrays.asList(specialCategory));
        // Mocking the service to return the above MenuDTO when the method is called
        when(menuService.getMenuForDay("today")).thenReturn(menuDTO);

        // Act: Call the controller method to fetch the today's menu
        ResponseEntity<MenuDTO> response = menuController.getTodayMenu();

        // Assert: Check that the response status code is 200 OK and the response body matches the expected MenuDTO
        assertEquals(200, response.getStatusCode().value());
        assertEquals(menuDTO, response.getBody());
    }

    /**
     * Tests the scenario where the service returns null for today's menu.
     * Expects a response indicating no menu is available.
     */
    @Test
    void getTodayMenu_whenMenuNotFound_shouldReturnNoContent() {
        // Arrange: Mocking the service to return null, simulating the absence of today's menu
        when(menuService.getMenuForDay("today")).thenReturn(null);

        // Act: Call the controller method to fetch the today's menu
        ResponseEntity<MenuDTO> response = menuController.getTodayMenu();

        // Assert: Check that the response status code is 204 No Content
        assertEquals(204,  response.getStatusCode().value());
        assertNull(response.getBody()); // Assert that the response body is null when no menu is found
    }
}
