-- liquibase formatted sql

-- changeset qmargo:1
CREATE TABLE animal
(
    id                  BIGSERIAL           PRIMARY KEY,
    type_animal         varchar(255)        NOT NULL,
    breed               varchar(255),
    in_shelter          boolean,
    health              boolean
)

-- changeset qmargo:2
INSERT INTO animal (type_animal,    breed,      in_shelter,         health)
VALUES             ('dog',       'дворняжка',        true,           false),
                   ('dog',       'овчарка',          false,          true),
                   ('dog',       'пудель',           true,           true),
                   ('dog',       'корги',            false,          true),

                   ('cat',       'дворняжка',        true,           false),
                   ('cat',       'сфинкс',           false,          true),
                   ('cat',       'бенгал',           true,           true),
                   ('cat',       'британец',         false,          true)
