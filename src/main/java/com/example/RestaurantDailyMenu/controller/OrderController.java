package com.example.RestaurantDailyMenu.controller;

import com.example.RestaurantDailyMenu.dto.OrderDTO;
import com.example.RestaurantDailyMenu.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * OrderController handles all operations related to creating, updating, and retrieving orders.
 * It provides endpoints to manage orders for individual users and retrieve all orders for restaurant admin.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    // Properties
    private final OrderService orderService;

    // Constructors

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Methods

    /**
     * Endpoint to create an order for a specific user.
     * This method is called after user clicks an order button
     *
     * @param username the username for which the order is created.
     * @param orderDTO the order details.
     * @return a response indicating the result of the order creation.
     */
    @PostMapping("/{username}")
    public ResponseEntity<String> createOrder(@PathVariable String username, @RequestBody OrderDTO orderDTO) {
        orderService.createOrder(username, orderDTO);
        return ResponseEntity.ok("Order created successfully for user: " + username);
    }

    /**
     * Endpoint to update the status of an existing order for a specific user.
     * This method is called after restaurant admin updates the status of an order
     *
     * @param username the username associated with the order.
     * @param orderDTO the updated order details, including status and dates.
     * @return a response indicating whether the update was successful.
     */
    @PostMapping("/{username}/update")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String username, @RequestBody OrderDTO orderDTO) {
        boolean updated = orderService.updateOrderStatus(username, orderDTO);
        if (updated) {
            return ResponseEntity.ok("Order status updated successfully for user: " + username);
        } else {
            return ResponseEntity.status(400).body("Failed to update order status.");
        }
    }

    /**
     * Endpoint to retrieve all orders for a specific user.
     * This method is called after a specific user clicks my orders button.
     *
     * @param username the username whose orders are requested.
     * @return a list of orders associated with the user.
     */
    @GetMapping("/{username}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable String username) {
        List<OrderDTO> orders = orderService.getOrdersByUsername(username);
        return ResponseEntity.ok(orders);
    }

    /**
     * Endpoint to retrieve all orders for all users.
     * This method is called after a restaurant admin log ins to the app.
     *
     * @return a list of all orders.
     */
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getOrdersByAllUsers() {
        List<OrderDTO> orders = orderService.getOrdersByAllUsers();
        return ResponseEntity.ok(orders);
    }
}