-- change instruction with action sleep to wait
UPDATE instruction SET element_action_id = (SELECT id FROM element_action WHERE name = 'Wait') WHERE element_action_id = (SELECT id FROM element_action WHERE name = 'Sleep');

--rename element Type
UPDATE element_type SET name = 'WebButton' WHERE name = 'Button';
UPDATE element_type SET name = 'WebLink' WHERE name = 'Link';
UPDATE element_type SET name = 'WebDropdown' WHERE name = 'Dropdown';
UPDATE element_type SET name = 'WebRadio' WHERE name = 'Radio';
UPDATE element_type SET name = 'WebCheckbox' WHERE name = 'Checkbox';
UPDATE element_type SET name = 'WebTable' WHERE name = 'Table';
UPDATE element_type SET name = 'WebFrame' WHERE name = 'Frame';
UPDATE element_type SET name = 'WebTextbox' WHERE name = 'Textbox';
UPDATE element_type SET name = 'WebBrowser' WHERE name = 'Browser';
  -- update element which is FileDown to FileUp
  DROP TRIGGER "instruction_insert_target" ON "instruction";
  DROP TRIGGER "instruction_update_target" ON "instruction";
  DROP TRIGGER "element_udpate_trigger_instruction_target_update" ON "element";
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'FileUp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'FileDown');
  DELETE FROM element_type_instruction_option_link WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'FileDown');
  DELETE FROM element_type_element_locator_type_link WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'FileDown');
  DELETE FROM element_type_element_action_link WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'FileDown');
UPDATE element_type SET name = 'WebFile' WHERE name = 'FileUp';

-- create the temp type
UPDATE element_type SET name = 'temp' WHERE name = 'FileDown';

--==start to reorder the element Type
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFile');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFile');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFile');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFile');  
  UPDATE element_type SET name = 'WebFileTemp' WHERE name = 'WebFile';
  UPDATE element_type SET name = 'WebFile' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'WebFileTemp';
  
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFrame');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFrame');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFrame');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebFrame');  
  UPDATE element_type SET name = 'WebFrameTemp' WHERE name = 'WebFrame';
  UPDATE element_type SET name = 'WebFrame' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'WebFrameTemp';
  
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebBrowser');  
  UPDATE element_type SET name = 'WebBrowserTemp' WHERE name = 'WebBrowser';
  UPDATE element_type SET name = 'WebBrowser' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'WebBrowserTemp';
  
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebTextbox');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebTextbox');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebTextbox');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'WebTextbox');  
  UPDATE element_type SET name = 'WebTextboxTemp' WHERE name = 'WebTextbox';
  UPDATE element_type SET name = 'WebTextbox' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'WebTextboxTemp';
  
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'SQL');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'SQL');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'SQL');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'SQL');  
  UPDATE element_type SET name = 'SQLTemp' WHERE name = 'SQL';
  UPDATE element_type SET name = 'SQL' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'SQLTemp';
  
  UPDATE element SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript');
  UPDATE element_type_instruction_option_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript');
  UPDATE element_type_element_locator_type_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript');
  UPDATE element_type_element_action_link SET element_type_id = (SELECT id FROM element_type WHERE name = 'temp') WHERE element_type_id = (SELECT id FROM element_type WHERE name = 'JavaScript');  
  UPDATE element_type SET name = 'JavaScriptTemp' WHERE name = 'JavaScript';
  UPDATE element_type SET name = 'JavaScript' WHERE name = 'temp';
  UPDATE element_type SET name = 'temp' WHERE name = 'JavaScriptTemp';
  
  DELETE FROM element_type WHERE name = 'temp';

