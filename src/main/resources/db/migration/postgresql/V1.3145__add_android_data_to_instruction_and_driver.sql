-- add data to driver type
INSERT INTO driver_type(id, name, is_multiselectable, is_active, is_predefined) 
VALUES 
(7, 'Android', false, true, false);

-- add data to instruction type
INSERT INTO instruction_type(id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) 
VALUES 
(13, 'AndroidFunction', true, true, false, 7, true, null, null);

-- add data to driver type instruction type link
INSERT INTO driver_type_instruction_type_link(
	id, driver_type_id, instruction_type_id)
	VALUES (6, 7, 13);

-- add data to instruction action
INSERT INTO instruction_action(
	id, name, is_active, is_predefined)
	VALUES (42, 'LongPress', true, true);

INSERT INTO instruction_action(
	id, name, is_active, is_predefined)
	VALUES (43, 'SwipeByDirection', true, true);

-- add data to element type
INSERT INTO element_type(
	id, name, is_active, is_driver)
	VALUES (16, 'AndroidButton', true, true);

INSERT INTO element_type(
	id, name, is_active, is_driver)
	VALUES (17, 'AndroidTextbox', true, true);

-- add data to instruction action links
INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (43, (SELECT id FROM instruction_type WHERE name = 'AndroidFunction'), (SELECT id FROM instruction_action WHERE name = 'Click'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (44, (SELECT id FROM instruction_type WHERE name = 'AndroidFunction'), (SELECT id FROM instruction_action WHERE name = 'DoubleClick'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (45, (SELECT id FROM instruction_type WHERE name = 'AndroidFunction'), (SELECT id FROM instruction_action WHERE name = 'LongPress'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (46, (SELECT id FROM instruction_type WHERE name = 'AndroidFunction'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (47, (SELECT id FROM instruction_type WHERE name = 'AndroidFunction'), (SELECT id FROM instruction_action WHERE name = 'Enter'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (326, (SELECT id FROM element_type WHERE name = 'AndroidButton'), (SELECT id FROM instruction_action WHERE name = 'Click'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (327, (SELECT id FROM element_type WHERE name = 'AndroidButton'), (SELECT id FROM instruction_action WHERE name = 'DoubleClick'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (328, (SELECT id FROM element_type WHERE name = 'AndroidButton'), (SELECT id FROM instruction_action WHERE name = 'LongPress'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (329, (SELECT id FROM element_type WHERE name = 'AndroidButton'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (330, (SELECT id FROM element_type WHERE name = 'AndroidTextbox'), (SELECT id FROM instruction_action WHERE name = 'Enter'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (331, (SELECT id FROM element_type WHERE name = 'AndroidTextbox'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));