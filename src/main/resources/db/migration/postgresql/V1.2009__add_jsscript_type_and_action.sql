UPDATE driver SET comment = 'Default Chrome settings' WHERE name = 'Chrome' AND active = true AND default_flag = false;

--add data
INSERT INTO element_action (id,name) VALUES (19,'Execute') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (id,name) VALUES (8,'Script') ON CONFLICT DO NOTHING;
INSERT INTO element_type (id,name) VALUES (13,'JavaScript') ON CONFLICT DO NOTHING;

--add link
INSERT INTO element_type_element_action_link (element_type_id, element_action_id) VALUES (13,19) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_locator_type_link (element_type_id, element_locator_type_id) VALUES (13,8) ON CONFLICT DO NOTHING;