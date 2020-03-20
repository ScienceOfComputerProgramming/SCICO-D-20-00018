package com.gamaza.rest4cep.config.constant;

/**
 * Constants for Swagger classes
 */
public final class SwaggerConstants {

    // General constants
    public static final String SWAGGER_UI_URL = "/swagger-ui.html";

    // Group names
    public static final String SWAGGER_GROUP_NAME_DESIGN = "REST4CEP Design API";
    public static final String SWAGGER_GROUP_NAME_RUNTIME = "REST4CEP Runtime API";

    // API Info
    public static final String SWAGGER_API_INFO_TITLE = "REST4CEP (Design & Runtime)";
    public static final String SWAGGER_API_INFO_DESCRIPTION = "API REST for CEP Design, management and runtime";
    public static final String SWAGGER_API_INFO_VERSION = "1.0";

    /**
     * Private constructor
     */
    private SwaggerConstants() {
        throw new IllegalStateException("Can not instantiate SwaggerConstants class.");
    }

}
