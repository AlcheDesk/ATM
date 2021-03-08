INSERT INTO element_type (name) VALUES ('sql') ON CONFLICT DO NOTHING;
INSERT INTO element_locator_type (name) VALUES ('jdbc') ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_locator_type_link (element_type_id,element_locator_type_id) 
VALUES ((select id from element_type where name = 'sql'),(select id from element_locator_type where name = 'jdbc')) ON CONFLICT DO NOTHING;