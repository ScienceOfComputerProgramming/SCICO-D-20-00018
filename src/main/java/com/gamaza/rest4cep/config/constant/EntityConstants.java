package com.gamaza.rest4cep.config.constant;

/**
 * Constants for Entity classes
 */
public final class EntityConstants {

    // Table names
    public static final String TABLE_EVENT_TYPE = "event_type";
    public static final String TABLE_EVENT_PATTERN = "event_pattern";
    public static final String TABLE_EVENT_TYPE_EVENT_PATTERN = "event_type_event_pattern";
    public static final String TABLE_LDAP_USER = "ldap_user";
    public static final String TABLE_LDAP_ROLE = "ldap_role";
    public static final String TABLE_LDAP_USER_ROLE = "ldap_user_role";

    // Join columns
    public static final String JOIN_COLUMN_EVENT_TYPE_ID = "event_type_id";
    public static final String JOIN_COLUMN_EVENT_PATTERN_ID = "event_pattern_id";
    public static final String JOIN_COLUMN_USER_ID = "user_id";
    public static final String JOIN_COLUMN_ROLE_ID = "role_id";

    // Relation fields
    public static final String RELATION_FIELD_EVENT_PATTERNS = "eventPatterns";
    public static final String RELATION_FIELD_EVENT_TYPES = "eventTypes";
    public static final String RELATION_FIELD_ROLES = "roles";

    // Field names
    public static final String AUDITABLE_FIELD_CREATED_DATE = "created_date";
    public static final String AUDITABLE_FIELD_LAST_MODIFIED_DATE = "last_modified_date";

    // Default
    public static final String DEFAULT_DESCRIPTION = "No description";

    // Collection names
    public static final String COLLECTION_EVENT = "event";
    public static final String COLLECTION_INCORRECT_EVENT_PATTERN = "incorrect_event_pattern";
    public static final String COLLECTION_INCORRECT_EVENT_TYPE = "incorrect_event_type";
    public static final String COLLECTION_COMPLEX_EVENTS_DETECTED = "complex_events_detected";

    /**
     * Private constructor
     */
    private EntityConstants() {
        throw new IllegalStateException("Can not instantiate EntityConstants class.");
    }

}
