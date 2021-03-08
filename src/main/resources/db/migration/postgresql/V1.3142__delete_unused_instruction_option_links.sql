-- delete from element type instruction option link
DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

-- delete from instruction action instruction option link
DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Wait') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Wait') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Navigate') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Navigate') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Back') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Back') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Close') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Close') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Forward') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Forward') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Refresh') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'Refresh') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE');