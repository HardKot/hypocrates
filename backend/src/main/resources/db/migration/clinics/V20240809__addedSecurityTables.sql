CREATE TABLE Token
(
    id        UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    token     VARCHAR(255),
    create_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);
