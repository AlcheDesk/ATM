INSERT INTO object_type (name) VALUES ('个人项目') ON CONFLICT DO NOTHING;
INSERT INTO object_type (name) VALUES ('团队项目') ON CONFLICT DO NOTHING;

INSERT INTO element_locator_type (name) VALUES ('name') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (name) VALUES ('css') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (name) VALUES ('tag') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (name) VALUES ('xpath') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (name) VALUES ('id') ON CONFLICT DO NOTHING;