INSERT INTO element_type_instruction_option_link(instruction_option_id, element_type_id)
VALUES 
(
(SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'), 
(SELECT id FROM element_type WHERE name = 'Redis')
);

UPDATE driver_vendor SET driver_type_id = 7 WHERE name = 'Redis';