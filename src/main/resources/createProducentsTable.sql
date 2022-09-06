create table if not exists `sklep`.`producents` (
producer_id int AUTO_INCREMENT PRIMARY KEY,
name varchar(255),
created_on TIMESTAMP,
country varchar(255)
);