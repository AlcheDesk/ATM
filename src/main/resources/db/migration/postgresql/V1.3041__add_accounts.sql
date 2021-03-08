ALTER TABLE account ADD CONSTRAINT accout_uc_username UNIQUE (username);

INSERT INTO account(username, password, type) VALUES ('admin', 'admin', 'admin') ON CONFLICT DO NOTHING;
INSERT INTO account(username, password, type) VALUES ('user', 'user', 'user') ON CONFLICT DO NOTHING;
INSERT INTO account(username, password, type) VALUES ('view', 'view', 'view') ON CONFLICT DO NOTHING;