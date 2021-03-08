-- create the instruction action instruction option table
CREATE TABLE instruction_action_instruction_option_link
    (
        id bigserial NOT NULL,
        instruction_action_id bigint NOT NULL,
        instruction_option_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT instruction_action_instruction_option_link_fk_instruction_action FOREIGN KEY
        (instruction_action_id) REFERENCES "instruction_action" ("id"),
        CONSTRAINT instruction_action_instruction_option_link_fk_instruction_option FOREIGN KEY
        (instruction_option_id) REFERENCES "instruction_option" ("id"),
        CONSTRAINT instruction_action_instruction_option_link_ix_instruction_action_instruction_option
        UNIQUE (instruction_action_id, instruction_option_id)
    );

-- insert data
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (1, (SELECT id FROM instruction_action WHERE name = 'Wait'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (2, (SELECT id FROM instruction_action WHERE name = 'Wait'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (3, (SELECT id FROM instruction_action WHERE name = 'Navigate'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (4, (SELECT id FROM instruction_action WHERE name = 'Navigate'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (5, (SELECT id FROM instruction_action WHERE name = 'Back'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (6, (SELECT id FROM instruction_action WHERE name = 'Back'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (7, (SELECT id FROM instruction_action WHERE name = 'Close'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (8, (SELECT id FROM instruction_action WHERE name = 'Close'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (9, (SELECT id FROM instruction_action WHERE name = 'Forward'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (10, (SELECT id FROM instruction_action WHERE name = 'Forward'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (11, (SELECT id FROM instruction_action WHERE name = 'Refresh'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (12, (SELECT id FROM instruction_action WHERE name = 'Refresh'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (13, (SELECT id FROM instruction_action WHERE name = 'Click'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (14, (SELECT id FROM instruction_action WHERE name = 'Click'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (15, (SELECT id FROM instruction_action WHERE name = 'Click'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (16, (SELECT id FROM instruction_action WHERE name = 'Click'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (17, (SELECT id FROM instruction_action WHERE name = 'Click'), (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (18, (SELECT id FROM instruction_action WHERE name = 'DoubleClick'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (19, (SELECT id FROM instruction_action WHERE name = 'DoubleClick'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (20, (SELECT id FROM instruction_action WHERE name = 'DoubleClick'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (21, (SELECT id FROM instruction_action WHERE name = 'DoubleClick'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (22, (SELECT id FROM instruction_action WHERE name = 'DoubleClick'), (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (23, (SELECT id FROM instruction_action WHERE name = 'IsDisable'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (24, (SELECT id FROM instruction_action WHERE name = 'IsDisable'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (25, (SELECT id FROM instruction_action WHERE name = 'IsDisable'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (26, (SELECT id FROM instruction_action WHERE name = 'IsDisable'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (27, (SELECT id FROM instruction_action WHERE name = 'IsEnable'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (28, (SELECT id FROM instruction_action WHERE name = 'IsEnable'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (29, (SELECT id FROM instruction_action WHERE name = 'IsEnable'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (30, (SELECT id FROM instruction_action WHERE name = 'IsEnable'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (31, (SELECT id FROM instruction_action WHERE name = 'Count'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (32, (SELECT id FROM instruction_action WHERE name = 'Count'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (33, (SELECT id FROM instruction_action WHERE name = 'Count'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (34, (SELECT id FROM instruction_action WHERE name = 'Count'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (35, (SELECT id FROM instruction_action WHERE name = 'Count'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (36, (SELECT id FROM instruction_action WHERE name = 'Exist'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (37, (SELECT id FROM instruction_action WHERE name = 'Exist'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (38, (SELECT id FROM instruction_action WHERE name = 'Exist'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (39, (SELECT id FROM instruction_action WHERE name = 'Exist'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (40, (SELECT id FROM instruction_action WHERE name = 'NonExist'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (41, (SELECT id FROM instruction_action WHERE name = 'NonExist'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (42, (SELECT id FROM instruction_action WHERE name = 'NonExist'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (43, (SELECT id FROM instruction_action WHERE name = 'NonExist'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (44, (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (45, (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (46, (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (47, (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (48, (SELECT id FROM instruction_action WHERE name = 'ClickOnHideButton'), (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (49, (SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (50, (SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (51, (SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (52, (SELECT id FROM instruction_action WHERE name = 'JsExcuteForElement'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (53, (SELECT id FROM instruction_action WHERE name = 'MoveToElement'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (54, (SELECT id FROM instruction_action WHERE name = 'MoveToElement'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (55, (SELECT id FROM instruction_action WHERE name = 'MoveToElement'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (56, (SELECT id FROM instruction_action WHERE name = 'MoveToElement'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (57, (SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (58, (SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (59, (SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (60, (SELECT id FROM instruction_action WHERE name = 'DragAndDrop'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (61, (SELECT id FROM instruction_action WHERE name = 'Enter'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (62, (SELECT id FROM instruction_action WHERE name = 'Enter'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (63, (SELECT id FROM instruction_action WHERE name = 'Enter'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (64, (SELECT id FROM instruction_action WHERE name = 'Enter'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (65, (SELECT id FROM instruction_action WHERE name = 'Enter'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (66, (SELECT id FROM instruction_action WHERE name = 'Match'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (67, (SELECT id FROM instruction_action WHERE name = 'Match'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (68, (SELECT id FROM instruction_action WHERE name = 'Match'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (69, (SELECT id FROM instruction_action WHERE name = 'Match'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (70, (SELECT id FROM instruction_action WHERE name = 'InputInPageText'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (71, (SELECT id FROM instruction_action WHERE name = 'InputInPageText'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (72, (SELECT id FROM instruction_action WHERE name = 'InputInPageText'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (73, (SELECT id FROM instruction_action WHERE name = 'InputInPageText'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (74, (SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (75, (SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (76, (SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (77, (SELECT id FROM instruction_action WHERE name = 'InputContainsPageText'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (78, (SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (79, (SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (80, (SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (81, (SELECT id FROM instruction_action WHERE name = 'EnterReadonly'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (82, (SELECT id FROM instruction_action WHERE name = 'Clear'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (83, (SELECT id FROM instruction_action WHERE name = 'Clear'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (84, (SELECT id FROM instruction_action WHERE name = 'Clear'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (85, (SELECT id FROM instruction_action WHERE name = 'Clear'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (86, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (87, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (88, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (89, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (90, (SELECT id FROM instruction_action WHERE name = 'Select'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (91, (SELECT id FROM instruction_action WHERE name = 'Check'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (92, (SELECT id FROM instruction_action WHERE name = 'Check'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (93, (SELECT id FROM instruction_action WHERE name = 'Check'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (94, (SELECT id FROM instruction_action WHERE name = 'Check'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (95, (SELECT id FROM instruction_action WHERE name = 'FileUpload'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (96, (SELECT id FROM instruction_action WHERE name = 'FileUpload'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (97, (SELECT id FROM instruction_action WHERE name = 'FileUpload'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (98, (SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (99, (SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (100, (SELECT id FROM instruction_action WHERE name = 'FileUploadByWindow'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (101, (SELECT id FROM instruction_action WHERE name = 'FileDownload'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (102, (SELECT id FROM instruction_action WHERE name = 'FileDownload'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (103, (SELECT id FROM instruction_action WHERE name = 'FileDownload'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (104, (SELECT id FROM instruction_action WHERE name = 'SwitchTo'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (105, (SELECT id FROM instruction_action WHERE name = 'SwitchTo'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (106, (SELECT id FROM instruction_action WHERE name = 'SwitchTo'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (107, (SELECT id FROM instruction_action WHERE name = 'GET'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (108, (SELECT id FROM instruction_action WHERE name = 'POST'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (109, (SELECT id FROM instruction_action WHERE name = 'PUT'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (110, (SELECT id FROM instruction_action WHERE name = 'PATCH'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (111, (SELECT id FROM instruction_action WHERE name = 'DELETE'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_JSONPATH'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (112, (SELECT id FROM instruction_action WHERE name = 'Execute'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (113, (SELECT id FROM instruction_action WHERE name = 'Execute'), (SELECT id FROM instruction_option WHERE name = 'DTA_COMPARE_RETURN_VALUE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (114, (SELECT id FROM instruction_action WHERE name = 'Execute'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO instruction_action_instruction_option_link (id, instruction_action_id, instruction_option_id) 
VALUES (115, (SELECT id FROM instruction_action WHERE name = 'Execute'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));