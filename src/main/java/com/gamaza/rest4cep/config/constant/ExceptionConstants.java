package com.gamaza.rest4cep.config.constant;

/**
 * Constants for Exception classes
 */
public final class ExceptionConstants {

    // Messages
    public static final String MESSAGE_NOT_FOUND_EXCEPTION = "The object %s with %s not found";
    public static final String MESSAGE_UPDATE_EVENT_TYPE_EXCEPTION = "The object %s with %s has to be disabled before update";
    public static final String MESSAGE_UPDATE_STATUS_EVENT_TYPE_EXCEPTION = "The object %s with %s is %s enabled";
    public static final String MESSAGE_UPDATE_EVENT_PATTERN_EXCEPTION = "The object %s with %s can not be updated because it is currently deployed";
    public static final String MESSAGE_UPDATE_STATUS_EVENT_PATTERN_EXCEPTION = "The object %s with %s can not be set ready to deploy because: %s";
    public static final String MESSAGE_UPDATE_STATUS_INCONSISTENT_EVENT_PATTERN_EXCEPTION = "The object %s with %s is %s ready to deploy";
    public static final String MESSAGE_UPDATE_STATUS_DEPLOYED_EVENT_PATTERN_EXCEPTION = "The object %s with %s is %s deployed";
    public static final String MESSAGE_LINK_EXCEPTION = "The object %s with %s can not be %s with the object %s with %s because they are %s linked";

    // Comments
    public static final String COMMENTS_UPDATE_STATUS_EMPTY_EVENT_TYPES = "It is not linked with any %s";
    public static final String COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_EVENT_TYPE = "It has %s %s objects not enabled";
    public static final String COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_PATTERN_CONTENT = "It has %s %s objects not present in his content query";

    // Operation strings
    public static final String OPERATION_LINK = "link";
    public static final String OPERATION_UNLINK = "unlink";
    public static final String OPERATION_WORD_ALREADY = "already";
    public static final String OPERATION_WORD_NOT = "not";

    // Object types
    public static final String OBJECT_EVENT_TYPE = "EVENT TYPE";
    public static final String OBJECT_EVENT_PATTERN = "EVENT PATTERN";
    public static final String OBJECT_COMPLEX_EVENT_DETECTED = "COMPLEX EVENT DETECTED";

    /**
     * Private constructor
     */
    private ExceptionConstants() {
        throw new IllegalStateException("Can not instantiate ExceptionConstants class.");
    }

}
