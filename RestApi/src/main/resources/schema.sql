drop table if exists product;
drop table if exists orders;
drop table if exists order_item;
drop table if exists customer;
drop table if exists cart;

CREATE TABLE customer
(
    customer_id bigint unsigned    not null auto_increment primary key,
    name        varchar(20)        not null,
    email       varchar(50) unique not null,
    address     varchar(200)       not null,
    postcode    varchar(200)       not null,
    created_at  timestamp(6)       not null,
    modified_at timestamp(6) default null
);

CREATE TABLE product
(
    product_id   BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(20)     NOT NULL,
    category     VARCHAR(50)     NOT NULL,
    price        BIGINT          NOT NULL,
    description  VARCHAR(500) DEFAULT NULL,
    created_at   datetime(6)     NOT NULL,
    modified_at  datetime(6)  DEFAULT NULL
);

CREATE TABLE orders
(
    order_id     BIGINT unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
    customer_id  BIGINT          NOT NULL,
    address      VARCHAR(200)    NOT NULL,
    postcode     VARCHAR(200)    NOT NULL,
    order_status VARCHAR(50)     NOT NULL,
    created_at   datetime(6)     NOT NULL,
    modified_at  datetime(6) DEFAULT NULL
);


CREATE TABLE order_item
(
    order_item_id bigint unsigned NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id      bigint          NOT NULL,
    product_id    bigint          NOT NULL,
    category      VARCHAR(50)     NOT NULL,
    price         bigint          NOT NULL,
    quantity      int             NOT NULL,
    created_at    datetime(6)     NOT NULL,
    modified_at    datetime(6) DEFAULT NULL
);

create table cart
(
    cart_id       bigint unsigned not null primary key auto_increment,
    product_id    bigint          not null,
    customer_id   bigint          not null,
    product_count bigint          not null
);