-- rename element_action
UPDATE element_action SET name = 'IsDisable' WHERE name = 'isDisabled';
UPDATE element_action SET name = 'IsEnable' WHERE name = 'IsEnabled';
UPDATE element_action SET name = 'Match' WHERE name = 'Verify';
UPDATE element_action SET name = 'SwitchTo' WHERE name = 'SwitchToFrame';
UPDATE element_action SET name = 'FileUpload' WHERE name = 'FileUp';
UPDATE element_action SET name = 'InputContainsPageText' WHERE name = 'VerifyContains';
UPDATE element_action SET name = 'InputInPageText' WHERE name = 'VerifyIn';
UPDATE element_action SET name = 'EnterReadonly' WHERE name = 'EnterTextReadOnly';
UPDATE element_action SET name = 'FileUploadByWindow' WHERE name = 'FileUpWithWindow';
UPDATE element_action SET name = 'FileDownload' WHERE name = 'Download';

INSERT INTO element_action (id,name) VALUES (27,'Close');
INSERT INTO element_action (id,name) VALUES (28,'Forward');
INSERT INTO element_action (id,name) VALUES (29,'Refresh');


-- rebuild element type element action link table
DELETE FROM element_type_element_action_link WHERE id IS NOT NULL;

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (1,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Navigate'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (2,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Wait'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (3,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Back'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (4,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Close'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (5,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Forward'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (6,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM element_action WHERE name = 'Refresh'));


INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (7,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'DoubleClick'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (8,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Click'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (9,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (10,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (11,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (12,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (13,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (14,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Enter'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (15,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Match'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (16,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'InputInPageText'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (17,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'InputContainsPageText'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (18,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'EnterReadonly'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (19,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Clear'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (20,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (21,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (22,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (23,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (24,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (25,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Select'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (26,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (27,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (28,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (29,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (30,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (31,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Click'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (32,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'DoubleClick'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (33,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (34,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (35,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (36,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (37,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (38,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Check'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (39,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (40,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (41,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (42,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (43,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (44,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Select'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (45,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'IsEnable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (46,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'IsDisable'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (47,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Count'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (48,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'Exist'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (49,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_action WHERE name = 'NonExist'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (50,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileUpload'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (51,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileUploadByWindow'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (52,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_action WHERE name = 'FileDownload'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (53,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_action WHERE name = 'SwitchTo'));

INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (54,(SELECT id FROM element_type WHERE name = 'SQL'), (SELECT id FROM element_action WHERE name = 'Execute'));
INSERT INTO element_type_element_action_link (id,element_type_id,element_action_id) 
VALUES (55,(SELECT id FROM element_type WHERE name = 'JavaScript'), (SELECT id FROM element_action WHERE name = 'Execute'));

-- clean up instruction option table
ALTER TABLE "instruction_option_entry" DROP CONSTRAINT "instruction_option_entry_fk_name";
DELETE FROM instruction_option WHERE id IS NOT NULL;
ALTER TABLE instruction_option ADD COLUMN active BOOLEAN;
UPDATE instruction_option SET active = true WHERE active IS NULL;
ALTER TABLE instruction_option ALTER COLUMN active SET NOT NULL;
ALTER TABLE instruction_option ALTER COLUMN active SET DEFAULT true;
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (1,'RES_IGNORE',true,true,false,'忽略测试步骤的运行结果');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (2,'BTN_RANDOM_CLICK_ONE',true,true,false,'定位到多个按钮时，随机点击一个');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (3,'NO_VERIFY',true,true,false,'对元素的操作不做校验');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (4,'INS_IGNORE',true,true,false,'忽略步骤的执行');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (5,'ELE_REPLACE_LOCATOR_VALUE',true,true,true,'输入值传递到定位值');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (6,'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT',true,true,true,'点击按钮出现弹窗后验证输入,文本等于在弹窗的文本中');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (7,'LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT',true,true,true,'点击链接出现弹窗后验证输入,文本等于在弹窗的文本中');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (8,'BTN_AlERT_CLICK_OK',true,true,false,'点击按钮，有弹窗出现点击确定');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (9,'DTA_SAVE_INPUT',true,true,true,'保存元素的输入');
INSERT INTO instruction_option (id,name,active,preloaded,with_value,comment) VALUES (10,'DTA_SAVE_TEXT',true,true,true,'保存元素的文本值');

-- clean up instruction entry table
DELETE FROM instruction_option_entry WHERE name NOT IN (SELECT name FROM instruction_option WHERE id IS NOT NULL);
ALTER TABLE instruction_option_entry ADD CONSTRAINT instruction_option_entry_fk_name FOREIGN KEY (name) REFERENCES "instruction_option" ("name");

--rebuild element_type_instruction_option_link table
DELETE FROM element_type_instruction_option_link WHERE id IS NOT NULL;
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (1,(SELECT id FROM element_type WHERE name = 'SQL'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (2,(SELECT id FROM element_type WHERE name = 'JavaScript'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (3,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (4,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (5,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (6,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (7,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (8,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (9,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (10,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (11,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'RES_IGNORE'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (12,(SELECT id FROM element_type WHERE name = 'SQL'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (13,(SELECT id FROM element_type WHERE name = 'JavaScript'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (14,(SELECT id FROM element_type WHERE name = 'WebBrowser'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (15,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (16,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (17,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (18,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (19,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (20,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (21,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (22,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'INS_IGNORE'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (23,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (24,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (25,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (26,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (27,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (28,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (29,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (30,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'NO_VERIFY'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (31,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (32,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (33,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (34,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (35,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (36,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (37,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (38,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'ELE_REPLACE_LOCATOR_VALUE'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (39,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (40,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (41,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (42,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (43,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (44,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (45,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (46,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_INPUT'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (47,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (48,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (49,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (50,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (51,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (52,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (53,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (54,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM instruction_option WHERE name = 'DTA_SAVE_TEXT'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (55,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'BTN_RANDOM_CLICK_ONE'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (56,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'BTN_CLICK_UNTIL_VERIFY_ALERT_TEXT'));
INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (57,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM instruction_option WHERE name = 'BTN_AlERT_CLICK_OK'));

INSERT INTO element_type_instruction_option_link (id,element_type_id,instruction_option_id) 
VALUES (58,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM instruction_option WHERE name = 'LNK_CLICK_UNTIL_VERIFY_ALERT_TEXT'));


--rebuild element_type_element_locator_type_link table
DELETE FROM element_type_element_locator_type_link WHERE id IS NOT NULL;
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (1,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (2,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (3,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (4,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (5,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (6,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (7,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_locator_type WHERE name = 'Name'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (8,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_locator_type WHERE name = 'Name'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (9,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (10,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (11,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (12,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (13,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (14,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (15,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (16,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_locator_type WHERE name = 'CSS'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (17,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (18,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (19,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (20,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (21,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (22,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (23,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (24,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_locator_type WHERE name = 'Tag'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (25,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (26,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (27,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (28,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (29,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (30,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (31,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (32,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_locator_type WHERE name = 'XPath'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (33,(SELECT id FROM element_type WHERE name = 'WebButton'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (34,(SELECT id FROM element_type WHERE name = 'WebTextbox'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (35,(SELECT id FROM element_type WHERE name = 'WebRadio'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (36,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (37,(SELECT id FROM element_type WHERE name = 'WebCheckbox'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (38,(SELECT id FROM element_type WHERE name = 'WebDropdown'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (39,(SELECT id FROM element_type WHERE name = 'WebFile'), (SELECT id FROM element_locator_type WHERE name = 'ID'));
INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (40,(SELECT id FROM element_type WHERE name = 'WebFrame'), (SELECT id FROM element_locator_type WHERE name = 'ID'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (41,(SELECT id FROM element_type WHERE name = 'WebLink'), (SELECT id FROM element_locator_type WHERE name = 'Linktext'));

INSERT INTO element_type_element_locator_type_link (id,element_type_id,element_locator_type_id) 
VALUES (42,(SELECT id FROM element_type WHERE name = 'SQL'), (SELECT id FROM element_locator_type WHERE name = 'JDBC'));
