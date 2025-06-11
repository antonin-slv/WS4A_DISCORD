TRUNCATE users CASCADE;
ALTER SEQUENCE users_id_user_seq RESTART WITH 1;
INSERT INTO users (email, login, pwd)
VALUES
    ('user@mail.com','user',sha256('pwd')),
    ('nino@mail.com','nino',sha256('pwd'));

TRUNCATE Subject CASCADE;
ALTER SEQUENCE subject_id_sub_seq RESTART WITH 1;
INSERT INTO Subject (subject, is_public)
VALUES
    ('General', TRUE),
    ('Annonces', TRUE),
    ('Support', FALSE);

TRUNCATE channel CASCADE;
ALTER SEQUENCE channel_id_chan_seq RESTART WITH 1;
INSERT INTO Channel (channel, subject_id)
VALUES
    ('general', 1),
    ('spautted', 1),
    ('Support general', 3),
    ('Support technique',3),
    ('Annonces dev', 2),
    ('Annonces admin', 2);

TRUNCATE Invite CASCADE;
INSERT INTO Invite (invite_id, remaining_use, id_sub)
VALUES
    (666666, 100000, 1);

TRUNCATE user_in_subject CASCADE;
INSERT INTO user_in_subject (id_user, id_sub, is_admin)
VALUES
    (1, 1, TRUE),
    (1, 2, TRUE),
    (2, 1, FALSE),
    (2, 3, TRUE);

TRUNCATE message CASCADE;
ALTER SEQUENCE message_id_msg_seq RESTART WITH 1;
INSERT INTO Message (content, send_date, id_sender, id_receiver, id_channel, response_to)
VALUES
    ('Hello world', '2023-10-01 12:00:00', 1, NULL, 1, NULL),
    ('Hello user', '2023-10-01 12:01:00', 2, NULL, 1, 1),
    ('Welcome to the server!', '2023-10-01 12:02:00', 1, NULL, 5, NULL),
    ('this is a DM test', '2023-10-01 12:03:00', 1, 2, NULL, NULL),
    ('this is a response', '2023-10-01 12:04:00', 2, 1, NULL, 4);

TRUNCATE react_to;
INSERT INTO react_to (id_user, id_msg, reaction)
VALUES
    (1, 1, '👍'),
    (2, 1, '👎'),
    (1, 2, '❤️'),
    (2, 3, '😂'),
    (1, 4, '😢'),
    (2, 5, '😡');