INSERT INTO role(name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO client(first_name, surname)
VALUES ('First Admin', 'Surname Admin'),
       ('Dmitriy', 'D'),
       ('Igor', 'I');

INSERT INTO user_security(password, username, client_id)
VALUES ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'admin', 1),
       ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'client1', 2),
       ('$2a$12$HlanqPNmawKa22FpNzLTZOrO/YDGSEFZkr1z4H3haQzRAK3CGSSAm', 'client2', 3);

INSERT INTO user_security_role(role_id, user_security_id)
VALUES (1, 1),
       (2, 2),
       (2, 3);

INSERT INTO price(purchase, expenses, markup, price_rent_of_day)
VALUES (2500, 200, 40, 50),
       (3500, 300, 40, 50),
       (4500, 310, 40, 50),
       (4000, 320, 40, 50),
       (4100, 330, 40, 50),
       (4200, 340, 40, 50),
       (4300, 370, 40, 50),
       (4350, 380, 40, 50),
       (4400, 390, 40, 50),
       (4450, 420, 40, 50),
       (4500, 430, 40, 50),
       (4550, 435, 40, 50),
       (4600, 450, 40, 50),
       (4650, 420, 40, 50),
       (4700, 410, 40, 50),
       (4750, 450, 40, 50),
       (4800, 500, 40, 50),
       (4850, 300, 40, 50),
       (4900, 350, 40, 50),
       (4950, 400, 40, 50),
       (5000, 390, 40, 50),
       (5050, 410, 40, 50),
       (5100, 380, 40, 50),
       (5150, 350, 40, 50);

INSERT INTO alignment(name, diameter, length, work_length, angle, check_status, price_id)
VALUES ('Centrovka 1', 2, 30, 10, 120, true, 1),
       ('Centrovka 2', 3, 30, 11, 120, true, 2),
       ('Centrovka 3', 4, 40, 12, 120, true, 3),
       ('Centrovka 4', 4, 30, 13, 120, true, 4),
       ('Centrovka 5', 4, 30, 10, 120, true, 5),
       ('Centrovka 6', 2, 40, 11, 120, true, 6),
       ('Centrovka 7', 3, 40, 12, 120, true, 7),
       ('Centrovka 8', 4, 30, 13, 120, true, 8),
       ('Centrovka 9', 2, 40, 14, 120, true, 9),
       ('Centrovka 10', 3, 40, 15, 120, true, 10),
       ('Centrovka 11', 4, 30, 11, 120, true, 11),
       ('Centrovka 12', 2, 40, 12, 120, true, 12);

INSERT INTO countersink(name, diameter, length, work_length, angle, check_status, price_id)
VALUES ('Zenkovka 1', 6, 40, 10, 90, true, 13),
       ('Zenkovka 2', 8, 40, 10, 90, true, 14),
       ('Zenkovka 3', 12, 40, 10, 90, true, 15),
       ('Zenkovka 4', 8, 40, 10, 90, true, 16),
       ('Zenkovka 5', 8, 40, 10, 90, true, 17),
       ('Zenkovka 6', 6, 40, 10, 90, true, 18),
       ('Zenkovka 7', 8, 40, 10, 90, true, 19),
       ('Zenkovka 8', 10, 40, 10, 90, true, 20),
       ('Zenkovka 9', 12, 40, 10, 90, true, 21),
       ('Zenkovka 10', 14, 40, 10, 90, true, 22),
       ('Zenkovka 11', 8, 40, 10, 90, true, 23),
       ('Zenkovka 12', 10, 40, 10, 90, true, 24);


INSERT INTO archive(create_archive, delete_archive, day_rent, start_rental, end_rental, id_client, name_client,
                    surname_client, id_instrument, name_instrument)
VALUES (date '2023-12-10', date '2023-12-10' + interval '1 year', 1, date '2023-12-10', date '2023-12-10' + 1, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 1,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 1)),

       (date '2023-12-11', date '2023-12-11' + interval '1 year', 2, date '2023-12-11', date '2023-12-11' + 2, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 2,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 2)),

       (date '2023-12-12', date '2023-12-12' + interval '1 year', 3, date '2023-12-12', date '2023-12-12' + 3, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 3,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 3)),

       (date '2023-12-13', date '2023-12-13' + interval '1 year', 4, date '2023-12-13', date '2023-12-13' + 4, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 4,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 4)),

       (date '2023-12-14', date '2023-12-14' + interval '1 year', 5, date '2023-12-14', date '2023-12-14' + 5, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 5,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 5)),

       (date '2023-12-15', date '2023-12-15' + interval '1 year', 6, date '2023-12-15', date '2023-12-15' + 6, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 6,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 6)),

       (date '2023-12-16', date '2023-12-16' + interval '1 year', 7, date '2023-12-16', date '2023-12-16' + 7, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 7,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 7)),

       (date '2023-12-17', date '2023-12-17' + interval '1 year', 8, date '2023-12-17', date '2023-12-17' + 8, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 8,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 8)),

       (date '2023-12-18', date '2023-12-18' + interval '1 year', 9, date '2023-12-18', date '2023-12-18' + 9, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 9,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 9)),

       (date '2023-12-19', date '2023-12-19' + interval '1 year', 10, date '2023-12-19', date '2023-12-19' + 10, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 10,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 10)),

       (date '2023-12-20', date '2023-12-20' + interval '1 year', 11, date '2023-12-20', date '2023-12-20' + 11, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 11,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 11)),

       (date '2023-12-21', date '2023-12-21' + interval '1 year', 12, date '2023-12-21', date '2023-12-21' + 12, 3,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 12,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 12)),


       (date '2023-11-05', date '2023-11-05' + interval '1 year', 5, date '2023-11-05', date '2023-11-05' + 5, 2,
        (SELECT client.first_name FROM client WHERE client.id = 2),
        (SELECT client.surname FROM client WHERE client.id = 2), 2,
        (SELECT alignment.name FROM alignment WHERE alignment.id = 2)),

       (date '2023-11-06', date '2023-11-06' + interval '1 year', 5, date '2023-11-06', date '2023-12-21' + 6, 2,
        (SELECT client.first_name FROM client WHERE client.id = 3),
        (SELECT client.surname FROM client WHERE client.id = 3), 1,
        (SELECT countersink.name FROM countersink WHERE countersink.id = 1));


