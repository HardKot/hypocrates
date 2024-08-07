CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


CREATE TABLE Clinic
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    update_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at  TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    code       VARCHAR(255),
    name       VARCHAR(255),
    address    VARCHAR(255),
    status     VARCHAR(255),
    avatar_url VARCHAR(255),
    email      VARCHAR(255)
);

CREATE TABLE ConfirmedCode
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    code      VARCHAR(255),
    update_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    create_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);

CREATE TABLE Token
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    token     VARCHAR(255),
    create_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
