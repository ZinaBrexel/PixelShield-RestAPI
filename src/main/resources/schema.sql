DROP TABLE IF EXISTS `authorities` ;
DROP TABLE IF EXISTS `users` ;
CREATE TABLE users
(
    `username` varchar(100) NOT NULL,
    `password` varchar(100) NOT NULL,
    `enabled` TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (username)
);
CREATE TABLE authorities
(
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);
CREATE TABLE user_profiles (
                               username VARCHAR(100) NOT NULL,
                               first_name VARCHAR(255) NOT NULL,
                               last_name VARCHAR(255) NOT NULL,
                               address VARCHAR(255) NOT NULL,
                               phone VARCHAR(255) NOT NULL,
                               formula VARCHAR(255) ,
                               FOREIGN KEY (username) REFERENCES users(username)
);
