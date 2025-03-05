-- liquibase formatted sql
-- changeset nadol:6
CREATE TABLE book_type
(
    book_type_id   serial PRIMARY KEY,
    book_type_name varchar NOT NULL
);