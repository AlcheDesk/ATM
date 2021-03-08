-- update data from element type instruction option link
DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebRadio') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT');

DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebDropdown') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY');

-- update data from instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link(
	id, instruction_action_id, instruction_option_id)
	VALUES (141, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));