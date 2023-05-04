DROP TABLE IF EXISTS ORDER_ITEM;
DROP TABLE IF EXISTS ORDERDATA;
DROP TABLE IF EXISTS STORE_USER;
DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS AUTHORITIES;
DROP TABLE IF EXISTS USERS;

CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(200) NOT NULL,
                       enabled TINYINT NOT NULL DEFAULT 1,
                       PRIMARY KEY (username)
);

CREATE TABLE authorities (
                             id int not null auto_increment,
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             PRIMARY KEY (id),
                             FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX ix_auth_username
    on authorities (username,authority);

create table if not exists Item (
    id int not null auto_increment,
    name varchar(50) not null,
    price  double not null,
    category int not null,
    PRIMARY KEY (id)
);

create table if not exists Category (
    id int not null auto_increment,
    name varchar(20) not null,
    PRIMARY KEY (id)
);

create table if not exists Store_User (
    id int not null auto_increment,
    name varchar(20) not null,
    PRIMARY KEY (id)
);

create table if not exists OrderData (
    id int not null auto_increment,
    order_date timestamp not null,
    store_user_id int not null,
    PRIMARY KEY (id)
);



create table if not exists Order_Item (
    id int not null auto_increment,
    order_id int,
    item_id int,
    PRIMARY KEY (id)
);


alter table Item
add foreign key (category) references CATEGORY(id);
alter table OrderData
add foreign key (store_user_id) references Store_User(id);
alter table Order_Item
add foreign key (order_id) references OrderData(id);
alter table Order_Item
add foreign key (item_id) references Item(id);

