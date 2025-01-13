package com.example.RestaurantDailyMenu.service;

import com.example.RestaurantDailyMenu.dto.OrderDTO;
import com.example.RestaurantDailyMenu.interfaces.OrderServiceInterface;
import com.example.RestaurantDailyMenu.model.Order;
import com.example.RestaurantDailyMenu.repository.OrderRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * OrderService class is responsible for handling operations related to orders.
 * Provides methods to create, update, and retrieve orders for users and restaurants.
 */
@Service
public class OrderService implements OrderServiceInterface {
    // Properties
    private final OrderRepository orderRepository;

    // Constructors
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Methods

    /**
     * Creates a new order for a specific user.
     * This method is called when a user clicks the order button on the menu.
     *
     * @param username the username of the user placing the order.
     * @param orderDTO the data transfer object containing order details.
     */
    public String createOrder(String username, OrderDTO orderDTO) {
        // create an Order model by using OrderDTO
        Order order = new Order();
        order.setItemName(orderDTO.getItemName());
        order.setPrice(orderDTO.getPrice());
        order.setId(orderDTO.getId());
        order.setStatus("Pending"); // Default status
        order.setOrderDate(orderDTO.getOrderDate());
        order.setApproveDate(null); // To be set later
        order.setArrivalDate(null); // To be set later
        order.setUsername(username);

        // Check if the order already exists
        Optional<Order> existingOrder = orderRepository.findById(orderDTO.getId());
        if (existingOrder.isPresent()) {
            return "Order already exists.";
        }

        orderRepository.save(order);

        // Return a success message
        return "Order created successfully.";
    }

    /**
     * Updates the status of an order for a specific user.
     * This method is called when a restaurant approves or marks an order as arrived.
     *
     * @param username    the username of the user associated with the order.
     * @param orderDTO the data transfer object containing order details.
     * @return true if the order was successfully updated, false otherwise.
     */
    public boolean updateOrderStatus(String username, OrderDTO orderDTO) {
        // find the order that will be updated
        Optional<Order> orderOptional = orderRepository.findById(orderDTO.getId());
        if (orderOptional.isPresent() && orderOptional.get().getUsername().equals(username)) {
            // update the order's status and possible approve and arrival dates
            Order order = orderOptional.get();
            order.setStatus(orderDTO.getStatus());
            if ("approved".equalsIgnoreCase(orderDTO.getStatus())) {
                order.setApproveDate(orderDTO.getApproveDate());
            } else if ("arrived".equalsIgnoreCase(orderDTO.getStatus())) {
                order.setArrivalDate(orderDTO.getArrivalDate());
            }
            orderRepository.save(order); // save the order again
            return true;
        }
        return false;
    }

    /**
     * Retrieves all orders associated with a specific user.
     *
     * @param username the username of the user whose orders are being fetched.
     * @return a list of OrderDTO objects representing the user's orders.
     */
    public List<OrderDTO> getOrdersByUsername(String username) {
        List<Order> orders = orderRepository.findByUsername(username);
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Retrieves all orders placed by all users.
     *
     * @return a list of OrderDTO objects representing all orders.
     */
    public List<OrderDTO> getOrdersByAllUsers() {
        // Fetch all orders
        List<Order> orders = orderRepository.findAll();

        // Convert List<Order> to List<OrderDTO>
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    /**
     * Converts an Order entity to its corresponding OrderDTO representation.
     *
     * @param order the Order entity to be converted.
     * @return the OrderDTO representation of the order.
     */
    private OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());  // Set the id
        orderDTO.setItemName(order.getItemName());
        orderDTO.setPrice(order.getPrice());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setApproveDate(order.getApproveDate());
        orderDTO.setArrivalDate(order.getArrivalDate());
        orderDTO.setUsername(order.getUsername());
        return orderDTO;
    }

    /**
     * Retrieves an order by its ID.
     *
     * This method calls the repository's method to get the order by ID.
     * If an order with the given ID exists, it will be returned; otherwise, it will return null.
     *
     *
     * @param id The ID of the order to be retrieved.
     * @return The order if found, or null if no order is found with the given ID.
     */
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);  // Delegate the call to the repository
    }
}
