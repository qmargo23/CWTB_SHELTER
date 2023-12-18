-- liquibase formatted sql

-- changeset yuzu:1
CREATE TABLE telegram_user (
    id BIGSERIAL PRIMARY KEY,
    chat_id BIGINT NOT NULL,
    bot_state SMALLINT NOT NULL
)

