-- liquibase formatted sql
-- changeset Ostrowski12:14
create table book_order
(
    book_order_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    book_id       UUID,
    order_ID      UUID,
    quantity      int,
    CONSTRAINT fk_book_order_book_book FOREIGN KEY (book_id) references book(book_id),
    CONSTRAINT fk_book_order_order_order FOREIGN KEY (order_ID) references orders(order_id)
)
