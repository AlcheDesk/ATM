INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebLink'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK')
);