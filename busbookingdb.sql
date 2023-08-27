CREATE TABLE `user` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50),
  `phone_number` char(10),
  `address` varchar(200),
  `email` varchar(50) UNIQUE,
  `active` boolean DEFAULT True,
  `password` varchar(100),
  `avatar` varchar(100),
  `role_id` int NOT NULL
);

CREATE TABLE `role` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `role_name` varchar(30)
);

CREATE TABLE `feedback` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `subject_name` varchar(200),
  `content` text,
  `stars` int NOT NULL,
  `state` int,
  `created_at` datetime,
  `updated_at` datetime,
  `ticket_id` int
);

CREATE TABLE `bus_type` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(30),
  `type_cost` float DEFAULT 1
);

CREATE TABLE `route` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50),
  `origin` int,
  `destination` int,
  `duration` time,
  `basic_price` float NOT NULL
);

CREATE TABLE `station` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `location` varchar(250),
  `province` varchar(50)
);

CREATE TABLE `bus` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `plate` varchar(12),
  `number_of_seats` int,
  `bus_type_id` int
);

CREATE TABLE `trip` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `set_off` datetime,
  `holiday_cost` float DEFAULT 1,
  `state` int,
  `driver_id` int,
  `bus_id` int,
  `route_id` int
);

CREATE TABLE `seat` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `seat_row_pos` int,
  `seat_col_pos` int,
  `bus_id` int
);

CREATE TABLE `bill` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `created_at` datetime,
  `state` int,
  `moneyPaid` double,
  `user_id` int,
  `bill_type_id` int
);

CREATE TABLE `offline_bill` (
  `bill_id` int UNIQUE NOT NULL,
  `client_name` varchar(50),
  `client_phone_number` char(10)
);

CREATE TABLE `ticket` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `bill_detail_id` int UNIQUE,
  `is_available` boolean,
  `price` double,
  `trip_id` int,
  `seat_id` int,
  `bill_id` int
);

CREATE TABLE `ticket_printed` (
  `ticket_id` int UNIQUE,
  `printed_time` datetime,
  `staff_id` int
);

ALTER TABLE `user` ADD FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

ALTER TABLE `offline_bill` ADD FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`);

ALTER TABLE `bus` ADD FOREIGN KEY (`bus_type_id`) REFERENCES `bus_type` (`id`);

ALTER TABLE `feedback` ADD FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`);

ALTER TABLE `route` ADD FOREIGN KEY (`destination`) REFERENCES `station` (`id`);

ALTER TABLE `route` ADD FOREIGN KEY (`origin`) REFERENCES `station` (`id`);

ALTER TABLE `trip` ADD FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`);

ALTER TABLE `trip` ADD FOREIGN KEY (`route_id`) REFERENCES `route` (`id`);

ALTER TABLE `trip` ADD FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`);

ALTER TABLE `seat` ADD FOREIGN KEY (`bus_id`) REFERENCES `bus` (`id`);

ALTER TABLE `ticket` ADD FOREIGN KEY (`trip_id`) REFERENCES `trip` (`id`);

ALTER TABLE `ticket` ADD FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`);

ALTER TABLE `bill` ADD FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

ALTER TABLE `ticket` ADD FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`);

ALTER TABLE `ticket_printed` ADD FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`);

ALTER TABLE `ticket_printed` ADD FOREIGN KEY (`staff_id`) REFERENCES `user` (`id`);
