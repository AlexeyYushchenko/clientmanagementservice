--liquibase formatted sql

--changeset ayushchenko:1
CREATE TABLE client_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:2
CREATE TABLE IF NOT EXISTS client_status_localization
(
    client_status_id INT REFERENCES client_status (id) ON DELETE CASCADE,
    language_code    VARCHAR(5),
    localized_name   VARCHAR(255) NOT NULL,
    PRIMARY KEY (client_status_id, language_code)
);

--changeset ayushchenko:3
CREATE TABLE IF NOT EXISTS business_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:4
CREATE TABLE IF NOT EXISTS business_type_localization
(
    business_type_id      INT REFERENCES business_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (business_type_id, language_code)
);

--changeset ayushchenko:5
CREATE TABLE IF NOT EXISTS industry_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    created_at  TIMESTAMP DEFAULT NOW(),
    modified_at TIMESTAMP DEFAULT NOW(),
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

--changeset ayushchenko:6
CREATE TABLE IF NOT EXISTS industry_type_localization
(
    industry_type_id      INT REFERENCES industry_type (id) ON DELETE CASCADE,
    language_code         VARCHAR(5),
    localized_name        VARCHAR(255) NOT NULL,
    localized_description TEXT,
    PRIMARY KEY (industry_type_id, language_code)
);

--changeset ayushchenko:7
CREATE TABLE IF NOT EXISTS client
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(50)                       NOT NULL UNIQUE,
    full_name        VARCHAR(100),
    status_id        INT REFERENCES client_status (id) NOT NULL,
    business_type_id INT REFERENCES business_type (id) NOT NULL,
    industry_type_id INT REFERENCES industry_type (id) NOT NULL,
    address          VARCHAR(255),
    created_at       TIMESTAMP DEFAULT NOW(),
    modified_at      TIMESTAMP DEFAULT NOW(),
    created_by       VARCHAR(64),
    modified_by      VARCHAR(64)
);

--changeset ayushchenko:8
CREATE TABLE IF NOT EXISTS client_balance
(
    id          BIGSERIAL PRIMARY KEY,
    client_id   INT NOT NULL REFERENCES client (id) ON DELETE CASCADE,
    currency_id INT NOT NULL,
    balance     DECIMAL(15, 2),
    UNIQUE (client_id, currency_id)
);

--changeset ayushchenko:9
CREATE TABLE IF NOT EXISTS client_preferences
(
    id                    SERIAL PRIMARY KEY,
    client_id             INT NOT NULL REFERENCES client (id) ON DELETE CASCADE,
    language              VARCHAR(50),
    timezone              VARCHAR(50),
    notifications_enabled BOOLEAN,
    created_at            TIMESTAMP DEFAULT NOW(),
    modified_at           TIMESTAMP DEFAULT NOW()
);