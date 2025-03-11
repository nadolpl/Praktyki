--liquibase formatted sql
--changeset konrad.niedzielski:16

CREATE TABLE IF NOT EXISTS user_accounts
(
    user_id  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(64) NOT NULL,
    password VARCHAR     NOT NULL,
    CONSTRAINT unique_username UNIQUE (username)
);