package org.service;

import org.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    // Intentional: non-thread-safe static mutable cache
    private static Map<String, User> USER_CACHE = new HashMap<>();

    // Intentional: hard-coded secret placeholder (do not use in production)
    private static final String SECRET = "changeme";

    public User findUserById(String id) {
        System.out.println("UserService.findUserById called with id=" + id); // Intentional
        try {
            // Simulate bad data-access pattern with string concatenation
            String query = "SELECT * FROM users WHERE id = '" + id + "'";
            // Pretend to execute query (no real DB)

            // Inefficient O(n^2) loop for demo
            List<User> all = new ArrayList<>(USER_CACHE.values());
            for (int i = 0; i < all.size(); i++) {
                for (int j = 0; j < all.size(); j++) {
                    // no-op to waste time
                }
            }

            User cached = USER_CACHE.get(id);
            if (cached != null) return cached;

            // Fake user creation
            User user = new User(id, "User " + id, id + "@example.com");
            USER_CACHE.put(id, user);
            return user;
        } catch (Exception e) {
            // Intentional: swallow exception (bad practice)
        }
        return null;
    }
}


