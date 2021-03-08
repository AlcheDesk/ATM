INSERT INTO element_action (name) VALUES ('Click') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Enter') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Modify') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Clear') ON CONFLICT DO NOTHING;
UPDATE element_action SET name = 'EnterTextReadonly' WHERE name = 'EntertextReadonly';
INSERT INTO element_action (name) VALUES ('EnterTextReadonly') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Select') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Check') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Download') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Upload') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Wait') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Navigate') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Exist') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Verify') ON CONFLICT DO NOTHING;
UPDATE element_action SET name = 'SwitchToFrame' WHERE name = 'SwitchToiFrame';
INSERT INTO element_action (name) VALUES ('SwitchToFrame') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Sleep') ON CONFLICT DO NOTHING;
INSERT INTO element_action (name) VALUES ('Back') ON CONFLICT DO NOTHING;

INSERT INTO element_type (name) VALUES ('button') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('link') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('dropdown') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('radio') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('checkbox') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('table') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('filedown') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('fileup') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('frame') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('browser') ON CONFLICT DO NOTHING;
INSERT INTO element_type (name) VALUES ('textbox') ON CONFLICT DO NOTHING;


INSERT INTO "group" (name,preloaded) VALUES ('DEFAULT',true) ON CONFLICT DO NOTHING;
INSERT INTO "group" (name,preloaded) VALUES ('DEV',true) ON CONFLICT DO NOTHING;
INSERT INTO "group" (name,preloaded) VALUES ('TEST',true) ON CONFLICT DO NOTHING;
INSERT INTO "group" (name,preloaded) VALUES ('CI',true) ON CONFLICT DO NOTHING;

INSERT INTO status (name) VALUES ('NEW') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('WIP') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('PASS') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('FAIL') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('ERROR') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('STOP') ON CONFLICT DO NOTHING;
INSERT INTO status (name) VALUES ('N/A') ON CONFLICT DO NOTHING;