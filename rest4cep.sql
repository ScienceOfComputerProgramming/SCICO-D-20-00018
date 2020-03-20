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

-- Inserts ldap_user
INSERT INTO ldap_user (username, password, is_enabled)
VALUES ('design', '$2a$10$yS6KOcl8BRcERw..AKOJF.OBbDeeL1cY/wgThZ9wL2rBnFQN6fbs.', 1),
       ('runtime', '$2a$10$yS6KOcl8BRcERw..AKOJF.OBbDeeL1cY/wgThZ9wL2rBnFQN6fbs.', 1);

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

-- Inserts ldap_role
INSERT INTO ldap_role (name)
VALUES ('ROLE_DESIGN'),
       ('ROLE_RUNTIME');

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

-- Inserts ldap_user_role
INSERT INTO ldap_user_role (user_id, role_id)
VALUES ((SELECT id FROM ldap_user WHERE username = 'design'), (SELECT id FROM ldap_role WHERE name = 'ROLE_DESIGN')),
       ((SELECT id FROM ldap_user WHERE username = 'runtime'), (SELECT id FROM ldap_role WHERE name = 'ROLE_RUNTIME'));

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

-- Inserts event_type
INSERT INTO event_type (channel_id, name, description, is_enabled)
VALUES (170892, 'AirSensors', 'Sensors for AIR Quality', 0);

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

-- Inserts event_pattern
INSERT INTO event_pattern (name, content, is_ready_to_deploy, is_deployed)
VALUES ('AirOzone2',
        '@Name("AirOzone2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.055 and O3 <= 0.070))].win:time(8 hour)',
        0, 0),
       ('AirOzone3-1',
        '@Name("AirOzone3-1") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.125 and O3 <= 0.164))].win:time(1 hour)',
        0, 0),
       ('AirOzone3-8',
        '@Name("AirOzone3-8") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.071 and O3 <= 0.085))].win:time(8 hour)',
        0, 0),
       ('AirOzone4-1',
        '@Name("AirOzone4-1") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.165 and O3 <= 0.204))].win:time(1 hour)',
        0, 0),
       ('AirOzone4-8',
        '@Name("AirOzone4-8") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.086 and O3 <= 0.105))].win:time(8 hour)',
        0, 0),
       ('AirOzone5-1',
        '@Name("AirOzone5-1") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.205 and O3 <= 0.404))].win:time(1 hour)',
        0, 0),
       ('AirOzone5-8',
        '@Name("AirOzone5-8") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.106 and O3 <= 0.200))].win:time(8 hour)',
        0, 0),
       ('AirOzone6-1',
        '@Name("AirOzone6-1") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.405 and O3 <= 0.604))].win:time(1 hour)',
        0, 0),
       ('AirPM25-2',
        '@Name("AirPM25-2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 12.1 and PM25 <= 35.4))].win:time(24 hour)',
        0, 0),
       ('AirPM25-3',
        '@Name("AirPM25-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 35.5 and PM25 <= 55.4))].win:time(24 hour)',
        0, 0),
       ('AirPM25-4',
        '@Name("AirPM25-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 55.5 and PM25 <= 150.4))].win:time(24 hour)',
        0, 0),
       ('AirPM25-5',
        '@Name("AirPM25-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 150.5 and PM25 <= 250.4))].win:time(24 hour)',
        0, 0),
       ('AirPM25-6',
        '@Name("AirPM25-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 250.5 and PM25 <= 500.4))].win:time(24 hour)',
        0, 0),
       ('AirPM10-2',
        '@Name("AirPM10-2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 55 and PM10 <= 154))].win:time(24 hour)',
        0, 0),
       ('AirPM10-3',
        '@Name("AirPM10-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 155 and PM10 <= 254))].win:time(24 hour)',
        0, 0),
       ('AirPM10-4',
        '@Name("AirPM10-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 255 and PM10 <= 354))].win:time(24 hour)',
        0, 0),
       ('AirPM10-5',
        '@Name("AirPM10-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 355 and PM10 <= 424))].win:time(24 hour)',
        0, 0),
       ('AirPM10-6',
        '@Name("AirPM10-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 425 and PM10 <= 604))].win:time(24 hour)',
        0, 0),
       ('AirCO-3',
        '@Name("AirCO-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 9.5 and CO <= 12.4))].win:time(8 hour)',
        0, 0),
       ('AirCO-4',
        '@Name("AirCO-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 12.5 and CO <= 15.4))].win:time(8 hour)',
        0, 0),
       ('AirCO-5',
        '@Name("AirCO-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 15.5 and CO <= 30.4))].win:time(8 hour)',
        0, 0),
       ('AirCO-6',
        '@Name("AirCO-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 30.5 and CO <= 50.4))].win:time(8 hour)',
        0, 0),
       ('AirSO2-3',
        '@Name("AirSO2-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 76 and SO2 <= 185))].win:time(1 hour)',
        0, 0),
       ('AirSO2-4',
        '@Name("AirSO2-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 186 and SO2 <= 304))].win:time(1 hour)',
        0, 0),
       ('AirSO2-5',
        '@Name("AirSO2-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 305 and SO2 <= 604))].win:time(24 hour)',
        0, 0),
       ('AirSO2-6',
        '@Name("AirSO2-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 605 and SO2 <= 1004))].win:time(24 hour)',
        0, 0),
       ('AirNO2-3',
        '@Name("AirNO2-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 101 and NO2 <= 360))].win:time(1 hour)',
        0, 0),
       ('AirNO2-4',
        '@Name("AirNO2-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 361 and NO2 <= 649))].win:time(1 hour)',
        0, 0),
       ('AirNO2-5',
        '@Name("AirNO2-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 650 and NO2 <= 1249))].win:time(1 hour)',
        0, 0),
       ('AirNO2-6',
        '@Name("AirNO2-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 1250 and NO2 <= 2049))].win:time(1 hour)',
        0, 0),
       ('AirT-1',
        '@Name("AirT-1") insert into AirSensors select 	1 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((T <= 10))].win:time(8 hour)',
        0, 0),
       ('AirT-6',
        '@Name("AirT-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((T >= 40))].win:time(8 hour)',
        0, 0);

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

-- Inserts event_type_event_pattern
INSERT INTO event_type_event_pattern (event_type_id, event_pattern_id)
VALUES ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirCO-3')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirCO-4')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirCO-5')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirCO-6')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirNO2-3')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirNO2-4')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirNO2-5')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirNO2-6')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone2')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone3-1')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone3-8')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone4-1')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone4-8')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone5-1')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone5-8')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirOzone6-1')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM10-2')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM10-3')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM10-4')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM10-5')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM10-6')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM25-2')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM25-3')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM25-4')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM25-5')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirPM25-6')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirSO2-3')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirSO2-4')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirSO2-5')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirSO2-6')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirT-1')),
       ((SELECT id FROM event_type WHERE channel_id = 170892), (SELECT id from event_pattern WHERE name = 'AirT-6'));