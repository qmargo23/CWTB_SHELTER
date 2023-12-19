-- liquibase formatted sql

-- changeset yuzu:1
CREATE TABLE telegram_user (
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    bot_state SMALLINT NOT NULL
)

-- changeset yuzu:2
ALTER TABLE telegram_user
    ADD COLUMN shelter_user_id BIGINT UNIQUE;
ALTER TABLE telegram_user
    ADD CONSTRAINT fk_telegram_user_shelter_user
    FOREIGN KEY (shelter_user_id)
    REFERENCES shelter_user;