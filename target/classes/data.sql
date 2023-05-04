-- INSERT INTO users (username, password, enabled)
-- values ('user1',
--         'better',
--         1);
--
-- INSERT INTO authorities (username, authority)
-- values ('user1', 'ROLE_USER');
--
-- INSERT INTO users (username, password, enabled)
-- values ('admin',
--         'thebest',
--         1);
-- INSERT INTO users (username, password, enabled)
-- values ('user2',
--         'user2',
--         1);
-- INSERT INTO authorities (username, authority)
-- values ('user2', 'ROLE_USER');
--
-- INSERT INTO authorities (username, authority)
-- values ('user2', 'ROLE_ADMIN');
--
-- INSERT INTO authorities (username, authority)
-- values ('admin', 'ROLE_ADMIN');

--encrypted
INSERT INTO users (username, password, enabled)
values ('user1',
        '$2a$12$6n2VuLde1QxwkDwx.ZZ5mONV/eJ7aKC2g8O8kyeFyaX0bQtXx/uCK',
        1);

INSERT INTO authorities (username, authority)
values ('user1', 'ROLE_USER');

INSERT INTO users (username, password, enabled)
values ('admin',
        '$2a$12$jU1SQMsqtV8jag8Ckt4.VeWMTT/b8bxWbLdK6pidwB/wexK0iEdAS',
        1);
INSERT INTO users (username, password, enabled)
values ('user2',
        '$2a$12$m.ezT/cMfMUnTz59O6EDLuvBHmjH9LzWTVCNYf4XPX/Fc76u75T7C',
        1);
INSERT INTO authorities (username, authority)
values ('user2', 'ROLE_USER');

INSERT INTO authorities (username, authority)
values ('user2', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
values ('admin', 'ROLE_ADMIN');



insert into Store_User (name)
values ('user1');
insert into Store_User (name)
values ('user2');
insert into Store_User (name)
values ('user3');

insert into Category (name)
values ('dairy');
insert into Category (name)
values ('snacks');
insert into Category (name)
values ('fruits');
insert into Category (name)
values ('vegetables');



insert into Item (name, price, category)
values ('yoghurt',2.33,select id from CATEGORY where CATEGORY.NAME='dairy');

insert into Item (name, price, category)
values ('milk',2.53,select id from CATEGORY where CATEGORY.NAME='dairy');

insert into Item (name, price, category)
values ('mars',3.45,select id from CATEGORY where CATEGORY.NAME='snacks');

insert into Item (name, price, category)
values ('apple',5.99,select id from CATEGORY where CATEGORY.NAME='fruits');


insert into OrderData(order_date,store_user_id)
values ('2022-03-15',select id from STORE_USER where STORE_USER.NAME='user1');

insert into order_item(order_id,item_id)
values (select id from ORDERDATA where ORDERDATA.ORDER_DATE='2022-03-15',select id from ITEM where ITEM.NAME='yoghurt');

insert into order_item(order_id,item_id)
values (select id from ORDERDATA where ORDERDATA.ORDER_DATE='2022-03-15',select id from ITEM where ITEM.NAME='milk');

insert into order_item(order_id,item_id)
values (1,select id from ITEM where ITEM.NAME='apple');