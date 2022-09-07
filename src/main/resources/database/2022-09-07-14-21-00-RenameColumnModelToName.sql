--liquibase formatted sql
--changeset skochanowski:3

alter table sklep.products rename column model to name;