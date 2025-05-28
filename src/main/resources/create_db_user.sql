-- DROP DATABASE IF EXISTS discord_ws;
-- CREATE DATABASE discord_ws;
CREATE EXTENSION IF NOT EXISTS pgcrypto;


CREATE user  discord_app with encrypted password 'motdepasse';

-- on se met sur la base de données discord_ws
\c discord_ws;

GRANT INSERT, SELECT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO discord_app;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO discord_app;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT INSERT, SELECT, UPDATE, DELETE ON TABLES TO discord_app;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
    GRANT USAGE, SELECT ON SEQUENCES TO discord_app;