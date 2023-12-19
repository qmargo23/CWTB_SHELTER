-- liquibase formatted sql

-- changeset nik:1
CREATE TABLE report (
    id BIGINT PRIMARY KEY,
    photo VARCHAR(255),
    local_date DATE,
    report_text_under_photo TEXT
)