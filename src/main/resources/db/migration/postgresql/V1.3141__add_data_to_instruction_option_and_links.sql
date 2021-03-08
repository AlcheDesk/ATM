-- add data to instruction option
INSERT INTO instruction_option(
	id, name, is_predefined, is_value_required, comment, is_active)
	VALUES (13, 'NO_BEGIN_SCREENSHOTS', true, false, '关闭步骤的开始截图', true);

-- add data to element type instruction option link
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
62,
(SELECT id FROM element_type WHERE name = 'WebButton'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
63,
(SELECT id FROM element_type WHERE name = 'WebTextbox'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
64,
(SELECT id FROM element_type WHERE name = 'WebRadio'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
65,
(SELECT id FROM element_type WHERE name = 'WebLink'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
66,
(SELECT id FROM element_type WHERE name = 'WebCheckbox'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
67,
(SELECT id FROM element_type WHERE name = 'WebDropdown'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
68,
(SELECT id FROM element_type WHERE name = 'WebFile'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES 
(
69,
(SELECT id FROM element_type WHERE name = 'WebFrame'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

-- add data to instruction action instruction option link
INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
119,
(SELECT id FROM instruction_action WHERE name = 'DoubleClick'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
120,
(SELECT id FROM instruction_action WHERE name = 'IsDisable'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
121,
(SELECT id FROM instruction_action WHERE name = 'IsEnable'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
122,
(SELECT id FROM instruction_action WHERE name = 'Count'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
123,
(SELECT id FROM instruction_action WHERE name = 'Exist'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
124,
(SELECT id FROM instruction_action WHERE name = 'NonExist'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
125,
(SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
126,
(SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
127,
(SELECT id FROM instruction_action WHERE name = 'MoveToElement'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
128,
(SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
129,
(SELECT id FROM instruction_action WHERE name = 'Match'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
130,
(SELECT id FROM instruction_action WHERE name = 'InputInPageText'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
131,
(SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
132,
(SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
133,
(SELECT id FROM instruction_action WHERE name = 'Clear'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
134,
(SELECT id FROM instruction_action WHERE name = 'Select'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
135,
(SELECT id FROM instruction_action WHERE name = 'Check'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
136,
(SELECT id FROM instruction_action WHERE name = 'FileUpload'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
137,
(SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
138,
(SELECT id FROM instruction_action WHERE name = 'FileDownload'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
139,
(SELECT id FROM instruction_action WHERE name = 'SwitchTo'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);

INSERT INTO instruction_action_instruction_option_link (id,instruction_action_id,instruction_option_id) 
VALUES 
(
140,
(SELECT id FROM instruction_action WHERE name = 'Click'), 
(SELECT id FROM instruction_option WHERE name = 'NO_BEGIN_SCREENSHOTS')
);