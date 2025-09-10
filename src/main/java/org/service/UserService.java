package org.service;

import org.configuration.AppConfig;
import org.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Business logic for working with {@link User} entities.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    // Thread-safe, instance-level cache to avoid static mutable state
    private final Map<String, User> userIdToUserCache = new ConcurrentHashMap<>();

    private final AppConfig appConfig;

    public UserService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    /**
     * Finds a user by identifier. Creates a simple placeholder user if not present in cache.
     *
     * @param id unique user identifier
     * @return user when found or created
     * @throws IllegalArgumentException when id is null or blank
     */
    public User findUserById(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must be provided");
        }

        // Access secret from configuration (never log the actual value)
        if (appConfig.getAppSecret() == null || appConfig.getAppSecret().isEmpty()) {
            log.debug("APP_SECRET is not set; proceeding with default behavior");
        }

        // Simple cache fetch or compute
        return userIdToUserCache.computeIfAbsent(id, key -> {
            log.info("Creating new user in cache for id={}", key);
            return new User(key, "User " + key, key + "@example.com");
        });
    }
}


