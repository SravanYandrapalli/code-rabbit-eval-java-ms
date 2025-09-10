package org.validator;

import org.springframework.util.StringUtils;

/**
 * Validation utilities for user inputs.
 */
public class UserValidator {

    public static boolean isValidUserId(String id) {
        return StringUtils.hasText(id) && id.length() <= 64;
    }
}


