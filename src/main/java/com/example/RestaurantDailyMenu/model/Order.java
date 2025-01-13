package com.example.RestaurantDailyMenu.model;

import java.time.LocalDateTime;

/**
 * Model class representing an order in the restaurant system.
 * It contains information about the order, such as the username of the customer, the item being ordered,
 * its price, the order status, and timestamps for the order, approval, and arrival dates.
 */

public class Order {
    // Properties
    private Long id;
    private String username;
    private String itemName;
    private double price;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime approveDate;
    private LocalDateTime arrivalDate;

    // Constructors
    public Order(Long id, String username, String itemName, double price, String status, LocalDateTime orderDate,
                 LocalDateTime approveDate, LocalDateTime arrivalDate) {
        this.id = id;
        this.username = username;
        this.itemName = itemName;
        this.price = price;
        this.status = status;
        this.orderDate = orderDate;
        this.approveDate = approveDate;
        this.arrivalDate = arrivalDate;
    }

    public Order() {
    }

    // getters and setters
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(LocalDateTime approveDate) {
        this.approveDate = approveDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
