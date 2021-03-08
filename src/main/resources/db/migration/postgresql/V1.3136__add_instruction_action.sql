-- add data to instruction action
INSERT INTO instruction_action(
	name, is_active, is_predefined)
	VALUES ('SwitchToNewtab', true, true);

-- add data to instruction action links
INSERT INTO instruction_type_instruction_action_link(
	instruction_type_id, instruction_action_id)
	VALUES ((SELECT id FROM instruction_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'));

INSERT INTO element_type_instruction_action_link(
	element_type_id, instruction_action_id)
	VALUES ((SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'));

SELECT setval('instruction_action_instruction_option_link_id_seq', 116, true);

INSERT INTO instruction_action_instruction_option_link(
	instruction_action_id, instruction_option_id)
	VALUES ((SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));

INSERT INTO instruction_action_instruction_option_link(
	instruction_action_id, instruction_option_id)
	VALUES ((SELECT id FROM instruction_action WHERE name = 'SwitchToNewtab'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));