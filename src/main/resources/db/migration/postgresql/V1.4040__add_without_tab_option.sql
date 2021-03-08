-- add data to instruction_option
INSERT INTO instruction_option(
	id, name, is_predefined, is_value_required, comment, is_active)
	VALUES (16, 'WITHOUT_TAB', true, false, 'Enter动作之后不隐式输入TAB', true);

-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebTextbox'), 
(SELECT id FROM instruction_option WHERE name = 'WITHOUT_TAB')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Enter'), 
(SELECT id FROM instruction_option WHERE name = 'WITHOUT_TAB')
);