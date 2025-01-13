package com.example.RestaurantDailyMenu.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// Configuration class to enable Cross-Origin Resource Sharing (CORS) for the application

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // Method to configure CORS settings for the application
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Allows all endpoints to have CORS enabled
                .allowedOrigins("http://localhost:3000") // Allows requests from frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allows specific methods
                .allowedHeaders("*"); // Allows all headers
    }
}
