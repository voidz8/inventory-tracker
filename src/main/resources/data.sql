INSERT INTO role (name) VALUES ('ROLE_USER')
INSERT INTO role (name) VALUES ('ROLE_ADMIN')

INSERT INTO app_user (email, password, username) VALUES ('user@user.com', '$2y$12$eO9XdxGb8W8h5SUYGzhGvegypR.8gdRJvSg/IC1RnsFaIvYaf90LO', 'user')
INSERT INTO app_user (email, password, username) VALUES ('admin@admin.com', '$2y$12$eO9XdxGb8W8h5SUYGzhGvegypR.8gdRJvSg/IC1RnsFaIvYaf90LO', 'admin')

INSERT INTO app_user_roles (user_id, roles_id) VALUES (1,1)
INSERT INTO app_user_roles (user_id, roles_id) VALUES (2,2)

INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, date_sold, sale_price) VALUES ('2021-07-07' , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Tail Light', 1, '2021-07-10', 400)
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, date_sold, sale_price) VALUES ('2021-07-08' , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Tail Light', 1, '2021-07-09', 380)
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id) VALUES ('2021-07-09' , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Tail Light', 1)
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id) VALUES (current_date , 'DH3227-105', 200, 9, 'Fragment x Travis Scott AirJordan 1', 1)
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id) VALUES (current_date , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Tail Light', 2)

INSERT INTO item (item_name, price_bought, date_bought, user_id) values ('Supreme Jersey', 80, '2021-07-07', 1)
INSERT INTO item (item_name, price_bought, date_bought, user_id) values ('Palace Tee', 60, '2021-07-07', 1)
INSERT INTO item (item_name, price_bought, date_bought, user_id, price_sold, date_sold) values ('Bearbrick', 20, '2021-07-09', 1, 50, '2021-07-11')
INSERT INTO item (item_name, price_bought, date_bought, user_id, price_sold, date_sold) values ('Kaws figure', 300, '2021-07-10', 1, 350, '2021-07-10')
INSERT INTO item (item_name, price_bought, date_bought, user_id) values ('Supreme Hoodie', 180, '2021-07-11', 1)

INSERT INTO bot (bot_name, price_bought, date_bought, user_id, date_sold, price_sold) values ('Cyber' , 7000 , '2021-07-01', 1, '2021-07-08', 7500)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('Polaris' , 1000 , '2021-07-04', 1)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('CastleAIO' , 800 , '2021-07-07', 1)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id, date_sold, price_sold) values ('Kodai' , 3500 , '2021-07-10', 1, '2021-07-11', 3600)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('Ganesh' , 2000 , '2021-07-11', 1)
