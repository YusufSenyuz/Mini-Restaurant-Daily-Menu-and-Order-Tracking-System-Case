package com.example.RestaurantDailyMenu.service;

import com.example.RestaurantDailyMenu.dto.MenuCategoryDTO;
import com.example.RestaurantDailyMenu.dto.MenuDTO;
import com.example.RestaurantDailyMenu.dto.MenuItemDTO;
import com.example.RestaurantDailyMenu.interfaces.MenuServiceInterface;
import com.example.RestaurantDailyMenu.model.Menu;
import com.example.RestaurantDailyMenu.model.MenuCategory;
import com.example.RestaurantDailyMenu.model.MenuCategoryEnum;
import com.example.RestaurantDailyMenu.model.MenuItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * MenuService class is responsible for handling menu-related operations.
 * Includes methods to initialize a daily menu and convert it to a DTO representation.
 */
@Service
public class MenuService implements MenuServiceInterface {
    // Properties
    private Menu menu;

    // Constructors
    public MenuService() {
    }

    // methods


    /**
     * Initializes today's menu using sample data.
     * Currently, this method creates a static menu as there is no database integration.
     * It uses sample data provided for modeling.
     *
     * @return A Menu object representing today's menu.
     */
    public Menu initializeTodayMenu() {
        // add menu items by using the sample data provided

        // Dishes & Appetizers
        try {
            MenuItem tartineLunchBox = new MenuItem(
                    "Tartine Lunch Box with Mozzarella",
                    64.00,
                    "Tartine prepared with Farro sourdough purple bread, zucchini, mozzarella, carrot, hummus, salad with pineapple, CF vegan chocolate",
                    null,
                    false,
                    null
            );

            MenuItem avocadoPeppersLunchBox = new MenuItem(
                    "Avocado Sauced Stuffed Red Peppers Lunch Box",
                    64.00,
                    "Avocado sauced roasted red peppers with legumes and vegetable stuffing, colorful salad with pineapple, chocolate-covered stuffed apricots",
                    null,
                    false,
                    Arrays.asList("orange")
            );

            MenuItem summerDetoxBowl = new MenuItem(
                    "Summer Detox Bowl",
                    62.00,
                    "Mixed greens, baked eggplant, pepper, asparagus, chickpeas with tahini, cherry tomato, cucumber, corn and strawberry sunflower seeds",
                    null,
                    false,
                    null
            );

            MenuItem springPastaSalad = new MenuItem(
                    "Spring Pasta Salad with Eggplant and Basil",
                    49.00,
                    "Gluten-free yellow pea pasta, eggplant, cherry tomatoes, mushrooms, garlic, basil, fresh aromatic herbs, olive oil, spices",
                    "750cc",
                    true,
                    Arrays.asList("orange")

            );

            MenuItem saladInAJar = new MenuItem(
                    "Salad in a Jar",
                    32.00,
                    "",
                    "without dressing",
                    false,
                    Arrays.asList("orange", "blue", "green", "red")
            );

            // Drinks
            MenuItem refreshingGreenJuice = new MenuItem(
                    "Refreshing Green Vegetable Juice",
                    25.00,
                    "Spinach, mint, apple, cucumber, lemon",
                    "250 ml",
                    false,
                    Arrays.asList("orange")
            );

            MenuItem slimDownBlend = new MenuItem(
                    "Slim Down Blend",
                    26.00,
                    "Celery stalk, parsley, cucumber, banana, pineapple, chia seeds, lemon, ginger",
                    "250 ml",
                    false,
                    Arrays.asList("orange")
            );

            MenuItem blackberryKombucha = new MenuItem(
                    "Blackberry Kombucha",
                    25.00,
                    "",
                    "250 ml",
                    false,
                    Arrays.asList("orange")
            );

            // Snacks
            MenuItem blueberryRawCake = new MenuItem(
                    "Blueberry Raw Cake",
                    45.00,
                    "Almond, date, cashew, date extract, blueberry, coconut oil",
                    null,
                    true,
                    null
            );

            MenuItem mintyCocoaRawCake = new MenuItem(
                    "Minty Cocoa Raw Cake",
                    42.00,
                    "Almond, hazelnut, walnut, date, cashew, coconut oil, chocolate, mint, vanilla, coconut oil",
                    null,
                    false,
                    null
            );

            MenuItem chocolateGranolaPudding = new MenuItem(
                    "Chocolate Granola Pudding",
                    40.00,
                    "Banana, avocado, date extract, coconut milk, vanilla extract, cardamom, cocoa, GF granola, cocoa hazelnut butter",
                    null,
                    false,
                    Arrays.asList("orange")
            );

            // Soup of the Day
            MenuItem asparagusDetoxSoup = new MenuItem(
                    "Asparagus Detox Soup",
                    26.00,
                    "Zucchini, broccoli, asparagus, onion, garlic, spices, parsley",
                    "(400 ml)",
                    false,
                    Arrays.asList("orange")
            );

            // Categories
            MenuCategory dishesCategory = new MenuCategory(MenuCategoryEnum.DISHES_AND_APPETIZERS, Arrays.asList(
                    tartineLunchBox, avocadoPeppersLunchBox, summerDetoxBowl, springPastaSalad, saladInAJar
            ));
            MenuCategory soupCategory = new MenuCategory(MenuCategoryEnum.SOUP_OF_THE_DAY, Arrays.asList(
                    asparagusDetoxSoup
            ));
            MenuCategory drinksCategory = new MenuCategory(MenuCategoryEnum.DRINKS, Arrays.asList(
                    refreshingGreenJuice, slimDownBlend, blackberryKombucha
            ));
            MenuCategory snacksCategory = new MenuCategory(MenuCategoryEnum.SNACKS, Arrays.asList(
                    blueberryRawCake, mintyCocoaRawCake, chocolateGranolaPudding
            ));


            return new Menu("Today", Arrays.asList(dishesCategory, soupCategory, drinksCategory, snacksCategory));
        }
        catch (Exception e) {
            return null;
        }
    }


    /**
     * Retrieves the menu for a specified day and converts it to a DTO format.
     *
     * @param day The day for which the menu is being fetched.
     * @return A MenuDTO object representing the menu for the specified day, or null if initialization fails.
     */
    public MenuDTO getMenuForDay(String day) {
        // initialize the menu
        Menu menu = null;
        menu = initializeTodayMenu();

        // check whether the menu null is or not for safety
        if (menu == null) {
            return null;
        }

        // convert menu model to DTO
        List<MenuCategoryDTO> categoryDTOs = new ArrayList<>();
        for (MenuCategory category : menu.getCategories()) {
            List<MenuItemDTO> itemDTOs = new ArrayList<>();
            for (MenuItem item : category.getItems()) {
                itemDTOs.add(new MenuItemDTO(item.getName(), item.getPrice(), item.getDescription(),
                        item.getParenthesesDescription(), item.isNew(), item.getColors()));
            }
            categoryDTOs.add(new MenuCategoryDTO(category.getCategoryEnum(), itemDTOs));
        }

        MenuDTO menuDTO = new MenuDTO(menu.getDay(), categoryDTOs);
        return menuDTO;
    }
}
