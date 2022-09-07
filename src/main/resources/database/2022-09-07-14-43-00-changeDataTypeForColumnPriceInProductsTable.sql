--liquibase formatted sql
--changeset skochanowski:5

alter table sklep.products modify column price DOUBLE;