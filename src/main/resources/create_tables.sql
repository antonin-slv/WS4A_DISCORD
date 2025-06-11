CREATE TABLE Users (
    id_user SERIAL PRIMARY KEY,
    email   TEXT  NOT NULL UNIQUE,
    login   TEXT  NOT NULL UNIQUE,
    pwd     BYTEA NOT NULL
);

CREATE TABLE Subject
(
    id_sub  SERIAL PRIMARY KEY,
    subject TEXT NOT NULL,
    is_public BOOLEAN NOT NULL
);

CREATE TABLE Channel
(
    id_chan    SERIAL PRIMARY KEY,
    channel    TEXT    NOT NULL,
    subject_id INTEGER NOT NULL,
    FOREIGN KEY (subject_id) REFERENCES Subject (id_sub)
);

CREATE TABLE Invite
(
    invite_id     BIGINT PRIMARY KEY,
    remaining_use INTEGER NOT NULL,
    id_sub        INTEGER NOT NULL,
    FOREIGN KEY (id_sub) REFERENCES Subject (id_sub)
);

CREATE TABLE Message
(
    id_msg             SERIAL PRIMARY KEY,
    content            TEXT NOT NULL,
    send_date          timestamp,
    id_sender  INTEGER,
    id_receiver            INTEGER,
    id_channel          INTEGER,
    response_to        INTEGER,
    FOREIGN KEY (id_receiver) REFERENCES users (id_user),
    FOREIGN KEY (id_sender) REFERENCES users (id_user),
    FOREIGN KEY (id_channel) REFERENCES Channel (id_chan),
    FOREIGN KEY (response_to) REFERENCES Message (id_msg)
);

CREATE TABLE react_to
(
    id_user  INTEGER,
    id_msg   INTEGER,
    reaction TEXT,
    PRIMARY KEY (id_user, id_msg),
    FOREIGN KEY (id_user) REFERENCES users (id_user),
    FOREIGN KEY (id_msg) REFERENCES Message (id_msg)
);

CREATE TABLE user_in_subject
(
    id_user  INTEGER,
    id_sub   INTEGER,
    is_admin BOOLEAN,
    PRIMARY KEY (id_user, id_sub),
    FOREIGN KEY (id_user) REFERENCES users (id_user),
    FOREIGN KEY (id_sub) REFERENCES Subject (id_sub)
);
