package com.example.RestaurantDailyMenu.repository;
import com.example.RestaurantDailyMenu.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the OrderRepository class.
 * It contains various test methods to ensure the proper functionality of the OrderRepository class.
 *
 */
class OrderRepositoryTest {

    // Properties
    private OrderRepository orderRepository;

    // Test methods
    /**
     * Initializes the OrderRepository instance before each test.
     * This method is called before each test method to ensure that we start with a fresh instance of the repository.
     */
    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
    }

    /**
     * Test for saving an order in the repository.
     * This test ensures that when an order is saved, it is assigned an ID and stored correctly.
     */
    @Test
    void testSave() {
        // Create a new order object
        Order order = new Order();
        order.setUsername("john_doe");
        order.setItemName("Pizza");
        order.setPrice(20.50);

        // Save the order in the repository
        Order savedOrder = orderRepository.save(order);

        // Verify the order is saved correctly
        assertNotNull(savedOrder.getId());  // Ensure the order has an ID after saving
        assertEquals("john_doe", savedOrder.getUsername()); // Ensure the username matches
        assertEquals("Pizza", savedOrder.getItemName()); // Ensure the item matches
    }

    /**
     * Test for finding orders by username.
     * This test ensures that the repository can retrieve orders based on the provided username.
     */
    @Test
    void testFindByUsername() {
        // Create and save orders with different usernames
        Order order1 = new Order();
        order1.setUsername("john_doe");
        order1.setItemName("Pizza");
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setUsername("jane_doe");
        order2.setItemName("Burger");
        orderRepository.save(order2);

        // Retrieve orders for "john_doe"
        List<Order> johnOrders = orderRepository.findByUsername("john_doe");

        // Verify that only the correct orders are returned
        assertEquals(1, johnOrders.size());
        assertEquals("john_doe", johnOrders.get(0).getUsername());
        assertEquals("Pizza", johnOrders.get(0).getItemName());
    }

    /**
     * Test for finding an order by ID.
     * This test ensures that the repository can correctly retrieve an order by its ID.
     */
    @Test
    void testFindById() {
        // Create and save an order
        Order order = new Order();
        order.setUsername("john_doe");
        order.setItemName("Pizza");
        Order savedOrder = orderRepository.save(order);

        // Retrieve the order by ID
        Optional<Order> foundOrder = orderRepository.findById(savedOrder.getId());

        // Verify the order is correctly found
        assertTrue(foundOrder.isPresent()); // Ensure the order is present
        assertEquals("john_doe", foundOrder.get().getUsername());
        assertEquals("Pizza", foundOrder.get().getItemName());
    }

    /**
     * Test for retrieving all orders.
     * This test ensures that the repository can return all orders stored in the system.
     */
    @Test
    void testFindAll() {
        // Create and save two orders
        Order order1 = new Order();
        order1.setUsername("john_doe");
        order1.setItemName("Pizza");
        orderRepository.save(order1);

        Order order2 = new Order();
        order2.setUsername("jane_doe");
        order2.setItemName("Burger");
        orderRepository.save(order2);

        // Retrieve all orders
        List<Order> orders = orderRepository.findAll();

        // Verify that both orders are returned
        assertEquals(2, orders.size());
    }

    /**
     * Test for retrieving an order by its ID.
     * This test ensures that if an order is not found by its ID, the result is null.
     */
    @Test
    void testGetOrderByIdNotFound() {
        // Attempt to retrieve an order with a non-existent ID
        Order order = orderRepository.getOrderById(999L);

        // Verify that no order is found
        assertNull(order);
    }

    /**
     * Test for retrieving an order by its ID when it exists.
     * This test ensures that the correct order is returned when a valid ID is provided.
     */
    @Test
    void testGetOrderById() {
        // Create and save an order
        Order order = new Order();
        order.setUsername("john_doe");
        order.setItemName("Pizza");
        Order savedOrder = orderRepository.save(order);

        // Retrieve the order by ID
        Order foundOrder = orderRepository.getOrderById(savedOrder.getId());

        // Verify the order is correctly found
        assertNotNull(foundOrder);  // Ensure the order is found
        assertEquals("john_doe", foundOrder.getUsername());
        assertEquals("Pizza", foundOrder.getItemName());
    }
}
