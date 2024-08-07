CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE Patient
(
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    update_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    firstname       VARCHAR(255),
    lastname        VARCHAR(255),
    patronymic      VARCHAR(255),
    date            VARCHAR(255),
    avatar_url      VARCHAR(255),
    email           VARCHAR(255),
    email_is_active BOOLEAN DEFAULT false,
    phone           VARCHAR(255),
    phone_is_active BOOLEAN DEFAULT false,
    password        VARCHAR(255)
);

CREATE TABLE StaffRole
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    update_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    name      VARCHAR(255)
);

CREATE TABLE StaffRole_Rules
(
    staff_role_schema_id UUID REFERENCES StaffRole(id),
    rules                VARCHAR(255)
);

INSERT INTO StaffRole(name) VALUES ('Owner');
INSERT INTO StaffRole_Rules VALUES (
    (SELECT id FROM StaffRole WHERE name = 'Owner'),
    'STAFF_CREATE'
);

CREATE TABLE Staff
(
    id              UUID PRIMARY KEY   DEFAULT uuid_generate_v4(),
    update_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    firstname       VARCHAR(255),
    lastname        VARCHAR(255),
    patronymic      VARCHAR(255),
    birthday        TIMESTAMP WITHOUT TIME ZONE,
    avatar_url      VARCHAR(255),
    email           VARCHAR(255),
    phone           VARCHAR(255),
    email_is_active BOOLEAN DEFAULT false,
    phone_is_active BOOLEAN DEFAULT false,
    password        VARCHAR(255),
    role_id         UUID REFERENCES StaffRole(id)
);
