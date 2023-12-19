-- liquibase formatted sql

-- changeset yuzu:1
CREATE TABLE shelter_user (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255),
    surname VARCHAR(255),
    phone_number VARCHAR(255),
    adopt_date DATE,
    type SMALLINT,
    PRIMARY KEY (id)
)