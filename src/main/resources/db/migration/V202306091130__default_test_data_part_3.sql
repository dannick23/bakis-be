INSERT INTO working_hours (system_id, day_of_week, time_from, time_to)
VALUES (1, 0, '2023-05-25 08:00:00', '2023-05-25 17:00:00'),
       (1, 1, '2023-05-26 08:00:00', '2023-05-26 17:00:00'),
       (1, 2, '2023-05-27 08:00:00', '2023-05-27 17:00:00'),
       (1, 3, '2023-05-28 08:00:00', '2023-05-28 17:00:00'),
       (1, 4, '2023-05-29 08:00:00', '2023-05-29 17:00:00');
INSERT INTO users (system_id, description, first_name, last_name, email, password, authority,
                   phone_number, image)
VALUES (1, '', 'Mindaugas', 'Admin', 'admin@a.com;test-name',
        '$2a$10$d0Le80wD34IXJj4dCyzRdOilRQj6bjxqpm2xx.wrTjnipPJCnvhaq', 'ADMIN', '860998366', NULL),
       (1, '', 'Mindaugas', 'Client', 'client@a.com;test-name',
        '$2a$10$TxNQaWAa5FCTa/Z69B0Sr.5RL63eDkOCwt/bQToDetk1m4mCDGuKe', 'CLIENT', '860998366', NULL),
       (1, 'worker 1', 'Laurynas', 'Navickas', 'a@a.com;test-name',
        '$2a$10$Ojqs1QSSpBr8qbwCWhpYMeMRAHU8muWUTn8KcWSydYrDOLor5iR1.', 'WORKER', '867634588', NULL),
       (1, 'Worker description', 'Vitalijus', 'Navickas', 'aa@a.com;test-name',
        '$2a$10$qJCfd950o20pSqGEJdADYObHhBJ.g1RC9T8pVu1ql3eaY8TPCo21.', 'WORKER', '867634588', NULL),
       (1, 'This is the worker profile', 'Unconfirmed', 'Demo', 'unconfirmed@a.com;test-name',
        '$2a$10$baYdDPtbVqjCWjbdDxRMCOfPGVcAorYaB5cqvH4Hdd9xhUGc/7md2', 'AWAITING_CONFIRMATION', '867634588', NULL),
       (1, 'Worker description', 'Mindaugas', 'Worker', 'worker@a.com;test-name',
        '$2a$10$iDdcCaWKEEUP3EHk3sQm/.WsibNEguCpBxnkHPyI32TobrTGr55Wy', 'WORKER', '860998366', NULL);
