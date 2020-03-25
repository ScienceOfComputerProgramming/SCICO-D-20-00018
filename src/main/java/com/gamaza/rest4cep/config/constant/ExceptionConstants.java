package com.gamaza.rest4cep.config.constant;

/**
 * Constants for Exception classes
 */
public final class ExceptionConstants {

    // Messages
    public static final String MESSAGE_NOT_FOUND_EXCEPTION = "The object %s with %s not found";
    public static final String MESSAGE_ALREADY_EXISTS_EXCEPTION = "The object %s with %s already exists";
    public static final String MESSAGE_UPDATE_EXCEPTION = "The object %s with %s can not be updated because [readyToDeploy=%s]";
    public static final String MESSAGE_UPDATE_STATUS_EXCEPTION = "The object %s with %s can not be set ready to deploy because: %s";
    public static final String MESSAGE_UPDATE_STATUS_INCONSISTENT_EXCEPTION = "The object %s with %s is %s ready to deploy";
    public static final String MESSAGE_UPDATE_STATUS_DEPLOYED_EXCEPTION = "The object %s with %s is %s deployed";
    public static final String MESSAGE_LINK_EXCEPTION = "The object %s with %s can not be %s with the object %s with %s because they are %s linked";

    // Comments
    public static final String COMMENTS_UPDATE_STATUS_EMPTY_EVENT_TYPES = "It is not linked with any %s";
    public static final String COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_EVENT_TYPE = "It has %s %s objects not deployed";
    public static final String COMMENTS_UPDATE_STATUS_NOT_DEPLOYED_PATTERN_CONTENT = "It has %s %s objects not present in his content query";

    // Format constants
    public static final String FORMAT_ID_TEXT = "[id=%s]";
    public static final String FORMAT_NAME_TEXT = "[name=%s]";

    // Operation strings
    public static final String OPERATION_LINK = "link";
    public static final String OPERATION_UNLINK = "unlink";
    public static final String OPERATION_WORD_ALREADY = "already";
    public static final String OPERATION_WORD_NOT = "not";

    // Object types
    public static final String OBJECT_EVENT_TYPE = "EVENT TYPE";
    public static final String OBJECT_EVENT_PATTERN = "EVENT PATTERN";
    public static final String OBJECT_EVENT = "EVENT";
    public static final String OBJECT_INCORECT_EVENT_PATTERN = "INCORRECT EVENT PATTERN";
    public static final String OBJECT_COMPLEX_EVENT_DETECTED = "COMPLEX EVENT DETECTED";

    /**
     * Private constructor
     */
    private ExceptionConstants() {
        throw new IllegalStateException("Can not instantiate ExceptionConstants class.");
    }

}
