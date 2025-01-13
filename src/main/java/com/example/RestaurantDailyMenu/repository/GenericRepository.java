package com.example.RestaurantDailyMenu.repository;

import com.example.RestaurantDailyMenu.model.BaseUser;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Generic repository class for handling user data.
 * This class is used by both user and restaurant.
 *
 * This class provides methods for:
 * - saving users of any type that extends BaseUser.
 * - finding a user by their username and type.
 *
 * The repository uses an in-memory list to store the users.
 *
 * @param <T> The type of user, which must extend from BaseUser.
 */
@Repository
public class GenericRepository<T extends BaseUser> {
    // Properties
    private final List<T> users = new ArrayList<>(); // In-memory list to store users

    // methods
    /**
     * Saves a user to the repository.
     *
     * @param user The user object to be saved.
     * @return The saved user object.
     */
    public T save(T user) {
        users.add(user);  // Assuming 'users' is a collection like a List<T>
        return user;  // Return the saved user object
    }

    /**
     * Finds a user by their username and class type.
     *
     * @param username The username of the user to search for.
     * @param type The class type of the user (e.g., User.class, Restaurant.class).
     * @return The user with the matching username and type, or null if not found.
     */
    public T findByUsername(String username, Class<T> type) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getClass().equals(type))
                .findFirst()
                .orElse(null);
    }
}

