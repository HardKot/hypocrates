CREATE SEQUENCE IF NOT EXISTS clinic_document_event_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS clinic_row_configuration_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS medical_card_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS medical_protocol_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS patient_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS security_token_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS staff_role_seq START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS staff_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE clinic_document_event
(
    id            BIGINT NOT NULL,
    update_at     TIMESTAMP WITHOUT TIME ZONE,
    create_at     TIMESTAMP WITHOUT TIME ZONE,
    document_name VARCHAR(255),
    CONSTRAINT pk_clinicdocumentevent PRIMARY KEY (id)
);

CREATE TABLE clinic_row_configuration
(
    id        BIGINT NOT NULL,
    update_at TIMESTAMP WITHOUT TIME ZONE,
    create_at TIMESTAMP WITHOUT TIME ZONE,
    key       VARCHAR(255),
    value     VARCHAR(255),
    CONSTRAINT pk_clinicrowconfiguration PRIMARY KEY (id)
);

CREATE TABLE medical_card
(
    id         BIGINT NOT NULL,
    update_at  TIMESTAMP WITHOUT TIME ZONE,
    create_at  TIMESTAMP WITHOUT TIME ZONE,
    number     VARCHAR(255),
    patient_id BIGINT,
    staff_id   BIGINT,
    CONSTRAINT pk_medicalcard PRIMARY KEY (id)
);

CREATE TABLE medical_protocol
(
    id        BIGINT NOT NULL,
    update_at TIMESTAMP WITHOUT TIME ZONE,
    create_at TIMESTAMP WITHOUT TIME ZONE,
    staff_id  BIGINT,
    CONSTRAINT pk_medicalprotocol PRIMARY KEY (id)
);

CREATE TABLE patient
(
    id                BIGINT  NOT NULL,
    firstname         VARCHAR(255),
    lastname          VARCHAR(255),
    patronymic        VARCHAR(255),
    birthday          TIMESTAMP WITHOUT TIME ZONE,
    avatar_url        VARCHAR(255),
    email             VARCHAR(255),
    phone             VARCHAR(255),
    email_is_active   BOOLEAN NOT NULL,
    phone_is_active   BOOLEAN NOT NULL,
    password          VARCHAR(255),
    update_at         TIMESTAMP WITHOUT TIME ZONE,
    create_at         TIMESTAMP WITHOUT TIME ZONE,
    global_patient_id VARCHAR(255),
    policy_number     VARCHAR(255),
    gender            SMALLINT,
    CONSTRAINT pk_patient PRIMARY KEY (id)
);

CREATE TABLE security_token
(
    id        BIGINT  NOT NULL,
    update_at TIMESTAMP WITHOUT TIME ZONE,
    create_at TIMESTAMP WITHOUT TIME ZONE,
    token     VARCHAR(255),
    is_banned BOOLEAN NOT NULL,
    ip        VARCHAR(255),
    staff_id  BIGINT,
    CONSTRAINT pk_securitytoken PRIMARY KEY (id)
);

CREATE TABLE staff
(
    id               BIGINT NOT NULL,
    update_at        TIMESTAMP WITHOUT TIME ZONE,
    create_at        TIMESTAMP WITHOUT TIME ZONE,
    role_id          BIGINT,
    invited_staff_id BIGINT,
    global_staff_id  VARCHAR(255),
    date_banned      TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_staff PRIMARY KEY (id)
);

CREATE TABLE staff_role
(
    id        BIGINT NOT NULL,
    update_at TIMESTAMP WITHOUT TIME ZONE,
    create_at TIMESTAMP WITHOUT TIME ZONE,
    name      VARCHAR(255),
    rules     SMALLINT,
    CONSTRAINT pk_staffrole PRIMARY KEY (id)
);

ALTER TABLE medical_card
    ADD CONSTRAINT FK_MEDICALCARD_ON_PATIENT FOREIGN KEY (patient_id) REFERENCES patient (id);

ALTER TABLE medical_card
    ADD CONSTRAINT FK_MEDICALCARD_ON_STAFF FOREIGN KEY (staff_id) REFERENCES staff (id);

ALTER TABLE medical_protocol
    ADD CONSTRAINT FK_MEDICALPROTOCOL_ON_STAFF FOREIGN KEY (staff_id) REFERENCES staff (id);

ALTER TABLE security_token
    ADD CONSTRAINT FK_SECURITYTOKEN_ON_STAFF FOREIGN KEY (staff_id) REFERENCES staff (id);

ALTER TABLE staff
    ADD CONSTRAINT FK_STAFF_ON_INVITEDSTAFF FOREIGN KEY (invited_staff_id) REFERENCES staff (id);

ALTER TABLE staff
    ADD CONSTRAINT FK_STAFF_ON_ROLE FOREIGN KEY (role_id) REFERENCES staff_role (id);