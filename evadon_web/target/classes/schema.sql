create table if not exists product (
    id int not null AUTO_INCREMENT PRIMARY KEY,
    name varchar(20) not null,
    quantity int not null default 0,
    order_quantity int default 0,
    order_code varchar(20) unique,
    oneday_need int not null default 0,
    necessary_quantity int not null default 0
)default charset=utf8;