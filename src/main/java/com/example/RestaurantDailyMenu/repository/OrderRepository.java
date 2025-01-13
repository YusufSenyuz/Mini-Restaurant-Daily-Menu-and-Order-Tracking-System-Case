package com.example.RestaurantDailyMenu.repository;

import com.example.RestaurantDailyMenu.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The OrderRepository class is responsible for managing the in-memory storage and retrieval of orders.
 * It provides methods for saving orders, finding orders by username, retrieving orders by their ID,
 * and fetching all orders stored in the system.
 *
 * This class uses a ConcurrentHashMap to store the orders in memory, with the order ID as the key and
 * the Order object as the value. It supports basic CRUD operations for order management, without
 * relying on a database.
 */
@Repository
public class OrderRepository {
    // Properties
    private final Map<Long, Order> orderStore = new ConcurrentHashMap<>();     // In-memory map to store orders by their ID
    private long idCounter = 1;

    // Methods
    /**
     * Method to save an order.
     * If the order doesn't already have an ID, a new ID is generated and assigned to it.
     * The order is then stored in the in-memory map.
     *
     * @param order The order to be saved
     * @return The saved order
     */
    public Order save(Order order) {
        // Auto-generate an ID for new orders
        if (order.getId() == null) {
            order.setId(idCounter++); // update counter
        }
        orderStore.put(order.getId(), order);
        return order;
    }


    /**
     * Method to find orders by the username.
     * This method filters the orders in the in-memory store based on the provided username.
     *
     * @param username The username whose orders are to be retrieved
     * @return A list of orders associated with the given username
     */
    public List<Order> findByUsername(String username) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderStore.values()) {
            if (order.getUsername().equals(username)) {
                orders.add(order);
            }
        }
        return orders;
    }


    /**
     * Method to find an order by its ID.
     * This method returns an Optional containing the order if it exists, or an empty Optional if the order is not found.
     *
     * @param id The ID of the order to be retrieved
     * @return An Optional containing the order, or empty if the order is not found
     */
    public Optional<Order> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(orderStore.get(id));
    }




    /**
     * Method to find all orders in the in-memory store.
     * This method returns a list of all orders currently stored.
     *
     * @return A list of all orders in the order store
     */
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderStore.values()) {
            orders.add(order);
        }
        return orders;
    }

    /**
     * Retrieves an order by its ID.
     * This method searches for an order in the in-memory store based on the provided ID.
     * If an order with the given ID is found, it will be returned; otherwise, null will be returned.
     *
     * @param id The ID of the order to be retrieved.
     * @return The order if found, or null if no order is found with the given ID.
     */
    public Order getOrderById(Long id) {
        return orderStore.get(id);
    }

}
