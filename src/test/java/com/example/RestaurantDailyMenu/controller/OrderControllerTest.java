package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.dto.OrderDTO;
import com.example.RestaurantDailyMenu.model.Order;
import com.example.RestaurantDailyMenu.repository.OrderRepository;
import com.example.RestaurantDailyMenu.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the OrderController class.
 * These tests verify that the OrderController methods return the correct responses
 * and interact properly with the OrderService layer.
 */

public class OrderControllerTest {
    // Properties
    @Mock
    private OrderService orderService; // Mock the service layer
    @InjectMocks
    private OrderController orderController; // Inject the controller to test

    // Test methods
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    /**
     * Test the createOrder() method in OrderController.
     * Verifies that a new order can be created successfully and the correct response is returned.
     */
    @Test
    public void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO(1L, "john_doe", "Pizza", 20.50, "Pending",
                LocalDateTime.now(), null, null);

        // Mock the service method to return a success message
        when(orderService.createOrder("john_doe", orderDTO)).thenReturn("Order created successfully.");

        // Call the controller method
        ResponseEntity<String> response = orderController.createOrder("john_doe", orderDTO);

        // Assert that the status code is OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert the response body message
        assertEquals("Order created successfully for user: john_doe", response.getBody());

        // Verify that the correct service method was called
        verify(orderService).createOrder("john_doe", orderDTO);
    }

    /**
     * Test the getOrdersByUsername() method in OrderController.
     * Verifies that all orders associated with a username are returned successfully.
     */
    @Test
    public void testGetOrdersByUsername() {
        // Create OrderDTO objects
        OrderDTO orderDTO1 = new OrderDTO(1L, "Pizza", "john_doe", 20.50, "Pending", LocalDateTime.now(), null, null);
        OrderDTO orderDTO2 = new OrderDTO(2L, "Burger", "john_doe", 15.00, "Completed", LocalDateTime.now(), null, null);

        // Mock the service method to return a List of OrderDTOs
        when(orderService.getOrdersByUsername("john_doe")).thenReturn(Arrays.asList(orderDTO1, orderDTO2));

        // Call the controller method (directly invoking the method)
        ResponseEntity<List<OrderDTO>> response = orderController.getOrdersByUser("john_doe");

        // Extract the body from the response entity
        List<OrderDTO> orders = response.getBody();

        // Verify the correct orders are returned
        assertNotNull(orders); // Ensure the list is not null
        assertEquals(2, orders.size());
        assertEquals("john_doe", orders.get(0).getUsername());
        assertEquals("Pizza", orders.get(0).getItemName());
        assertEquals("john_doe", orders.get(1).getUsername());
        assertEquals("Burger", orders.get(1).getItemName());
    }


    /**
     * Test the updateOrder() method in OrderController.
     * Verifies that an existing order can be updated successfully and the correct response is returned.
     */
    @Test
    public void testUpdateOrder() {
        // Create OrderDTO for update
        OrderDTO orderDTO = new OrderDTO(1L, "john_doe", "Pizza", 20.50, "Approved", LocalDateTime.now(), null, null);

        // Mock the service method to return a success flag
        when(orderService.updateOrderStatus("john_doe", orderDTO)).thenReturn(true);

        // Invoke the controller method
        ResponseEntity<String> response = orderController.updateOrderStatus("john_doe", orderDTO);

        // Assert that the status code is OK (200)
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Assert the response body message
        assertEquals("Order status updated successfully for user: john_doe", response.getBody());

        // Verify that the service method was called with the correct parameters
        verify(orderService).updateOrderStatus("john_doe", orderDTO);
    }
}
