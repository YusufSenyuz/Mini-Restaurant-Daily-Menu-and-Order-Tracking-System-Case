package com.example.RestaurantDailyMenu.service;


import com.example.RestaurantDailyMenu.dto.OrderDTO;
import com.example.RestaurantDailyMenu.model.Order;
import com.example.RestaurantDailyMenu.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the OrderService class.
 * These tests validate the functionality of the methods in the OrderService class,
 * such as creating, retrieving, updating, and deleting orders.
 */
class OrderServiceTest {

    // Properties

    // Instance of the OrderService to be tested
    private OrderService orderService;

    // Mocked OrderRepository to simulate interactions with the database
    @Mock
    private OrderRepository orderRepository;


    // Test methods
    @BeforeEach
    void setUp() {
        // Mocking the GenericRepository classes to isolate the tests from the database
        orderRepository = mock(OrderRepository.class);

        // Creating the OrderService instance with the mocked repositories
        orderService = new OrderService(orderRepository);
    }

    /**
     * Test the createOrder() method to ensure it creates an order successfully.
     * Verifies that the correct response is returned and the order is saved to the repository.
     */
    @Test
    void testCreateOrder_Success() {
        // Create a new order with adjusted parameters
        Order order = new Order(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);

        OrderDTO orderDTO = new OrderDTO(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);


        // When the repository's findById method is called, return an empty Optional (order does not exist)
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Mock the behavior of save method to return the created order
        when(orderRepository.save(order)).thenReturn(order);

        // Assuming OrderService has a createOrder method that takes an order as input
        String result = orderService.createOrder("john_doe", orderDTO);

        // Assert that the order creation was successful
        assertEquals("Order created successfully.", result);
    }

    /**
     * Test the createOrder() method when the order already exists.
     * Verifies that the appropriate response is returned without saving the order.
     */
    @Test
    void testCreateOrder_AlreadyExists() {
        // Create a new order with adjusted parameters
        Order order = new Order(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);
        OrderDTO orderDTO = new OrderDTO(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);

        // Mock the repository behavior: the order already exists
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(new Order()));

        // Set the username
        String username = "john_doe";
        // Call the method under test
        String result = orderService.createOrder(username, orderDTO);

        // Assert that the correct message is returned when the order already exists
        assertEquals("Order already exists.", result);

        // Verify that the save method was not called on the repository
        verify(orderRepository, never()).save(any(Order.class));
    }

    /**
     * Test the getOrderById() method when the order is not found.
     * Verifies that the method returns null when the order does not exist.
     */
    @Test
    void testGetOrderById_NotFound() {
        // Mock the repository behavior: the order does not exist
        when(orderRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Call the method under test
        Order result = orderService.getOrderById(1L);

        // Assert that no order is found (null is returned)
        assertNull(result);
    }

    /**
     * Test the updateOrder() method to ensure it updates an existing order.
     * Verifies that the correct response is returned and the order is saved to the repository.
     */
    @Test
    void testUpdateOrder_Success() {
        // Create a new OrderDTO with the necessary details for updating
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L); // Set the ID of the order to update
        orderDTO.setStatus("approved");
        orderDTO.setApproveDate(LocalDateTime.now().plusDays(1)); // Set approval date

        // Create a new order with adjusted parameters
        Order order = new Order(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);

        // Mock the repository behavior: the order exists and belongs to the correct user
        when(orderRepository.findById(orderDTO.getId())).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(order);

        // Call the method under test: updating the order status
        boolean result = orderService.updateOrderStatus("john_doe", orderDTO);

        // Assert that the order was updated successfully
        assertTrue(result);

        // Verify that the save method was called on the repository
        verify(orderRepository).save(order);

        // Assert that the order's status was updated to "approved"
        assertEquals("approved", order.getStatus());
        assertNotNull(order.getApproveDate());
    }


    /**
     * Test the updateOrder() method when the order does not exist.
     * Verifies that the appropriate response is returned without updating the order.
     */
    @Test
    void testUpdateOrder_NotFound() {
        // Create a new OrderDTO with the necessary details for updating
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L); // Set the ID of the order to update
        orderDTO.setStatus("approved");

        // Create a new order (it does not exist in the repository)
        Order order = new Order(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);

        // Mock the repository behavior: the order does not exist
        when(orderRepository.findById(orderDTO.getId())).thenReturn(Optional.empty());

        // Call the method under test: trying to update a non-existent order
        boolean result = orderService.updateOrderStatus("john_doe", orderDTO);

        // Assert that the correct response is returned when the order is not found
        assertFalse(result);

        // Verify that the save method was not called on the repository
        verify(orderRepository, never()).save(order);
    }


}
