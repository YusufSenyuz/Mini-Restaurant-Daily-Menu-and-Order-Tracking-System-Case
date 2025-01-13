package com.example.RestaurantDailyMenu.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing an order in the system.
 * Contains order-related details such as ID, item name, username,
 * price, status, and important timestamps like order, approval, and arrival dates.
 */
public class OrderDTO {
    // Properties
    private Long id;
    private String itemName;
    private String username;
    private double price;
    private String status;
    private LocalDateTime orderDate;
    private LocalDateTime approveDate;
    private LocalDateTime arrivalDate;

    // Constructors
    public OrderDTO() {
    }
    public OrderDTO(Long id, String itemName, String username, double price, String status,
                    LocalDateTime orderDate, LocalDateTime approveDate, LocalDateTime arrivalDate) {
        this.id = id;
        this.itemName = itemName;
        this.username = username;
        this.price = price;
        this.status = status;
        this.orderDate = orderDate;
        this.approveDate = approveDate;
        this.arrivalDate = arrivalDate;
    }


    // Getters and Setters


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
