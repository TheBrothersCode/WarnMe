CREATE TABLE meteo_alert
(
    id            BIGSERIAL PRIMARY KEY,
    level         INTEGER   NOT NULL,
    categories    VARCHAR[] NOT NULL DEFAULT '{}',
    creation_date TEXT      NOT NULL,
    description   TEXT      NOT NULL,
    external_id   TEXT      NOT NULL,
    media         VARCHAR[]
);