package com.example.RestaurantDailyMenu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HomeController serves the initial landing page for the application.
 * This controller provides a simple response to indicate that the application is running.
 * It does not perform any checks or serve the frontend view directly.
 */
@RestController
public class HomeController {
    // Mapping the root URL to serve a home page
    @GetMapping("/")
    public String home() {
        return "App is running";
    }
}
