INSERT INTO element_type_element_locator_type_link(id, element_type_id, element_locator_type_id)
VALUES 
(
45,
(SELECT id FROM element_type WHERE name = 'SOAP_API'), 
(SELECT id FROM element_locator_type WHERE name = 'URL')
);