package com.example.RestaurantDailyMenu.service;

import com.example.RestaurantDailyMenu.dto.MenuCategoryDTO;
import com.example.RestaurantDailyMenu.dto.MenuDTO;
import com.example.RestaurantDailyMenu.dto.MenuItemDTO;
import com.example.RestaurantDailyMenu.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link MenuService} class.
 * This test class verifies the functionality of the menu service operations, including menu initialization,
 * conversion to DTO format, and logging behavior. It uses mocks and spies to simulate and validate
 * interactions without a real database or external dependencies.
 */
public class MenuServiceTest {

    // Properties
    @InjectMocks
    private MenuService menuService;

    @Mock
    private Menu mockMenu;
    // Test methods

    /**
     * Initializes the mock objects and dependencies before each test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    /**
     * Tests the initialization of today's menu through the {@link MenuService#getMenuForDay(String)} method.
     * Verifies that the menu is not null, correctly initialized with the specified day, and contains categories and items.
     */
    @Test
    public void testInitializeTodayMenu() {
        // Invoke method
        MenuDTO menuDTO = menuService.getMenuForDay("Today");

        // Verify menu is not null
        assertNotNull(menuDTO, "Menu should be initialized");
        assertEquals("Today", menuDTO.getDay(), "Menu day should be 'Today'");

        // Verify categories and items are initialized
        assertFalse(menuDTO.getCategories().isEmpty(), "Menu categories should not be empty");
        for (MenuCategoryDTO category : menuDTO.getCategories()) {
            assertFalse(category.getItems().isEmpty(), "Menu items should not be empty");
        }
    }

    /**
     * Tests the behavior of {@link MenuService#getMenuForDay(String)} when menu initialization returns null.
     * Verifies that a null MenuDTO is returned in this scenario.
     */
    @Test
    public void testGetMenuForDay_NullInitialization() {
        // Simulate null menu
        MenuService spyMenuService = spy(menuService);
        doReturn(null).when(spyMenuService).initializeTodayMenu();

        MenuDTO menuDTO = spyMenuService.getMenuForDay("Today");
        assertNull(menuDTO, "MenuDTO should be null when initialization fails");
    }

    /**
     * Tests the conversion of the menu model to DTO format through {@link MenuService#getMenuForDay(String)}.
     * Verifies that the conversion is successful, the day is correct, and the DTO contains non-empty categories and items.
     */
    @Test
    public void testGetMenuForDay_ConvertsToDTO() {
        MenuDTO menuDTO = menuService.getMenuForDay("Today");

        // Verify conversion to DTO
        assertNotNull(menuDTO, "MenuDTO should not be null");
        assertEquals("Today", menuDTO.getDay(), "MenuDTO day should be 'Today'");
        assertFalse(menuDTO.getCategories().isEmpty(), "MenuDTO categories should not be empty");

        for (MenuCategoryDTO categoryDTO : menuDTO.getCategories()) {
            assertFalse(categoryDTO.getItems().isEmpty(), "MenuItemDTOs should not be empty");
            for (MenuItemDTO itemDTO : categoryDTO.getItems()) {
                assertNotNull(itemDTO.getName(), "MenuItemDTO name should not be null");
                assertNotNull(itemDTO.getDescription(), "MenuItemDTO description should not be null");
            }
        }
    }

    /**
     * Verifies the logging behavior of {@link MenuService} when fetching the menu for a day.
     * Ensures that the initialization method is called exactly once.
     */
    @Test
    public void testMenuLogging() {
        MenuService spyMenuService = spy(menuService);
        spyMenuService.getMenuForDay("Today");
        verify(spyMenuService, times(1)).initializeTodayMenu();
    }
}
