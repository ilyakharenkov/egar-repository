INSERT INTO role(name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO client(first_name, surname)
VALUES ('First Admin', 'Surname Admin'), ('Vasiliy', 'V'), ('Igor', 'I');

INSERT INTO user_security(password, username, client_id)
VALUES ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'admin', 1),
       ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'client1', 2),
       ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'client2', 3);

INSERT INTO user_security_role VALUES (1, 1), (2, 2), (3, 2);

INSERT INTO price(purchase, expenses) VALUES (2500, 200);
INSERT INTO price(purchase, expenses) VALUES (3500, 300);
INSERT INTO price(purchase, expenses) VALUES (4500, 400);

INSERT INTO alignment(name, diameter, length, work_length, angle, check_status, price_id)
VALUES ('Centrovka 1', 2, 20, 12, 120, true, 1);

INSERT INTO alignment(name, diameter, length, work_length, angle, check_status, price_id)
VALUES ('Centrovka 2', 3, 30, 13, 120, true, 2);

INSERT INTO alignment(name, diameter, length, work_length, angle, check_status, price_id)
VALUES ('Centrovka 3', 4, 40, 14, 120, true, 3);





