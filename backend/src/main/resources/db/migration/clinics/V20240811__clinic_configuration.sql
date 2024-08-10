CREATE TABLE ClinicConfiguration (
    id              UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    key VARCHAR(255),
    value TEXT,

    update_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at       TIMESTAMP WITHOUT TIME ZONE DEFAULT now()

);


INSERT INTO ClinicConfiguration(key, value)
VALUES ('privateRegistration', '1');