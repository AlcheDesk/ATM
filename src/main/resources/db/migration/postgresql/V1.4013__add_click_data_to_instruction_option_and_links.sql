-- add data to instruction option
INSERT INTO instruction_option(
	id, name, is_predefined, is_value_required, comment, is_active)
	VALUES (14, 'BTN_AlERT_CLICK_CANCEL', true, false, '点击按钮出现弹窗后，自动点击弹窗的取消按钮', true);

-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
70,
(SELECT id FROM element_type WHERE name = 'WebButton'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
72,
(SELECT id FROM element_type WHERE name = 'WebLink'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
73,
(SELECT id FROM element_type WHERE name = 'WebLink'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
144,
(SELECT id FROM instruction_action WHERE name = 'Click'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
145,
(SELECT id FROM instruction_action WHERE name = 'Click'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
146,
(SELECT id FROM instruction_action WHERE name = 'DoubleClick'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
147,
(SELECT id FROM instruction_action WHERE name = 'DoubleClick'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
148,
(SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_CANCEL')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
149,
(SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), 
(SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT')
);