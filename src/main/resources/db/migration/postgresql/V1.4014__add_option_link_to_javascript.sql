-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
74,
(SELECT id FROM element_type WHERE name = 'JavaScript'), 
(SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT')
);