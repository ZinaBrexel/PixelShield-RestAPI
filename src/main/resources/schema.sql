DROP TABLE IF EXISTS `authorities` ;
DROP TABLE IF EXISTS `users` ;
CREATE TABLE users
(
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `address` varchar(250) NOT NULL,
    `phone` varchar(50) NOT NULL,
    `firstName` varchar(100) NOT NULL,
    `lastName` varchar(100) NOT NULL,
    `enabled` TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);
CREATE TABLE authorities
(
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);
