package com.example.RestaurantDailyMenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Main application class that serves as the entry point for the Restaurant application.
 * This class initializes and starts the Spring Boot application.
 * The DataSourceAutoConfiguration is excluded since we are not using a database in this application.
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class RestaurantApplication {

    /**
     * The main method to run the Spring Boot application.
     * It starts the application and outputs a message indicating the application is running.
     *
     * @param args command-line arguments (unused).
     */
    public static void main(String[] args) {
        SpringApplication.run(RestaurantApplication.class, args);
        System.out.println("Restaurant Application is running on http://localhost:8080");
    }
}
