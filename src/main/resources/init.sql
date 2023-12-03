INSERT INTO users(is_suspended, first_name, last_name, phone_number)
VALUES (false, '1', '1', '1'),
       (false, '2', '2', '2'),
       (false, '3', '3', '3'),
       (false, '4', '4', '4'),
       (false, '5', '5', '5'),
       (false, '6', '6', '6'),
       (false, '7', '7', '7'),
       (false, '8', '8', '8'),
       (false, '9', '9', '9'),
       (false, '10', '1', '10'),
       (false, '11', '11', '11'),
       (false, '12', '12', '12'),
       (false, '13', '13', '13'),
       (false, '14', '14', '14'),
       (false, '15', '15', '15'),
       (false, '16', '16', '16');

INSERT INTO courier(first_name, last_name, phone_number, courier_status)
VALUES ('1', '1', '1', 'WORKING'),
       ('2', '2', '2', 'WORKING'),
       ('3', '3', '3', 'ON_MODERATING'),
       ('4', '4', '4', 'WORKING'),
       ('5', '5', '5', 'WORKING'),
       ('6', '6', '6', 'SUSPENDED'),
       ('7', '7', '7', 'FREE'),
       ('8', '8', '8', 'WORKING'),
       ('9', '9', '9', 'WORKING'),
       ('10', '10', '10', 'WORKING');


INSERT INTO package(description, height, length, weight, width, package_status, courier_id, from_user_id, to_user_id)
VALUES ('Описание для 1 посылки', 1.0, 1.0, 1.0, 1.0, 'UNDER_MODERATOR_CONSIDERATION', 9, 3, 11),
       ('Описание для 2 посылки', 2.2, 2.2, 2.2, 2.2, 'WAITING_FOR_COURIER', NULL, 3, 10),
       ('Описание для 3 посылки', 3.3, 3.3, 3.3, 3.3, 'CANCELED_BY_MODERATOR', 7, 3, 9),
       ('Описание для 4 посылки', 4.4, 4.4, 4.4, 4.4, 'WAITING_FOR_COURIER', 6, 3, 8),
       ('Описание для 5 посылки', 5.5, 5.5, 5.5, 5.5, 'COURIER_APPOINTED', 5, 3, 7),
       ('Описание для 6 посылки', 6.6, 6.6, 6.6, 6.6, 'COURIER_ON_THE_WAY_TO_RECEIVE', 4, 3, 6),
       ('Описание для 7 посылки', 7.7, 7.7, 7.7, 7.7, 'COURIER_DENY_PACKAGE', 3, 3, 5),
       ('Описание для 8 посылки', 8.8, 8.8, 8.8, 8.8, 'COURIER_ON_THE_WAY_TO_DELIVERY', 2, 3, 4),
       ('Описание для 9 посылки', 9.9, 9.9, 9.9, 9.9, 'PACKAGE_DELIVERED', 1, 4, 3);
