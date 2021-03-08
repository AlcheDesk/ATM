INSERT INTO instruction_type_instruction_action_link(id, instruction_type_id, instruction_action_id)
VALUES 
(
48,
(SELECT id FROM instruction_type WHERE name = 'SOAP_API'), 
(SELECT id FROM instruction_action WHERE name = 'Execute')
);