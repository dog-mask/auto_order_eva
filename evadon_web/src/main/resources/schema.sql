drop database if exists EVADON;
create database EVADON default charset=utf8 collate=utf8_bin;
use EVADON;
drop table if exists product;
create table product (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) not null,
    quantity int not null default 0,
    order_quantity int default 0,
    order_code varchar(20),
    oneday_need int not null default 0
)default charset=utf8;

