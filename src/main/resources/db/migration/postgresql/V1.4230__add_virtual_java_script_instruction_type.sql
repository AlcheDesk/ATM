INSERT INTO element (id, name, comment, locator_value, html_position_x, html_position_y, is_deleted, created_at, updated_at, log, element_type_id, element_locator_type_id, color_id, parameter, is_driver, project_id, section_id, copy_from_id, tenant_id, application_id) 
VALUES (6, 'Virtual JavaScript Executor', 'This is vistual JavaScript executor, created by system', null, null, null, false, '2018-04-20 20:28:58.379634+0800', '2019-10-22 01:49:41.626499+0800', null, 12, null, 1, '{}', true, null, null, null, 1000, null);

INSERT INTO instruction_type (id, name, is_active, is_predefined, is_driver, driver_type_id, is_element_required, virtual_element_id, overridable_fields) 
VALUES (19, 'VirtualJavaScript', false, true, true, 9, true, 6, null);

INSERT INTO instruction_type_instruction_action_link (id, instruction_type_id, instruction_action_id) VALUES (123, 19, 19);

INSERT INTO instruction_type_element_type_link (id, instruction_type_id, element_type_id) VALUES (26, 19, 12);

INSERT INTO driver_type_instruction_type_link (id, driver_type_id, instruction_type_id) VALUES (11, 9, 19);
