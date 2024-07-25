CREATE TABLE IF NOT EXISTS Config_Schema
(
    id        SERIAL PRIMARY KEY,

    name      VARCHAR(255) UNIQUE,
    value     VARCHAR(255),

    update_at TIMESTAMP,
    create_at TIMESTAMP
);


CREATE TABLE IF NOT EXISTS Staff_Role_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    name      VARCHAR(255),
    rule      VARCHAR(255),

    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Staff_Role_Schema_Rule
(
    staff_role_id UUID REFERENCES Staff_Role_Schema (id),
    rules      VARCHAR(255)
);

INSERT INTO Staff_Role_Schema(name, update_at, create_at) VALUES ('Owner', now(), now());


CREATE TABLE IF NOT EXISTS Staff_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    firstname   VARCHAR(255),
    lastname    VARCHAR(255),
    patronymic  VARCHAR(255),
    avatar_url  VARCHAR(255),
    birthday    DATE,

    role_id   UUID REFERENCES Staff_Role_Schema (id),

    password VARCHAR(255),
    is_banned BOOLEAN default false,

    email         VARCHAR(255),
    email_is_active BOOLEAN DEFAULT FALSE,
    phone         VARCHAR(255),
    phone_is_active BOOLEAN DEFAULT FALSE,

    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS Patient_Schema
(
    id        UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    firstname   VARCHAR(255),
    lastname    VARCHAR(255),
    patronymic  VARCHAR(255),
    avatar_url  VARCHAR(255),
    birthday    DATE,

    password VARCHAR(255),
    is_banned BOOLEAN default false,

    email         VARCHAR(255),
    email_is_active BOOLEAN DEFAULT FALSE,
    phone         VARCHAR(255),
    phone_is_active BOOLEAN DEFAULT FALSE,

    update_at TIMESTAMP,
    create_at TIMESTAMP
);
