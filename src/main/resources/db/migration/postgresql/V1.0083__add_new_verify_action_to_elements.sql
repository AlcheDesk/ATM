INSERT INTO element_action (id,name) VALUES (20,'VerifyContains') ON CONFLICT DO NOTHING;
INSERT INTO element_action (id,name) VALUES (21,'VerifyIn') ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (3,20) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (4,20) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (5,20) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (11,20) ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (3,21) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (4,21) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (5,21) ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id) VALUES (11,21) ON CONFLICT DO NOTHING;