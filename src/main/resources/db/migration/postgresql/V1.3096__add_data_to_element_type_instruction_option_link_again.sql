SELECT setval('element_type_instruction_option_link_id_seq', 60, true);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'REST_API'), 
(SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH')
);