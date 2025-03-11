-- liquibase formatted sql
-- changeset nadol:7
CREATE TABLE published_book
(
    published_book_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id           uuid NOT NULL,
    publisher_id      uuid NOT NULL,
    book_type_id      serial  NOT NULL,
    release_number    int  NOT NULL,
    publish_date      date NOT NULL,
    CONSTRAINT fk_published_book_book FOREIGN KEY (book_id) REFERENCES book (book_id),
    CONSTRAINT fk_published_book_publisher FOREIGN KEY (publisher_id) REFERENCES publisher (publisher_id),
    CONSTRAINT fk_published_book_book_type FOREIGN KEY (book_type_id) REFERENCES book_type (book_type_id)
);
