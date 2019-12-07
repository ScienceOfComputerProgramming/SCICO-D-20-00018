--
-- DROP Structures
--
REVOKE ALL PRIVILEGES, GRANT OPTION FROM 'rest4cep'@'%';
DROP USER IF EXISTS 'rest4cep'@'%';
DROP DATABASE IF EXISTS rest4cep;

--
-- Database creation
--
CREATE DATABASE rest4cep;
USE rest4cep;

--
-- User creation
--
CREATE USER 'rest4cep'@'%' IDENTIFIED BY 'REST4CEP.2020$';
GRANT ALL PRIVILEGES ON rest4cep.* TO 'rest4cep'@'%';

--
-- Structure esper_epl_event_patterns
--
CREATE TABLE `esper_epl_event_patterns` (
  `id` int(4) NOT NULL,
  `name` varchar(64) NOT NULL,
  `content` text NOT NULL,
  `is_deployed` tinyint(1) NOT NULL DEFAULT '0',
  `is_in_esper` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts esper_epl_event_patterns
--
INSERT INTO `esper_epl_event_patterns` (`id`, `name`, `content`, `is_deployed`, `is_in_esper`) VALUES
(1, 'AirOzone2', '@Name("AirOzone2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.055 and O3 <= 0.070))].win:time(8 hour)', 1, 1),
(2, 'AirOzone3-1', '@Name("AirOzone3-1") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.125 and O3 <= 0.164))].win:time(1 hour)', 0, 0),
(3, 'AirOzone3-8', '@Name("AirOzone3-8") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.071 and O3 <= 0.085))].win:time(8 hour)', 0, 0),
(4, 'AirOzone4-1', '@Name("AirOzone4-1") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.165 and O3 <= 0.204))].win:time(1 hour)', 1, 1),
(5, 'AirOzone4-8', '@Name("AirOzone4-8") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.086 and O3 <= 0.105))].win:time(8 hour)', 0, 0),
(6, 'AirOzone5-1', '@Name("AirOzone5-1") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.205 and O3 <= 0.404))].win:time(1 hour)', 0, 0),
(7, 'AirOzone5-8', '@Name("AirOzone5-8") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.106 and O3 <= 0.200))].win:time(8 hour)', 0, 0),
(8, 'AirOzone6-1', '@Name("AirOzone6-1") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((O3 >= 0.405 and O3 <= 0.604))].win:time(1 hour)', 0, 0),
(9, 'AirPM25-2', '@Name("AirPM25-2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 12.1 and PM25 <= 35.4))].win:time(24 hour)', 0, 0),
(10, 'AirPM25-3', '@Name("AirPM25-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 35.5 and PM25 <= 55.4))].win:time(24 hour)', 0, 0),
(11, 'AirPM25-4', '@Name("AirPM25-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 55.5 and PM25 <= 150.4))].win:time(24 hour)', 0, 0),
(12, 'AirPM25-5', '@Name("AirPM25-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 150.5 and PM25 <= 250.4))].win:time(24 hour)', 0, 0),
(13, 'AirPM25-6', '@Name("AirPM25-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM25 >= 250.5 and PM25 <= 500.4))].win:time(24 hour)', 0, 0),
(14, 'AirPM10-2', '@Name("AirPM10-2") insert into AirSensors select 	2 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 55 and PM10 <= 154))].win:time(24 hour)', 0, 0),
(15, 'AirPM10-3', '@Name("AirPM10-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 155 and PM10 <= 254))].win:time(24 hour)', 0, 0),
(16, 'AirPM10-4', '@Name("AirPM10-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 255 and PM10 <= 354))].win:time(24 hour)', 0, 0),
(17, 'AirPM10-5', '@Name("AirPM10-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 355 and PM10 <= 424))].win:time(24 hour)', 0, 0),
(18, 'AirPM10-6', '@Name("AirPM10-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((PM10 >= 425 and PM10 <= 604))].win:time(24 hour)', 0, 0),
(19, 'AirCO-3', '@Name("AirCO-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 9.5 and CO <= 12.4))].win:time(8 hour)', 0, 0),
(20, 'AirCO-4', '@Name("AirCO-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 12.5 and CO <= 15.4))].win:time(8 hour)', 1, 1),
(21, 'AirCO-5', '@Name("AirCO-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 15.5 and CO <= 30.4))].win:time(8 hour)', 0, 0),
(22, 'AirCO-6', '@Name("AirCO-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((CO >= 30.5 and CO <= 50.4))].win:time(8 hour)', 0, 0),
(23, 'AirSO2-3', '@Name("AirSO2-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 76 and SO2 <= 185))].win:time(1 hour)', 0, 0),
(24, 'AirSO2-4', '@Name("AirSO2-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 186 and SO2 <= 304))].win:time(1 hour)', 0, 0),
(25, 'AirSO2-5', '@Name("AirSO2-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 305 and SO2 <= 604))].win:time(24 hour)', 0, 0),
(26, 'AirSO2-6', '@Name("AirSO2-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((SO2 >= 605 and SO2 <= 1004))].win:time(24 hour)', 0, 0),
(27, 'AirNO2-3', '@Name("AirNO2-3") insert into AirSensors select 	3 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 101 and NO2 <= 360))].win:time(1 hour)', 0, 0),
(28, 'AirNO2-4', '@Name("AirNO2-4") insert into AirSensors select 	4 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 361 and NO2 <= 649))].win:time(1 hour)', 0, 0),
(29, 'AirNO2-5', '@Name("AirNO2-5") insert into AirSensors select 	5 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 650 and NO2 <= 1249))].win:time(1 hour)', 1, 1),
(30, 'AirNO2-6', '@Name("AirNO2-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((NO2 >= 1250 and NO2 <= 2049))].win:time(1 hour)', 0, 0),
(31, 'AirT-1', '@Name("AirT-1") insert into AirSensors select 	1 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((T <= 10))].win:time(8 hour)', 0, 0),
(32, 'AirT-6', '@Name("AirT-6") insert into AirSensors select 	6 as level, "Air" as property, 	e.id as id, e.name as name, 	e.O3 as O3, e.PM25 as PM25, e.PM10 as PM10, e.CO as CO, e.SO2 as SO2, e.NO2 as NO2, e.T as T from 	pattern [every-distinct (e.id)e = AirSensors((T >= 40))].win:time(8 hour)', 0, 0),
(33, 'WaterPH-3', '@Name("WaterPH-3") insert into WaterSensors select 	3 as level, "Water" as property, 	e.id as id, e.name as name, 	e.PH as PH, e.Cl as Cl from 	pattern [every-distinct (e.id)e =  WaterSensors((PH > 7))].win:time(1 hour)', 1, 1),
(34, 'WaterCl-3', '@Name("WaterCl-3") insert into WaterSensors select 	3 as level, "Water" as property, 	e.id as id, e.name as name, 	e.PH as PH, e.Cl as Cl from 	pattern [every-distinct (e.id)e =  WaterSensors((Cl > 2))].win:time(1 hour)', 1, 1),
(35, 'MQTT-1', '@Name("MQTT-1") insert into MQTT select 	1 as level, "Water" as property, 	e.name as name, 	e.PH as PH, e.Cl as Cl from 	pattern [every-distinct (e.name)e = MQTT((Cl > 2))].win:time(1 hour)', 0, 0),
(36, 'JuntaAndalucia-6', '@Name("JuntaAndalucia-6") insert into JuntaAndalucia select 	6 as level, "Air" as property, 	e.analizador as analizador, e.valor as valor, e.unidad as unidad from 	pattern [every-distinct (e.analizador)e = JuntaAndalucia((valor >= "40.5" and valor <= "60.4"))].win:time(1 hour)', 0, 0);

-- --------------------------------------------------------

--
-- Structures esper_event_types
--
CREATE TABLE `esper_event_types` (
  `id` int(4) NOT NULL,
  `channel_id` int(16) NOT NULL,
  `name` varchar(64) NOT NULL,
  `description` text NOT NULL,
  `is_enabled` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts esper_event_types
--
INSERT INTO `esper_event_types` (`id`, `channel_id`, `name`, `description`, `is_enabled`) VALUES
(1, 170892, 'AirSensors', 'Sensors that are in Gamaza\'s home.', 0),
(2, 545022, 'WaterSensors', 'Sensors that are in Gamaza\'s beach.', 0);

-- --------------------------------------------------------

--
-- Structure esper_event_type_epl_patterns
--
CREATE TABLE `esper_event_type_epl_patterns` (
  `id` int(4) NOT NULL,
  `event_type_id` int(4) NOT NULL,
  `epl_pattern_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts esper_event_type_epl_patterns
--
INSERT INTO `esper_event_type_epl_patterns` (`id`, `event_type_id`, `epl_pattern_id`) VALUES
(101, 1, 1),
(102, 1, 2),
(103, 1, 3),
(104, 1, 4),
(105, 1, 5),
(106, 1, 6),
(107, 1, 7),
(108, 1, 8),
(109, 1, 9),
(110, 1, 10),
(111, 1, 11),
(112, 1, 12),
(113, 1, 13),
(114, 1, 14),
(115, 1, 15),
(116, 1, 16),
(117, 1, 17),
(118, 1, 18),
(119, 1, 19),
(120, 1, 20),
(121, 1, 21),
(122, 1, 22),
(123, 1, 23),
(124, 1, 24),
(125, 1, 25),
(126, 1, 26),
(127, 1, 27),
(128, 1, 28),
(129, 1, 29),
(130, 1, 30),
(131, 1, 31),
(132, 1, 32),
(133, 2, 33),
(134, 2, 34);

-- --------------------------------------------------------

--
-- Structure ldap_roles
--
CREATE TABLE `ldap_roles` (
  `id` int(4) NOT NULL,
  `role` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts ldap_roles
--
INSERT INTO `ldap_roles` (`id`, `role`) VALUES
(1, 'ROLE_DESIGN'),
(2, 'ROLE_RUNTIME');

-- --------------------------------------------------------

--
-- Structure ldap_users
--
CREATE TABLE `ldap_users` (
  `id` int(4) NOT NULL,
  `username` varchar(64) NOT NULL,
  `password` varchar(128) NOT NULL,
  `is_enabled` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts ldap_users
--
INSERT INTO `ldap_users` (`id`, `username`, `password`, `is_enabled`) VALUES
(1, 'design', '$2a$10$yS6KOcl8BRcERw..AKOJF.OBbDeeL1cY/wgThZ9wL2rBnFQN6fbs.', 1),
(2, 'runtime', '$2a$10$yS6KOcl8BRcERw..AKOJF.OBbDeeL1cY/wgThZ9wL2rBnFQN6fbs.', 1);

-- --------------------------------------------------------

--
-- Structure ldap_user_roles
--
CREATE TABLE `ldap_user_roles` (
  `id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `role_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserts ldap_user_roles
--
INSERT INTO `ldap_user_roles` (`id`, `user_id`, `role_id`) VALUES
(1, 1, 1),
(2, 2, 2);

--
-- Indexes esper_epl_event_patterns
--
ALTER TABLE `esper_epl_event_patterns`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes esper_event_types
--
ALTER TABLE `esper_event_types`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `channel_id` (`channel_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes esper_event_type_epl_patterns
--
ALTER TABLE `esper_event_type_epl_patterns`
  ADD PRIMARY KEY (`id`,`event_type_id`,`epl_pattern_id`),
  ADD KEY `event_type_id` (`event_type_id`),
  ADD KEY `epl_pattern_id` (`epl_pattern_id`);

--
-- Indexes ldap_roles
--
ALTER TABLE `ldap_roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`role`);

--
-- Indexes ldap_users
--
ALTER TABLE `ldap_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes ldap_user_roles
--
ALTER TABLE `ldap_user_roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_index` (`user_id`,`role_id`),
  ADD KEY `ldap_ur_ibfk_2` (`role_id`);

--
-- AUTO_INCREMENT esper_epl_event_patterns
--
ALTER TABLE `esper_epl_event_patterns`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT desper_event_types
--
ALTER TABLE `esper_event_types`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT esper_event_type_epl_patterns
--
ALTER TABLE `esper_event_type_epl_patterns`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;
--
-- AUTO_INCREMENT ldap_roles
--
ALTER TABLE `ldap_roles`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT ldap_users
--
ALTER TABLE `ldap_users`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT ldap_user_roles
--
ALTER TABLE `ldap_user_roles`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Filters esper_event_type_epl_patterns
--
ALTER TABLE `esper_event_type_epl_patterns`
  ADD CONSTRAINT `esper_event_type_epl_patterns_ibfk_1` FOREIGN KEY (`event_type_id`) REFERENCES `esper_event_types` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `esper_event_type_epl_patterns_ibfk_2` FOREIGN KEY (`epl_pattern_id`) REFERENCES `esper_epl_event_patterns` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filters ldap_user_roles
--
ALTER TABLE `ldap_user_roles`
  ADD CONSTRAINT `ldap_user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `ldap_users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ldap_user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `ldap_roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;