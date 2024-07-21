CREATE TABLE IF NOT EXISTS Config_Schema
(
    id        SERIAL PRIMARY KEY,

    name      VARCHAR(255) UNIQUE,
    value     VARCHAR(255),

    update_at TIMESTAMP,
    create_at TIMESTAMP
);


CREATE TABLE IF NOT EXISTS App_User
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    firstname   VARCHAR(255),
    lastname    VARCHAR(255),
    patronymic  VARCHAR(255),
    avatar_url  VARCHAR(255),
    birthday    DATE,

    security_id INTEGER,
    contact_id  INTEGER,

    update_at   TIMESTAMP,
    create_at   TIMESTAMP
);


CREATE TABLE IF NOT EXISTS Staff_Role_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    name      VARCHAR(255),
    rule      VARCHAR(255),

    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Staff_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id   UUID REFERENCES App_User (id),

    role_id   UUID REFERENCES Staff_Role_Schema (id),
    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Patient_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id   UUID REFERENCES App_User (id),

    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS User_Contact
(
    id            SERIAL PRIMARY KEY,
    email         VARCHAR(255),
    email_is_active BOOLEAN DEFAULT FALSE,
    phone         VARCHAR(255),
    phone_is_active BOOLEAN DEFAULT FALSE

);


CREATE TABLE IF NOT EXISTS User_Security
(
    id       SERIAL PRIMARY KEY,
    password VARCHAR(255),
    is_banned BOOLEAN default false
);

