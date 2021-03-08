-- add data to instruction action
INSERT INTO instruction_action(
	id, name, is_active, is_predefined)
	VALUES (52, 'BackFromFrame', true, true);

-- add data to instruction type instruction action link
INSERT INTO instruction_type_instruction_action_link (id,instruction_type_id,instruction_action_id) 
VALUES 
(
49,
(SELECT id FROM instruction_type WHERE name = 'WebFunction'), 
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame')
);

-- add data to element type instruction action link
INSERT INTO element_type_instruction_action_link (id,element_type_id,instruction_action_id) 
VALUES 
(
332,
(SELECT id FROM element_type WHERE name = 'WebFrame'), 
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
146,
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame'), 
(SELECT id FROM instruction_option WHERE name = 'INS_IGNORE')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
147,
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame'), 
(SELECT id FROM instruction_option WHERE name = 'RES_IGNORE')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
148,
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame'), 
(SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
149,
(SELECT id FROM instruction_action WHERE name = 'BackFromFrame'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);