CREATE TABLE meteo_alert
(
    id SERIAL PRIMARY KEY,
    level SMALLINT NOT NULL,
    categories VARCHAR[] NOT NULL DEFAULT '{}',
    creation_date TEXT NOT NULL,
    description TEXT NOT NULL,
    external_id TEXT NOT NULL,
    media VARCHAR[]
)