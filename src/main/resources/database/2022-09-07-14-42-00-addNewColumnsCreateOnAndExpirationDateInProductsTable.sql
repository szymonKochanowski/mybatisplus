--liquibase formatted sql
--changeset skochanowski:4

alter table sklep.products add column created_on timestamp;
alter table sklep.products add column expiration_date timestamp;