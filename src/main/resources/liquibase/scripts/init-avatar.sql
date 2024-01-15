-- liquibase formatted sql
-- changeset qMargo:4
CREATE TABLE avatar (
    id              BIGSERIAL           PRIMARY KEY,
    file_path       VARCHAR(255),
    file_size       BIGINT,
    media_type      VARCHAR(255),
    data            OID,
    shelter_info_id BIGINT,

    FOREIGN KEY (shelter_info_id) REFERENCES shelter_info(id) ON DELETE CASCADE
);