INSERT INTO log_level (name) VALUES ('N/A') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('PASS') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('FAIL') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('WIP') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('ERROR') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('INFO') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('WARNING') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('DEBUG') ON CONFLICT DO NOTHING;
INSERT INTO log_level (name) VALUES ('TRACE') ON CONFLICT DO NOTHING;