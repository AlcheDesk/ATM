ALTER TABLE "element_type_instruction_action_link" DROP CONSTRAINT 
"element_type_element_action_link_instruction_action";
ALTER TABLE "instruction_action_instruction_option_link" DROP CONSTRAINT
"instruction_action_instruction_option_link_fk_instruction_actio";
ALTER TABLE "instruction_type_instruction_action_link" DROP CONSTRAINT
"instruction_type_instruction_action_link_fk_instruction_action";
    
UPDATE element_type_instruction_action_link SET instruction_action_id = 41 WHERE instruction_action_id = 1000;
UPDATE instruction_action_instruction_option_link SET instruction_action_id = 41 WHERE instruction_action_id = 1000;
UPDATE instruction_type_instruction_action_link SET instruction_action_id = 41 WHERE instruction_action_id = 1000;
UPDATE instruction_action SET name = 'SwitchToNewTab', id = 41 WHERE id = 1000 AND name = 'SwitchToNewtab';

ALTER TABLE "element_type_instruction_action_link" ADD CONSTRAINT 
element_type_instruction_action_link FOREIGN KEY ("instruction_action_id") REFERENCES "instruction_action" ("id");
ALTER TABLE "instruction_action_instruction_option_link" ADD CONSTRAINT
instruction_action_instruction_option_link_fk_instruction_action FOREIGN KEY ("instruction_action_id") REFERENCES "instruction_action" ("id");
ALTER TABLE "instruction_type_instruction_action_link" ADD CONSTRAINT 
instruction_type_instruction_action_link_fk_instruction_action FOREIGN KEY ("instruction_action_id") REFERENCES "instruction_action" ("id");

-- element_type_instruction_action_link
UPDATE element_type_instruction_action_link SET id = 334 WHERE id = 1001;
UPDATE element_type_instruction_action_link SET id = 335 WHERE id = 1002;
UPDATE element_type_instruction_action_link SET id = 336 WHERE id = 1003;
UPDATE element_type_instruction_action_link SET id = 337 WHERE id = 1004;
UPDATE element_type_instruction_action_link SET id = 338 WHERE id = 1005;
UPDATE element_type_instruction_action_link SET id = 339 WHERE id = 1006;
UPDATE element_type_instruction_action_link SET id = 340 WHERE id = 1007;
UPDATE element_type_instruction_action_link SET id = 341 WHERE id = 1008;

-- element
INSERT INTO element (id, name, comment, locator_value, html_position_x, html_position_y, is_deleted, created_at, updated_at, log, element_type_id, element_locator_type_id, color_id, parameter, is_driver, project_id, section_id, copy_from_id, tenant_id, application_id) 
VALUES 
(5, 'VirtualBrowser', 'This is the virtual web browser, created by system', null, null, null, false, '2018-04-20 20:28:58.379634+0800', '2019-10-22 01:49:41.626499+0800', null, 9, null, 1, '{}', true, null, null, null, 1000, null);

-- instruction_type
INSERT INTO instruction_type (id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) 
VALUES (18, 'VirtualWebBrowser', true, true, true, 9, true, 5, '@.input');

-- instruction_type_element_type_link
INSERT INTO instruction_type_element_type_link ( id, instruction_type_id, element_type_id ) VALUES ( 25, 18, 9 );

-- instruction_type_instruction_action_link
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (116, 18, 29);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (117, 18, 11);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (118, 18, 16);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (119, 18, 28);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (120, 18, 10);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (121, 18, 27);
INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (122, 18, 41);
