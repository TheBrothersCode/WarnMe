CREATE TABLE meteo_alert_categories
(
    meteo_alert_id BIGINT NOT NULL,
    categories     VARCHAR(255)
);

create table meteo_alert_media
(
    meteo_alert_id BIGINT NOT NULL,
    media          VARCHAR(255)
)