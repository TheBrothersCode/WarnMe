CREATE TABLE meteo_alert_origin
(
    alert_origin_id  BIGSERIAL PRIMARY KEY,
    source_name      TEXT NOT NULL,
    source_author_id TEXT NOT NULL,
    original_id      TEXT
);

ALTER TABLE meteo_alert
    ADD COLUMN alert_origin_id BIGINT;

ALTER TABLE meteo_alert
    ADD CONSTRAINT fk_meteoalerts_alertsorigin
        FOREIGN KEY  (alert_origin_id)
            references meteo_alert_origin;

ALTER TABLE meteo_alert_categories
    ADD CONSTRAINT fk_categories_meteoalerts
        FOREIGN KEY  (meteo_alert_id)
            references meteo_alert;

ALTER TABLE meteo_alert_media
    ADD CONSTRAINT fk_media_meteoalerts
        FOREIGN KEY  (meteo_alert_id)
            references meteo_alert;