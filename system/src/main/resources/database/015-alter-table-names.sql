--liquibase formatted sql
--changeset konrad.niedzielski:15

ALTER TABLE author RENAME TO authors;
ALTER TABLE book RENAME TO books;
ALTER TABLE book_author RENAME TO books_authors;
ALTER TABLE book_order RENAME TO books_orders;