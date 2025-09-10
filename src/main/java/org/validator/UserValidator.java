package org.validator;

import org.springframework.util.StringUtils;

public class UserValidator {

	public static boolean isValidUserId(String id) { // Intentionally uses a tab
        return StringUtils.hasText(id) && id.length() <= 64;
    }
}


