drop database if exists EVADON;
create database EVADON default charset=utf8 collate=utf8_bin;

use EVADON;
drop table if exists PRODUCT;
create table PRODUCT (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    name char(20) not null,
    quantity int not null default 0,
    order_quantity int default 0
)default charset=utf8;

