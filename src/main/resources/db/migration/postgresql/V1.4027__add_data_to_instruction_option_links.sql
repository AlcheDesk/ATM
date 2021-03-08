-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebBrowser'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Wait'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Navigate'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Back'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Close'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Forward'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Refresh'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);