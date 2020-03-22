-- Previous deletions in schema
DROP DATABASE IF EXISTS rest4cep;

-- Database creation
CREATE DATABASE rest4cep;
USE rest4cep;

-- --------------------------------------------------------

-- Structure ldap_user
CREATE TABLE ldap_user
(
    id                 INT(4)       NOT NULL AUTO_INCREMENT,
    username           VARCHAR(64)  NOT NULL,
    password           VARCHAR(128) NOT NULL,
    is_enabled         BOOLEAN      NOT NULL,
    created_date       TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_ldap_user PRIMARY KEY (id),
    CONSTRAINT uindex_ldap_user_username UNIQUE (username)
);

-- Structure ldap_role
CREATE TABLE ldap_role
(
    id                 INT(4)      NOT NULL AUTO_INCREMENT,
    name               VARCHAR(64) NOT NULL,
    created_date       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_ldap_role PRIMARY KEY (id),
    CONSTRAINT uindex_ldap_role_role UNIQUE (name)
);

-- Structure ldap_user_role
CREATE TABLE ldap_user_role
(
    id                 INT(4)    NOT NULL AUTO_INCREMENT,
    user_id            INT(4)    NOT NULL,
    role_id            INT(4)    NOT NULL,
    created_date       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_ldap_user_role PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES ldap_user (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES ldap_role (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    KEY (user_id, role_id)
);

-- --------------------------------------------------------

-- Structure event_type
CREATE TABLE event_type
(
    id                 INT(4)      NOT NULL AUTO_INCREMENT,
    channel_id         INT(16)     NOT NULL,
    name               VARCHAR(64) NOT NULL,
    description        TEXT        NOT NULL,
    is_enabled         BOOLEAN     NOT NULL DEFAULT 0,
    created_date       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_event_type PRIMARY KEY (id),
    CONSTRAINT uindex_channel_id_event_type UNIQUE (channel_id),
    CONSTRAINT uindex_name_event_type UNIQUE (name)
);

-- --------------------------------------------------------

-- Structure event_pattern
CREATE TABLE event_pattern
(
    id                 INT(4)      NOT NULL AUTO_INCREMENT,
    name               VARCHAR(64) NOT NULL,
    content            TEXT        NOT NULL,
    is_ready_to_deploy BOOLEAN     NOT NULL DEFAULT 0,
    is_deployed        BOOLEAN     NOT NULL DEFAULT 0,
    created_date       TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_event_pattern PRIMARY KEY (id),
    CONSTRAINT uindex_name_event_pattern UNIQUE (name)
);

-- --------------------------------------------------------

-- Structure event_type_event_pattern

CREATE TABLE event_type_event_pattern
(
    id                 INT(4)    NOT NULL AUTO_INCREMENT,
    event_type_id      INT(4)    NOT NULL,
    event_pattern_id   INT(4)    NOT NULL,
    created_date       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT pk_event_type_event_pattern PRIMARY KEY (id),
    CONSTRAINT uindex_event_type_event_pattern UNIQUE (event_type_id, event_pattern_id),
    CONSTRAINT fk_event_type_id FOREIGN KEY (event_type_id)
        REFERENCES event_type (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_event_pattern_id FOREIGN KEY (event_pattern_id)
        REFERENCES event_pattern (id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    KEY (event_type_id, event_pattern_id)
);