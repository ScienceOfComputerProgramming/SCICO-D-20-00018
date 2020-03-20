package com.gamaza.rest4cep.config.constant;

import com.google.common.collect.ImmutableList;

import java.util.Collections;
import java.util.List;

/**
 * Constants for CORS Configuration
 */
public final class CorsConstants {

    /**
     * General
     */
    public static final List<String> ALLOWED_ORIGINS = Collections.singletonList("*");
    public static final List<String> ALLOWED_METHODS = ImmutableList.of("GET", "POST", "PUT", "DELETE", "OPTIONS");
    public static final List<String> ALLOWED_HEADERS = ImmutableList.of("authorization", "content-type", "x-auth-token");
    public static final List<String> EXPOSED_HEADERS = Collections.singletonList("x-auth-token");
    public static final String CORS_REGISTER = "/**";

    /**
     * Private constructor
     */
    private CorsConstants() {
        throw new IllegalStateException("Can not instantiate CorsConstants class.");
    }
}
