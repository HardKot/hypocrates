CREATE TABLE IF NOT EXISTS ClinicSchema
(
    id            SERIAL PRIMARY KEY,

    name          VARCHAR(255),
    address       VARCHAR(255),
    avatar_url    VARCHAR(255),

    parent_clinic INTEGER null REFERENCES ClinicSchema (id),

    update_at     TIMESTAMP,
    create_at     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS AppUser
(
    id         SERIAL PRIMARY KEY,

    firstname  VARCHAR(255),
    lastname   VARCHAR(255),
    patronymic VARCHAR(255),
    birthday   DATE,

    update_at  TIMESTAMP,
    create_at  TIMESTAMP
);


CREATE TABLE IF NOT EXISTS StaffRoleSchema
(
    id        SERIAL PRIMARY KEY,

    name      VARCHAR(255),
    rule      VARCHAR(255),

    clinic_id INTEGER REFERENCES ClinicSchema (id),
    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS StaffSchema
(
    id        SERIAL PRIMARY KEY,
    user_id   INTEGER REFERENCES AppUser (id),

    clinic_id INTEGER REFERENCES ClinicSchema (id),
    role_id   INTEGER REFERENCES StaffRoleSchema (id),
    update_at TIMESTAMP,
    create_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS PatientSchema
(
    id        SERIAL PRIMARY KEY,
    user_id   INTEGER REFERENCES AppUser (id),

    clinic_id INTEGER REFERENCES ClinicSchema (id),
    update_at TIMESTAMP,
    create_at TIMESTAMP
);
