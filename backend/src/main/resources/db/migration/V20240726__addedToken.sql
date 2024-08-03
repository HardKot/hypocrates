CREATE TABLE Token_Schema (
    id        SERIAL PRIMARY KEY,
    token  VARCHAR(255) UNIQUE ,
    create_at TIMESTAMP
);