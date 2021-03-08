-- add data to instruction_option
INSERT INTO instruction_option(
	id, name, is_predefined, is_value_required, comment, is_active)
	VALUES (15, 'SAVE_BROWSER_COOKIE', true, false, '保存当前浏览器中的Cookie，用例范围内的后续接口调用会使用这些Cookie', true);

-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebButton'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebTextbox'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebRadio'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebLink'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebCheckbox'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebDropdown'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebFile'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO element_type_instruction_option_link (element_type_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM element_type WHERE name = 'WebFrame'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Click'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'DoubleClick'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'IsDisable'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'IsEnable'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Count'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Exist'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'NonExist'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'MoveToElement'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Enter'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Match'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'InputInPageText'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'Clear'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'FileUpload'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'FileDownload'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);

INSERT INTO instruction_action_instruction_option_link (instruction_action_id,instruction_option_id) 
VALUES 
(
(SELECT id FROM instruction_action WHERE name = 'SwitchTo'), 
(SELECT id FROM instruction_option WHERE name = 'SAVE_BROWSER_COOKIE')
);