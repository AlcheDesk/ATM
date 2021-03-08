-- add data to instruction action
INSERT INTO instruction_action(
	id, name, is_active, is_predefined)
	VALUES (53, 'NonOptionExist', true, true);

-- add data to instruction type instruction action link
INSERT INTO instruction_type_instruction_action_link (id,instruction_type_id,instruction_action_id) 
VALUES 
(
50,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM instruction_action WHERE name = 'NonOptionExist')
);

-- add data to element type instruction action link
INSERT INTO element_type_instruction_action_link (id,element_type_id,instruction_action_id) 
VALUES 
(
333,
(SELECT id FROM element_type WHERE name = 'WebDropdown'), 
(SELECT id FROM instruction_action WHERE name = 'NonOptionExist')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
150,
(SELECT id FROM instruction_action WHERE name = 'NonOptionExist'), 
(SELECT id FROM instruction_option WHERE name = 'INS_IGNORE')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
151,
(SELECT id FROM instruction_action WHERE name = 'NonOptionExist'), 
(SELECT id FROM instruction_option WHERE name = 'RES_IGNORE')
);


INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
152,
(SELECT id FROM instruction_action WHERE name = 'NonOptionExist'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);