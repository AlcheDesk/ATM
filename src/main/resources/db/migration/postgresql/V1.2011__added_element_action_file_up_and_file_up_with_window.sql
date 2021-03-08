--add data
INSERT INTO element_action (id,name) VALUES (22,'FileUp') ON CONFLICT DO NOTHING;
INSERT INTO element_action (id,name) VALUES (23,'FileUpWithWindow') ON CONFLICT DO NOTHING;

--add link
INSERT INTO element_type_element_action_link (element_type_id, element_action_id) VALUES (8,22) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id, element_action_id) VALUES (8,23) ON CONFLICT DO NOTHING;