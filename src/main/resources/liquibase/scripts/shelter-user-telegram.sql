-- liquibase formatted sql
-- changeset qmargo:1
CREATE TABLE shelter_user_telegram (
    id                      BIGSERIAL               PRIMARY KEY,
    chat_id                 BIGINT                  NOT NULL,
    bot_state               SMALLINT                NOT NULL,
    user_name               VARCHAR(255),
    user_surname            VARCHAR(255),
    user_phone_number       VARCHAR(255),
    adopt_date              DATE,
    shelter_user_type       SMALLINT,
    animal_id   BIGINT      UNIQUE
)