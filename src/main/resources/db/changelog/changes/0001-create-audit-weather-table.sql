--liquibase formatted sql

--changeset audit-weather:1

CREATE TABLE audit_weather
(
    id            INT                   NOT NULL    AUTO_INCREMENT,
    city          VARCHAR(255)          NOT NULL,
    country       VARCHAR(255)          NOT NULL,
    temperature   NUMERIC(5, 2)         NOT NULL,
    timestamp     TIMESTAMP             NOT NULL,
    type          VARCHAR(20)           NOT NULL,
    PRIMARY KEY (id)
);

