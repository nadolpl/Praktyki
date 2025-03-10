-- liquibase formatted sql
-- changeset nadol:5
CREATE TABLE publisher
(
    publisher_id   uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    publisher_name varchar(100) NOT NULL,
    CONSTRAINT uq_publisher_name UNIQUE (publisher_name)
);
