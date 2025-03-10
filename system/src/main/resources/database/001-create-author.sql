-- liquibase formatted sql
-- changeset nadol:1
CREATE TABLE author
(
    author_id  uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL,
    CONSTRAINT uq_author_email UNIQUE (email)
);