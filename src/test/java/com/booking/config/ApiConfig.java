package com.booking.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ApiConfig {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream input = ApiConfig.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("config.properties not found");
            }
            PROPERTIES.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }
    }

    private ApiConfig() {
    }

    public static String baseUrl() {
        return PROPERTIES.getProperty("base.url");
    }
}