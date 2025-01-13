package com.example.RestaurantDailyMenu.interfaces;

import com.example.RestaurantDailyMenu.dto.OrderDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * OrderServiceInterface defines the contract for order management operations.
 */
public interface OrderServiceInterface {

    /**
     * Creates a new order for a specific user.
     *
     * @param username the username of the user placing the order.
     * @param orderDTO the data transfer object containing order details.
     * @return the String of success or failure
     */
    String createOrder(String username, OrderDTO orderDTO);

    /**
     * Updates the status of an order for a specific user.
     *
     * @param username    the username of the user associated with the order.
     * @param orderDTO the data transfer object containing order details.
     * @return true if the order was successfully updated, false otherwise.
     */
    boolean updateOrderStatus(String username, OrderDTO orderDTO);

    /**
     * Retrieves all orders associated with a specific user.
     *
     * @param username the username of the user whose orders are being fetched.
     * @return a list of OrderDTO objects representing the user's orders.
     */
    List<OrderDTO> getOrdersByUsername(String username);

    /**
     * Retrieves all orders placed by all users.
     *
     * @return a list of OrderDTO objects representing all orders.
     */
    List<OrderDTO> getOrdersByAllUsers();
}
