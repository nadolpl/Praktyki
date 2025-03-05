-- liquibase formatted sql
-- changeset nadol:3
CREATE TABLE book
(
    book_id     uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    book_title  varchar(255) NOT NULL,
    pages       int NOT NULL CHECK (pages > 0),
    category_id int NOT NULL,
    CONSTRAINT fk_book_category FOREIGN KEY (category_id) REFERENCES book_category(category_id)
);