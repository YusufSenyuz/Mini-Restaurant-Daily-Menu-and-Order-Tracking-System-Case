package com.example.RestaurantDailyMenu.repository;

import com.example.RestaurantDailyMenu.model.User;
import com.example.RestaurantDailyMenu.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for the GenericRepository.
 * This test class verifies the functionality of the GenericRepository class methods for both User and Restaurant repositories.
 *
 * The tests ensure that:
 * - Users and restaurants can be saved to the repository.
 * - Users and restaurants can be retrieved by their username and class type.
 *
 * The tests use in-memory data, so no actual database or persistent storage is required.
 */
class GenericRepositoryTest {

    // Properties
    private GenericRepository<User> userRepository;
    private GenericRepository<Restaurant> restaurantRepository;

    /**
     * Initializes the GenericRepository instances before each test.
     * This method is called before each test method to ensure fresh instances of the repositories.
     */
    @BeforeEach
    void setUp() {
        userRepository = new GenericRepository<>();
        restaurantRepository = new GenericRepository<>();
    }

    /**
     * Test for saving a user to the repository.
     * This test ensures that when a user is saved, it is correctly added to the in-memory list.
     */
    @Test
    void testSaveUser() {
        // Create a user instance
        User user = new User("john_doe", "password123", "John");

        // Save the user in the repository
        User savedUser = userRepository.save(user);

        // Verify that the user is saved correctly
        assertNotNull(savedUser);  // Ensure the user is not null
        assertEquals("john_doe", savedUser.getUsername());  // Ensure the username matches
        assertEquals("password123", savedUser.getPassword());  // Ensure the password matches
        assertEquals("John", savedUser.getName());  // Ensure the name matches
    }

    /**
     * Test for saving a restaurant to the repository.
     * This test ensures that when a restaurant is saved, it is correctly added to the in-memory list.
     */
    @Test
    void testSaveRestaurant() {
        // Create a restaurant instance
        Restaurant restaurant = new Restaurant("The Pizza Place", "restaurantPassword", "John's Pizza");

        // Save the restaurant in the repository
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        // Verify that the restaurant is saved correctly
        assertNotNull(savedRestaurant);  // Ensure the restaurant is not null
        assertEquals("The Pizza Place", savedRestaurant.getUsername());  // Ensure the restaurant name matches
        assertEquals("restaurantPassword", savedRestaurant.getPassword());  // Ensure the password matches
        assertEquals("John's Pizza", savedRestaurant.getRestaurantName());  // Ensure the name matches
    }

    /**
     * Test for finding a user by their username and class type.
     * This test ensures that the repository can correctly find a user based on the username and class type.
     */
    @Test
    void testFindUserByUsername() {
        // Create a user instance and save it
        User user = new User("john_doe", "password123", "John");
        userRepository.save(user);

        // Retrieve the user by username and class type
        User foundUser = userRepository.findByUsername("john_doe", User.class);

        // Verify that the correct user is found
        assertNotNull(foundUser);  // Ensure the user is found
        assertEquals("john_doe", foundUser.getUsername());  // Ensure the username matches
        assertEquals("password123", foundUser.getPassword());  // Ensure the password matches
        assertEquals("John", foundUser.getName());  // Ensure the name matches
    }

    /**
     * Test for finding a restaurant by their name and class type.
     * This test ensures that the repository can correctly find a restaurant based on the name and class type.
     */
    @Test
    void testFindRestaurantByName() {
        // Create a restaurant instance and save it
        Restaurant restaurant = new Restaurant("The Pizza Place", "restaurantPassword", "John's Pizza");
        restaurantRepository.save(restaurant);

        // Retrieve the restaurant by name and class type
        Restaurant foundRestaurant = restaurantRepository.findByUsername("The Pizza Place", Restaurant.class);

        // Verify that the correct restaurant is found
        assertNotNull(foundRestaurant);  // Ensure the restaurant is found
        assertEquals("The Pizza Place", foundRestaurant.getUsername());  // Ensure the restaurant name matches
        assertEquals("restaurantPassword", foundRestaurant.getPassword());  // Ensure the password matches
        assertEquals("John's Pizza", foundRestaurant.getRestaurantName());  // Ensure the name matches
    }

    /**
     * Test for finding a user when no matching username exists.
     * This test ensures that the repository returns null if no user with the provided username is found.
     */
    @Test
    void testFindUserByUsernameNotFound() {
        // Try to find a user that doesn't exist
        User foundUser = userRepository.findByUsername("non_existent_user", User.class);

        // Verify that no user is found
        assertNull(foundUser);  // Ensure the result is null
    }

    /**
     * Test for finding a restaurant when no matching name exists.
     * This test ensures that the repository returns null if no restaurant with the provided name is found.
     */
    @Test
    void testFindRestaurantByNameNotFound() {
        // Try to find a restaurant that doesn't exist
        Restaurant foundRestaurant = restaurantRepository.findByUsername("non_existent_restaurant", Restaurant.class);

        // Verify that no restaurant is found
        assertNull(foundRestaurant);  // Ensure the result is null
    }
}
