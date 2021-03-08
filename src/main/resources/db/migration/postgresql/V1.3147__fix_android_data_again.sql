-- delete old data

DELETE FROM instruction_type_instruction_action_link WHERE instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'AndroidFunction');

DELETE FROM element_type_instruction_action_link WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'AndroidButton');

DELETE FROM element_type_instruction_action_link WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'AndroidTextbox');

DELETE FROM element_type WHERE name = 'AndroidButton';

DELETE FROM element_type WHERE name = 'AndroidTextbox';

DELETE FROM driver_type_instruction_type_link WHERE driver_type_id = (SELECT id FROM driver_type WHERE name = 'Android') and
instruction_type_id = (SELECT id FROM instruction_type WHERE name = 'AndroidFunction');

DELETE FROM instruction_type WHERE name = 'AndroidFunction';

DELETE FROM driver_type WHERE name = 'Android';

-- add new data

-- add data to instruction type
UPDATE instruction_type SET is_active = true WHERE name='AppFunction';

-- add data to driver type instruction type link
INSERT INTO driver_type_instruction_type_link(
	id, driver_type_id, instruction_type_id)
	VALUES (6, (SELECT id FROM driver_type WHERE name = 'APP'), (SELECT id FROM instruction_type WHERE name = 'AppFunction'));

-- add data to element type
INSERT INTO element_type(
	id, name, is_active, is_driver)
	VALUES (16, 'AppButton', true, true);

INSERT INTO element_type(
	id, name, is_active, is_driver)
	VALUES (17, 'AppTextbox', true, true);

-- add data to instruction action links
INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (43, (SELECT id FROM instruction_type WHERE name = 'AppFunction'), (SELECT id FROM instruction_action WHERE name = 'Click'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (44, (SELECT id FROM instruction_type WHERE name = 'AppFunction'), (SELECT id FROM instruction_action WHERE name = 'DoubleClick'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (45, (SELECT id FROM instruction_type WHERE name = 'AppFunction'), (SELECT id FROM instruction_action WHERE name = 'LongPress'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (46, (SELECT id FROM instruction_type WHERE name = 'AppFunction'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));

INSERT INTO instruction_type_instruction_action_link(
	id, instruction_type_id, instruction_action_id)
	VALUES (47, (SELECT id FROM instruction_type WHERE name = 'AppFunction'), (SELECT id FROM instruction_action WHERE name = 'Enter'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (326, (SELECT id FROM element_type WHERE name = 'AppButton'), (SELECT id FROM instruction_action WHERE name = 'Click'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (327, (SELECT id FROM element_type WHERE name = 'AppButton'), (SELECT id FROM instruction_action WHERE name = 'DoubleClick'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (328, (SELECT id FROM element_type WHERE name = 'AppButton'), (SELECT id FROM instruction_action WHERE name = 'LongPress'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (329, (SELECT id FROM element_type WHERE name = 'AppButton'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (330, (SELECT id FROM element_type WHERE name = 'AppTextbox'), (SELECT id FROM instruction_action WHERE name = 'Enter'));

INSERT INTO element_type_instruction_action_link(
	id, element_type_id, instruction_action_id)
	VALUES (331, (SELECT id FROM element_type WHERE name = 'AppTextbox'), (SELECT id FROM instruction_action WHERE name = 'SwipeByDirection'));

-- add data to driver

INSERT INTO driver(
	id, name, comment, is_active, is_default, driver_type_id, vendor_name, is_predefined, is_deleted, execution_count)
	VALUES (4, 'Android', 'Default Android settings', true, true, 2, 'Android', true, false, 0);