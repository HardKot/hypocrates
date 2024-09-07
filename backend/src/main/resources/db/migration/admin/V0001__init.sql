CREATE TABLE clinic
(
    id         UUID NOT NULL,
    update_at  TIMESTAMP WITHOUT TIME ZONE,
    create_at  TIMESTAMP WITHOUT TIME ZONE,
    code       VARCHAR(255),
    name       VARCHAR(255),
    address    VARCHAR(255),
    avatar_url VARCHAR(255),
    email      VARCHAR(255),
    status     SMALLINT,
    CONSTRAINT pk_clinic PRIMARY KEY (id)
);

CREATE TABLE subscription
(
    id             UUID    NOT NULL,
    update_at      TIMESTAMP WITHOUT TIME ZONE,
    create_at      TIMESTAMP WITHOUT TIME ZONE,
    name           VARCHAR(255),
    price          INTEGER NOT NULL,
    duration       BIGINT,
    is_active      BOOLEAN NOT NULL,
    functional_set SMALLINT,
    CONSTRAINT pk_subscription PRIMARY KEY (id)
);