INSERT INTO rent(day_rent, start_rental, end_rental, check_status, client_id, alignment_id, countersink_id,
                 archive_id)
VALUES (1, date '2023-12-10', date '2023-12-10' + 1, true, 2, 1, null, 1),
       (2, date '2023-12-11', date '2023-12-11' + 2, true, 2, null, 2, 2),
       (3, date '2023-12-12', date '2023-12-12' + 3, true, 3, 3, null, 3),
       (4, date '2023-12-13', date '2023-12-13' + 4, true, 3, null, 4, 4),
       (5, date '2023-12-14', date '2023-12-14' + 5, true, 2, 5, null, 5),
       (6, date '2023-12-15', date '2023-12-15' + 6, true, 2, null, 6, 6),
       (7, date '2023-12-16', date '2023-12-16' + 7, true, 3, 7, null, 7),
       (8, date '2023-12-17', date '2023-12-17' + 8, true, 3, null, 8, 8),
       (9, date '2023-12-18', date '2023-12-18' + 9, true, 2, 9, null, 9),
       (10, date '2023-12-19', date '2023-12-19' + 10, true, 2, null, 10, 10),
       (11, date '2023-12-20', date '2023-12-20' + 11, true, 3, 11, null, 11),
       (12, date '2023-12-21', date '2023-12-21' + 12, true, 3, null, 12, 12),

       (5, date '2023-11-05', date '2023-11-05' + 5, false, 2, 2, null, 13),
       (5, date '2023-11-06', date '2023-11-06' + 5, false, 2, null, 1, 14);

INSERT INTO profit(day_rent, income, tax, rent_id)
VALUES (1, 1 * 50, 1 * 50 * 13 / 100, 1),
       (2, 2 * 50, 2 * 50 * 13 / 100, 2),
       (3, 3 * 50, 3 * 50 * 13 / 100, 3),
       (4, 4 * 50, 4 * 50 * 13 / 100, 4),
       (5, 5 * 50, 5 * 50 * 13 / 100, 5),
       (6, 6 * 50, 6 * 50 * 13 / 100, 6),
       (7, 7 * 50, 7 * 50 * 13 / 100, 7),
       (8, 8 * 50, 8 * 50 * 13 / 100, 8),
       (9, 9 * 50, 9 * 50 * 13 / 100, 9),
       (10, 10 * 50, 10 * 50 * 13 / 100, 10),
       (11, 11 * 50, 11 * 50 * 13 / 100, 11),
       (12, 12 * 50, 12 * 50 * 13 / 100, 12),

       (13, 13 * 50, 13 * 50 * 13 / 100, 13),
       (14, 14 * 50, 14 * 50 * 13 / 100, 14);

UPDATE alignment
SET check_status = false
WHERE alignment.id = 1;
UPDATE alignment
SET check_status = false
WHERE alignment.id = 3;
UPDATE alignment
SET check_status = false
WHERE alignment.id = 5;
UPDATE alignment
SET check_status = false
WHERE alignment.id = 7;
UPDATE alignment
SET check_status = false
WHERE alignment.id = 9;
UPDATE alignment
SET check_status = false
WHERE alignment.id = 11;

UPDATE countersink
SET check_status = false
WHERE countersink.id = 2;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 4;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 6;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 8;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 10;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 12;

UPDATE alignment
SET check_status = false
WHERE alignment.id = 2;
UPDATE countersink
SET check_status = false
WHERE countersink.id = 1;

INSERT INTO renovation(check_status, count_day, description_result, price_diagnostics, end_renovation, result_price,
                       start_renovation, alignment_id, countersink_id)
VALUES (true, 2, 'Successful', 100, date '2023-12-13' + 2, 200, date '2023-12-13', 2, null),
       (true, 2, 'Successful', 100, date '2023-12-12' + 2, 200, date '2023-12-12', null, 1);









