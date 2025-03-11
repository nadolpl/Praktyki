-- liquibase formatted sql
-- changeset nadol:4
CREATE TABLE book_author
(
    book_author_id serial PRIMARY KEY,
    author_id      uuid NOT NULL,
    book_id        uuid NOT NULL,
    CONSTRAINT fk_book_author_author FOREIGN KEY (author_id) REFERENCES author (author_id),
    CONSTRAINT fk_book_author_book FOREIGN KEY (book_id) REFERENCES book (book_id),
    CONSTRAINT uq_book_author UNIQUE (book_id, author_id)
);
