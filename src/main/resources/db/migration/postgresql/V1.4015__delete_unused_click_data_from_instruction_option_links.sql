-- delete from element type instruction option link
DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebLink') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK');

DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebLink') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL');

DELETE FROM element_type_instruction_option_link
	WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebLink') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT');

-- delete from instruction action instruction option link
DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'DoubleClick') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'DoubleClick') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'DoubleClick') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL');

DELETE FROM instruction_action_instruction_option_link
	WHERE instruction_action_id = (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton') and
	instruction_option_id = (SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT');