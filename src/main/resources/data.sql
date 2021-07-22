INSERT INTO role (name) VALUES ('ROLE_USER')
INSERT INTO role (name) VALUES ('ROLE_ADMIN')

INSERT INTO app_user (email, password, username) VALUES ('user@user.com', '$2y$12$eO9XdxGb8W8h5SUYGzhGvegypR.8gdRJvSg/IC1RnsFaIvYaf90LO', 'user')
INSERT INTO app_user (email, password, username) VALUES ('admin@admin.com', '$2y$12$eO9XdxGb8W8h5SUYGzhGvegypR.8gdRJvSg/IC1RnsFaIvYaf90LO', 'admin')

INSERT INTO app_user_roles (user_id, roles_id) VALUES (1,1)
INSERT INTO app_user_roles (user_id, roles_id) VALUES (2,2)

insert into image (id, file_name, file_type) values ('1','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('2','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('3','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('4','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('5','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('6','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('7','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('8','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('9','no-photo', 'no-type')
insert into image (id, file_name, file_type) values ('10','no-photo', 'no-type')

INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, date_sold, sale_price, image_id) VALUES ('2021-07-07' , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Tail Light', 1, '2021-07-10', 400, '1')
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, date_sold, sale_price, image_id) VALUES ('2021-07-08' , 'FX9017', 220, 9.5, 'Adidas YeezyBoost 350 V2 Zebra', 1, '2021-07-09', 380, '2')
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, image_id) VALUES ('2021-07-09' , 'FX9017', 220, 9.5, 'AirJordan 1 Court Purple 2021', 1, '3')
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, image_id) VALUES (current_date , 'DH3227-105', 200, 9, 'Fragment x Travis Scott AirJordan 1', 1, '4')
INSERT INTO sneaker (date_bought, pid, price_bought, sneaker_size , sneaker_name, user_id, image_id) VALUES (current_date , 'FX9017', 220, 9.5, 'Parra x Nike SB Dunk Low', 2, '5')

INSERT INTO item (item_name, price_bought, date_bought, user_id, image_id) values ('Supreme Jersey', 80, '2021-07-07', 1, '6')
INSERT INTO item (item_name, price_bought, date_bought, user_id, image_id) values ('Palace Tee', 60, '2021-07-07', 1,'7')
INSERT INTO item (item_name, price_bought, date_bought, user_id, price_sold, date_sold, image_id) values ('Bearbrick', 20, '2021-07-09', 1, 50, '2021-07-11','8')
INSERT INTO item (item_name, price_bought, date_bought, user_id, price_sold, date_sold, image_id) values ('Kaws figure', 300, '2021-07-10', 1, 350, '2021-07-10','9')
INSERT INTO item (item_name, price_bought, date_bought, user_id, image_id) values ('Supreme Hoodie', 180, '2021-07-11', 1, '10')

INSERT INTO bot (bot_name, price_bought, date_bought, user_id, date_sold, price_sold) values ('Cyber' , 7000 , '2021-07-01', 1, '2021-07-08', 7500)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('Polaris' , 1000 , '2021-07-04', 1)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('CastleAIO' , 800 , '2021-07-07', 1)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id, date_sold, price_sold) values ('Kodai' , 3500 , '2021-07-10', 1, '2021-07-11', 3600)
INSERT INTO bot (bot_name, price_bought, date_bought, user_id) values ('Ganesh' , 2000 , '2021-07-11', 1)

