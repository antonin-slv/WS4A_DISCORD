-- Table des utilisateurs
CREATE TABLE users (
    id_user SERIAL PRIMARY KEY,
    email TEXT NOT NULL UNIQUE,
    login TEXT NOT NULL UNIQUE,
    pwd BYTEA NOT NULL
);

-- Table des sujets (serveurs)
CREATE TABLE subjects (
    id_sub SERIAL PRIMARY KEY,
    subject TEXT NOT NULL
);

-- Table des canaux (channels)
CREATE TABLE channels (
    id_chan SERIAL PRIMARY KEY,
    channel TEXT NOT NULL,
    subject_id INTEGER NOT NULL
);

ALTER TABLE channels
    ADD CONSTRAINT fk_channel_subject FOREIGN KEY (subject_id) REFERENCES subjects(id_sub) ON DELETE CASCADE;

-- Table des invitations
CREATE TABLE invites (
    invite_id BIGINT PRIMARY KEY,
    remaining_use INTEGER NOT NULL,
    id_sub INTEGER NOT NULL
);

ALTER TABLE invites
    ADD CONSTRAINT fk_invite_subject FOREIGN KEY (id_sub) REFERENCES subjects(id_sub) ON DELETE CASCADE;

-- Table des messages
CREATE TABLE messages (
    id_msg SERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    send_date DATE NOT NULL DEFAULT CURRENT_DATE,
    id_sender INTEGER,
    id_receiver INTEGER, -- NULL SI C'EST UN MESSAGE DANS UN CANAL
    id_channel INTEGER, -- NULL SI C'EST UN MESSAGE PRIVÉ
    response_to INTEGER
);

ALTER TABLE messages ADD CONSTRAINT fk_msg_user FOREIGN KEY (id_sender) REFERENCES users(id_user) ON DELETE CASCADE;
ALTER TABLE messages ADD CONSTRAINT fk_msg_user_1 FOREIGN KEY (id_receiver) REFERENCES users(id_user) ON DELETE SET NULL;
ALTER TABLE messages ADD CONSTRAINT fk_msg_channel FOREIGN KEY (id_channel) REFERENCES channels(id_chan) ON DELETE CASCADE;
ALTER TABLE messages ADD CONSTRAINT fk_msg_response FOREIGN KEY (response_to) REFERENCES messages(id_msg) ON DELETE SET NULL;

-- Réactions aux messages
CREATE TABLE react_to (
    id_user INTEGER,
    id_msg INTEGER,
    reaction TEXT,
    PRIMARY KEY(id_user, id_msg)
);

ALTER TABLE react_to
    ADD CONSTRAINT fk_react_user FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE;

ALTER TABLE react_to
    ADD CONSTRAINT fk_react_message FOREIGN KEY (id_msg) REFERENCES messages(id_msg) ON DELETE CASCADE;

-- Appartenance des utilisateurs aux sujets (serveurs)
CREATE TABLE user_in_subject (
    id_user INTEGER,
    id_sub INTEGER,
    is_admin BOOLEAN NOT NULL,
    PRIMARY KEY (id_user, id_sub)
);

ALTER TABLE user_in_subject
    ADD CONSTRAINT fk_uis_user FOREIGN KEY (id_user) REFERENCES users(id_user) ON DELETE CASCADE;

ALTER TABLE user_in_subject
    ADD CONSTRAINT fk_uis_subject FOREIGN KEY (id_sub) REFERENCES subjects(id_sub) ON DELETE CASCADE;
