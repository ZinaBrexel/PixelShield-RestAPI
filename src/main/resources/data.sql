INSERT INTO users (username,password, address, phone, firstName, lastName, enabled) VALUES (
                                                                                               'admin@admin.com', '{bcrypt}$2a$10$1VeFJ6PoPyrfI.pqxdaIJeFaZFpuueDsILcSbrJg8x8iHaKXOObES','10 rue du stade 75001 Paris', '07.20.56.89.65', 'Kitana', 'Mortal', 1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('admin@admin.com','ROLE_ADMIN');
INSERT INTO users (username,password, address, phone, firstName, lastName, enabled) VALUES (
                                                                                               'emp@emp.com', '{bcrypt}$2a$10$npy2YDg.mh9GBT7C.PcSb.Z5h7NKhwK8spU19WqjmpqWBafTRGtVS','25 rue du chateau 75001 Paris', '07.84.25.57.69', 'Paul', 'Jackson', 1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('emp@emp.com','ROLE_MANAGER');
INSERT INTO users (username,password, address, phone, firstName, lastName, enabled) VALUES (
                                                                                               'customer@customer.com', '{bcrypt}$2a$10$4AUeEOn6LeIpJrhQDgzxwufoY8RyY1JYFIBL18IZHsQZGkw.TPOg6','36 boulevard du grand chêne 29200 Brest', '07.92.34.75.65', 'Paul', 'Jackson', 1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('customer@customer.com','ROLE_USER');