package com.gamaza.rest4cep.config.constant;

/**
 * Constants for Configuration classes
 */
public final class ConfigurationConstants {

    // Mappers
    public static final String MAPPER_COMPONENT_MODEL = "spring";

    // Role names
    public static final String ROLE_DESIGN = "DESIGN";
    public static final String ROLE_RUNTIME = "RUNTIME";

    /**
     * Private constructor
     */
    private ConfigurationConstants() {
        throw new IllegalStateException("Can not instantiate ConfigurationConstants class.");
    }

}
