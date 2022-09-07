--liquibase formatted sql
--changeset skochanowski:7

insert into sklep.producents (name, created_on, country)
values
("Mondelēz International", "2012-10-01 11:40:47", "USA"),
("Wedel", "2000-10-01 11:40:47", "Poland"),
("The Coca-Cola Company", "2001-10-01 11:40:47", "USA"),
("Lorenz Bahlsen Snack-World", "2012-10-01 11:40:47", "Germany");
