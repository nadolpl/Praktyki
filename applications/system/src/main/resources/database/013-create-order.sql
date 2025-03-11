-- liquibase formatted sql
-- changeset Ostrowski12:13

create table orders
(
    order_id     UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    final_price  decimal,
    order_status varchar(32)
)

