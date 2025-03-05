-- liquibase formatted sql
-- changeset nadol:2
CREATE TABLE book_category
(
    category_id serial PRIMARY KEY,
    category_name varchar(100) NOT NULL,
    CONSTRAINT uq_book_category_name UNIQUE (category_name)
);

