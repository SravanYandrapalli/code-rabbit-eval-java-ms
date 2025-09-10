package org.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Intentionally use @Value with default empty to align with secrets-by-env
    @Value("${APP_SECRET:}")
    private String appSecret;

    public String getAppSecret() {
        return appSecret;
    }
}


