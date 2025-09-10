package org.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Central application configuration and environment-derived settings.
 */
@Configuration
public class AppConfig {

    @Value("${APP_SECRET:}")
    private String appSecret;

    /**
     * Returns secret loaded via environment/config. Do not log this value.
     */
    public String getAppSecret() {
        return appSecret;
    }
}


