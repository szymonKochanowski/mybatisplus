create table if not exists `sklep`.`products` (
product_id int AUTO_INCREMENT PRIMARY KEY,
producer_id int,
model varchar(255),
category varchar(50),
price int
);