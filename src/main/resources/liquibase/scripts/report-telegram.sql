-- liquibase formatted sql
-- changeset report:1
CREATE TABLE report_telegram (
    id          BIGSERIAL PRIMARY KEY,
    photo       VARCHAR(255),
    local_date  DATE,
    report_text_under_photo TEXT,
    shelter_users_telegram_id BIGINT,

    FOREIGN KEY (shelter_users_telegram_id) REFERENCES shelter_user_telegram(id) ON DELETE CASCADE
)