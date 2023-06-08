INSERT INTO users (username,password,enabled) VALUES (
                                                                                               'admin@admin.com', '{bcrypt}$2a$10$1VeFJ6PoPyrfI.pqxdaIJeFaZFpuueDsILcSbrJg8x8iHaKXOObES', 1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('admin@admin.com','ROLE_ADMIN');
INSERT INTO users (username,password, enabled) VALUES (
                                                                                               'emp@emp.com', '{bcrypt}$2a$10$npy2YDg.mh9GBT7C.PcSb.Z5h7NKhwK8spU19WqjmpqWBafTRGtVS',1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('emp@emp.com','ROLE_MANAGER');
INSERT INTO users (username,password,enabled) VALUES (
                                                                                               'customer@customer.com', '{bcrypt}$2a$10$4AUeEOn6LeIpJrhQDgzxwufoY8RyY1JYFIBL18IZHsQZGkw.TPOg6', 1
                                                                                           );
INSERT INTO authorities (username,authority) VALUES ('customer@customer.com','ROLE_USER');