INSERT INTO instruction_type(
	id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields)
	VALUES (13, 'SOAP_API', true, true, false, 5, true, null, '@.input');

INSERT INTO instruction_type_element_type_link(id, instruction_type_id, element_type_id)
VALUES 
(
14,
(SELECT id FROM instruction_type WHERE name = 'SOAP_API'), 
(SELECT id FROM element_type WHERE name = 'SOAP_API')
);

UPDATE element_type SET is_active=true WHERE name = 'SOAP_API';