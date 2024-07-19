--liquibase formatted sql

--changeset ayushchenko:1
INSERT INTO client_status (name, created_by)
VALUES ('active', 'YAE'),
       ('passive', 'YAE');

--changeset ayushchenko:2
INSERT INTO client_status_localization (client_status_id, language_code, localized_name)
VALUES (1, 'en', 'active'),
       (1, 'ru', 'активный'),
       (2, 'en', 'passive'),
       (2, 'ru', 'пассивный');

--changeset ayushchenko:3
INSERT INTO business_type (name, description, created_by)
VALUES ('Microenterprise', 'Up to 15 employees or turnover up to 120 million rubles', 'YAE'),
       ('Small Business', '16-100 employees or turnover up to 800 million rubles', 'YAE'),
       ('Medium Business', '101-250 employees or turnover up to 2 billion rubles', 'YAE'),
       ('Large Business', 'More than 251 employees or turnover from 2 billion rubles', 'YAE'),
       ('Individual', 'Private individual making purchases for personal, non-commercial purposes', 'YAE');

--changeset ayushchenko:4
INSERT INTO business_type_localization(business_type_id, language_code, localized_name, localized_description)
VALUES (1, 'ru','Микропредприятие', 'до 15 человек или оборот до 120 млн рублей'),
       (2, 'ru','Малый бизнес', '16-100 человек или оборот до 800 млн рублей'),
       (3, 'ru','Средний бизнес', '101-250 человек или оборот до 2 млрд рублей'),
       (4, 'ru','Крупный бизнес', '251< человек или оборот от 2 млрд рублей'),
       (5, 'ru','Физлицо', 'Частное лицо, совершающее покупки для личных, некоммерческих целей');

--changeset ayushchenko:5
INSERT INTO industry_type (name, description, created_by)
VALUES ('Manufacturing', 'The industry involved in the production of goods', 'YAE'),
       ('Retail', 'The sale of goods directly to end consumers', 'YAE'),
       ('Pharmaceuticals', 'The industry engaged in the development, production, and marketing of drugs', 'YAE'),
       ('Automotive Industry', 'The industry involved in the production of automobiles', 'YAE'),
       ('Technology and Electronics', 'The industry related to the development and production of technological and electronic devices', 'YAE'),
       ('Construction', 'The industry involved in the building of structures and buildings', 'YAE'),
       ('Food Industry', 'The industry involved in the production of food products', 'YAE'),
       ('eCommerce', 'Electronic commerce, associated with buying and selling goods or services over the internet', 'YAE'),
       ('Agricultural Sector', 'The industry involved in agriculture and processing of agricultural products', 'YAE');

--changeset ayushchenko:6
INSERT INTO industry_type_localization(industry_type_id, language_code, localized_name, localized_description)
VALUES
    (1,'ru', 'Производство', 'Отрасль, связанная с производством товаров'),
    (2,'ru', 'Розничная торговля', 'Продажа товаров напрямую конечным потребителям'),
    (3,'ru', 'Фармацевтика', 'Отрасль, занимающаяся разработкой, производством и продажей лекарственных средств'),
    (4,'ru', 'Автомобильная промышленность', 'Отрасль, занимающаяся производством автомобилей'),
    (5,'ru', 'Технологии и электроника', 'Отрасль, связанная с разработкой и производством технологических и электронных устройств'),
    (6,'ru', 'Строительство', 'Отрасль, занимающаяся возведением зданий и сооружений'),
    (7,'ru', 'Пищевая Промышленность', 'Отрасль, занимающаяся производством пищевых продуктов'),
    (8,'ru', 'eCommerce', 'Электронная коммерция, связанная с покупкой и продажей товаров или услуг через интернет'),
    (9,'ru', 'Агропромышленный Сектор', 'Отрасль, занимающаяся сельским хозяйством и обработкой сельскохозяйственной продукции');

--changeset ayushchenko:7
INSERT INTO client (name, full_name, status_id, business_type_id, industry_type_id, address)
VALUES
    ('UTLS RUS', 'UTLS RUSSIA', 1, 1, 1, 'Moscow, Tverskaya str., 1'),
    ('UTLS CHINA', 'UTLS CHINA', 1, 1, 1, 'Moscow, Tverskaya str., 1'),
    ('RUSSIANO', 'RUSSIANO', 1, 1, 1, 'Italia, Roma, via Felice, 37'),
    ('MEXICAN CARTEL', 'MEXICAN CARTEL', 1, 1, 1, 'Museo Nacional de la Estampa, Av. Hidalgo 39, Centro Histórico de la Cdad. de México, Guerrero, Cuauhtémoc, 06050 Ciudad de México, CDMX, Messico');
