CREATE TABLE IF NOT EXISTS UserContact
(
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    emailIsActive BOOLEAN DEFAULT FALSE,
    phone VARCHAR(255),
    phoneIsActive BOOLEAN DEFAULT FALSE

);


ALTER TABLE AppUser ADD contact_id INTEGER REFERENCES UserContact (id);

CREATE TABLE IF NOT EXISTS UserSecurity (
    id SERIAL PRIMARY KEY,
    password VARCHAR(255),
    isBanned BOOLEAN default false
);

ALTER TABLE AppUser ADD security_id INTEGER REFERENCES UserSecurity (id);