INSERT INTO worker_shift (user_id, time_from, time_to, shift_type)
VALUES (3, '2023-05-25 08:00:00', '2023-05-25 17:00:00', 'REGULAR'),
       (3, '2023-05-26 08:00:00', '2023-05-26 17:00:00', 'REGULAR'),
       (3, '2023-05-29 08:00:00', '2023-05-29 17:00:00', 'REGULAR'),
       (3, '2023-05-30 08:00:00', '2023-05-30 17:00:00', 'REGULAR'),
       (3, '2023-05-31 08:00:00', '2023-05-31 17:00:00', 'REGULAR'),
       (3, '2023-06-01 08:00:00', '2023-06-01 17:00:00', 'REGULAR'),
       (3, '2023-06-02 08:00:00', '2023-06-02 17:00:00', 'REGULAR'),
       (3, '2023-06-05 08:00:00', '2023-06-05 17:00:00', 'REGULAR'),
       (3, '2023-06-06 08:00:00', '2023-06-06 17:00:00', 'REGULAR'),
       (3, '2023-06-07 08:00:00', '2023-06-07 17:00:00', 'REGULAR'),
       (3, '2023-06-08 08:00:00', '2023-06-08 17:00:00', 'REGULAR'),
       (3, '2023-06-09 08:00:00', '2023-06-09 17:00:00', 'REGULAR'),
       (3, '2023-06-12 08:00:00', '2023-06-12 17:00:00', 'REGULAR'),
       (3, '2023-06-13 08:00:00', '2023-06-13 17:00:00', 'REGULAR'),
       (3, '2023-06-14 08:00:00', '2023-06-14 17:00:00', 'REGULAR'),
       (3, '2023-06-15 08:00:00', '2023-06-15 17:00:00', 'REGULAR'),
       (3, '2023-06-16 08:00:00', '2023-06-16 17:00:00', 'REGULAR'),
       (3, '2023-06-19 08:00:00', '2023-06-19 17:00:00', 'REGULAR'),
       (3, '2023-06-20 08:00:00', '2023-06-20 17:00:00', 'REGULAR'),
       (3, '2023-06-21 08:00:00', '2023-06-21 17:00:00', 'REGULAR'),
       (3, '2023-06-22 08:00:00', '2023-06-22 17:00:00', 'REGULAR'),
       (3, '2023-06-23 08:00:00', '2023-06-23 17:00:00', 'REGULAR'),
       (4, '2023-05-25 08:00:00', '2023-05-25 17:00:00', 'REGULAR'),
       (4, '2023-05-26 08:00:00', '2023-05-26 17:00:00', 'REGULAR'),
       (4, '2023-05-29 08:00:00', '2023-05-29 17:00:00', 'REGULAR'),
       (4, '2023-05-30 08:00:00', '2023-05-30 17:00:00', 'REGULAR'),
       (4, '2023-05-31 08:00:00', '2023-05-31 17:00:00', 'REGULAR'),
       (4, '2023-06-01 08:00:00', '2023-06-01 17:00:00', 'REGULAR'),
       (4, '2023-06-02 08:00:00', '2023-06-02 17:00:00', 'REGULAR'),
       (4, '2023-06-05 08:00:00', '2023-06-05 17:00:00', 'REGULAR'),
       (4, '2023-06-06 08:00:00', '2023-06-06 17:00:00', 'REGULAR'),
       (4, '2023-06-07 08:00:00', '2023-06-07 17:00:00', 'REGULAR'),
       (4, '2023-06-08 08:00:00', '2023-06-08 17:00:00', 'REGULAR'),
       (4, '2023-06-09 08:00:00', '2023-06-09 17:00:00', 'REGULAR'),
       (4, '2023-06-12 08:00:00', '2023-06-12 17:00:00', 'REGULAR'),
       (4, '2023-06-13 08:00:00', '2023-06-13 17:00:00', 'REGULAR'),
       (4, '2023-06-14 08:00:00', '2023-06-14 17:00:00', 'REGULAR'),
       (4, '2023-06-15 08:00:00', '2023-06-15 17:00:00', 'REGULAR'),
       (4, '2023-06-16 08:00:00', '2023-06-16 17:00:00', 'REGULAR'),
       (4, '2023-06-19 08:00:00', '2023-06-19 17:00:00', 'REGULAR'),
       (4, '2023-06-20 08:00:00', '2023-06-20 17:00:00', 'REGULAR'),
       (4, '2023-06-21 08:00:00', '2023-06-21 17:00:00', 'REGULAR'),
       (4, '2023-06-22 08:00:00', '2023-06-22 17:00:00', 'REGULAR'),
       (4, '2023-06-23 08:00:00', '2023-06-23 17:00:00', 'REGULAR'),
       (5, '2023-05-25 08:00:00', '2023-05-25 17:00:00', 'REGULAR'),
       (5, '2023-05-26 08:00:00', '2023-05-26 17:00:00', 'REGULAR'),
       (5, '2023-05-29 08:00:00', '2023-05-29 17:00:00', 'REGULAR'),
       (5, '2023-05-30 08:00:00', '2023-05-30 17:00:00', 'REGULAR'),
       (5, '2023-05-31 08:00:00', '2023-05-31 17:00:00', 'REGULAR'),
       (5, '2023-06-01 08:00:00', '2023-06-01 17:00:00', 'REGULAR'),
       (5, '2023-06-02 08:00:00', '2023-06-02 17:00:00', 'REGULAR'),
       (5, '2023-06-05 08:00:00', '2023-06-05 17:00:00', 'REGULAR'),
       (5, '2023-06-06 08:00:00', '2023-06-06 17:00:00', 'REGULAR'),
       (5, '2023-06-07 08:00:00', '2023-06-07 17:00:00', 'REGULAR'),
       (5, '2023-06-08 08:00:00', '2023-06-08 17:00:00', 'REGULAR'),
       (5, '2023-06-09 08:00:00', '2023-06-09 17:00:00', 'REGULAR'),
       (5, '2023-06-12 08:00:00', '2023-06-12 17:00:00', 'REGULAR'),
       (5, '2023-06-13 08:00:00', '2023-06-13 17:00:00', 'REGULAR'),
       (5, '2023-06-14 08:00:00', '2023-06-14 17:00:00', 'REGULAR'),
       (5, '2023-06-15 08:00:00', '2023-06-15 17:00:00', 'REGULAR'),
       (5, '2023-06-16 08:00:00', '2023-06-16 17:00:00', 'REGULAR'),
       (5, '2023-06-19 08:00:00', '2023-06-19 17:00:00', 'REGULAR'),
       (5, '2023-06-20 08:00:00', '2023-06-20 17:00:00', 'REGULAR'),
       (5, '2023-06-21 08:00:00', '2023-06-21 17:00:00', 'REGULAR'),
       (5, '2023-06-22 08:00:00', '2023-06-22 17:00:00', 'REGULAR'),
       (5, '2023-06-23 08:00:00', '2023-06-23 17:00:00', 'REGULAR'),
       (6, '2023-05-25 08:00:00', '2023-05-25 17:00:00', 'REGULAR'),
       (6, '2023-05-26 08:00:00', '2023-05-26 17:00:00', 'REGULAR'),
       (6, '2023-05-29 08:00:00', '2023-05-29 17:00:00', 'REGULAR'),
       (6, '2023-05-30 08:00:00', '2023-05-30 17:00:00', 'REGULAR'),
       (6, '2023-05-31 08:00:00', '2023-05-31 17:00:00', 'REGULAR'),
       (6, '2023-06-01 08:00:00', '2023-06-01 17:00:00', 'REGULAR'),
       (6, '2023-06-02 08:00:00', '2023-06-02 17:00:00', 'REGULAR'),
       (6, '2023-06-05 08:00:00', '2023-06-05 17:00:00', 'REGULAR'),
       (6, '2023-06-06 08:00:00', '2023-06-06 17:00:00', 'REGULAR'),
       (6, '2023-06-07 08:00:00', '2023-06-07 17:00:00', 'REGULAR'),
       (6, '2023-06-08 08:00:00', '2023-06-08 17:00:00', 'REGULAR'),
       (6, '2023-06-09 08:00:00', '2023-06-09 17:00:00', 'REGULAR'),
       (6, '2023-06-12 08:00:00', '2023-06-12 17:00:00', 'REGULAR'),
       (6, '2023-06-13 08:00:00', '2023-06-13 17:00:00', 'REGULAR'),
       (6, '2023-06-14 08:00:00', '2023-06-14 17:00:00', 'REGULAR'),
       (6, '2023-06-15 08:00:00', '2023-06-15 17:00:00', 'REGULAR'),
       (6, '2023-06-16 08:00:00', '2023-06-16 17:00:00', 'REGULAR'),
       (6, '2023-06-19 08:00:00', '2023-06-19 17:00:00', 'REGULAR'),
       (6, '2023-06-20 08:00:00', '2023-06-20 17:00:00', 'REGULAR'),
       (6, '2023-06-21 08:00:00', '2023-06-21 17:00:00', 'REGULAR'),
       (6, '2023-06-22 08:00:00', '2023-06-22 17:00:00', 'REGULAR'),
       (6, '2023-06-23 08:00:00', '2023-06-23 17:00:00', 'REGULAR'),
       (3, '2023-05-27 08:00:00', '2023-05-27 20:00:00', 'OVERTIME');