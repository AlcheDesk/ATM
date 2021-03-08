INSERT INTO element_type_element_locator_type_link (element_type_id, element_locator_type_id) 
VALUES 
((select id from element_type where name = 'link'), (select id from element_locator_type where name = 'linkText')) 
ON CONFLICT DO NOTHING;