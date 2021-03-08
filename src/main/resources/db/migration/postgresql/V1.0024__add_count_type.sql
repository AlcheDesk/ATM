DROP FUNCTION "access_update_none" ( ) CASCADE;
DROP FUNCTION "execution_status_trigger_test" ( ) CASCADE;
DROP FUNCTION "execution_status_update" ( ) CASCADE;

DROP TRIGGER IF EXISTS "insert_application_section_link_active" ON "application_section_link";
DROP TRIGGER IF EXISTS "insert_element_element_type_link_active" ON "element_element_type_link";
DROP TRIGGER IF EXISTS "insert_element_type_element_action_link_active" ON "element_type_element_action_link";
--DROP TRIGGER IF EXISTS "insert_element_type_element_locator_type_link_active" ON "element_type_element_locator_type_link";
DROP TRIGGER IF EXISTS "insert_engine_module_property_link_active" ON "engine_module_property_link";
DROP TRIGGER IF EXISTS "insert_instruction_result_file_link_active" ON "instruction_result_file_link";
DROP TRIGGER IF EXISTS "insert_instruction_result_step_log_link_active" ON "instruction_result_step_log_link";
DROP TRIGGER IF EXISTS "insert_instruction_step_option_link_active" ON "instruction_step_option_link";
DROP TRIGGER IF EXISTS "insert_instruction_test_case_option_link_active" ON "instruction_test_case_option_link";
DROP TRIGGER IF EXISTS "insert_module_module_property_link_active" ON "module_module_property_link";
DROP TRIGGER IF EXISTS "insert_project_application_link_active" ON "project_application_link";
DROP TRIGGER IF EXISTS "insert_project_test_case_link_active" ON "project_test_case_link";
DROP TRIGGER IF EXISTS "insert_run_instruction_result_link_active" ON "run_instruction_result_link";
--DROP TRIGGER IF EXISTS "insert_run_set_job_link_active" ON "run_set_job_link";
DROP TRIGGER IF EXISTS "insert_run_set_test_case_link_active" ON "run_set_test_case_link";
DROP TRIGGER IF EXISTS "insert_section_element_link_active" ON "section_element_link";
DROP TRIGGER IF EXISTS "insert_section_section_line_link_active" ON "section_section_line_link";
DROP TRIGGER IF EXISTS "insert_test_case_engine_link_active" ON "test_case_engine_link";
--DROP TRIGGER IF EXISTS "insert_test_case_environment_link_active" ON "test_case_environment_link";
DROP TRIGGER IF EXISTS "insert_test_case_folder_test_case_link_active" ON "test_case_folder_test_case_link";
DROP TRIGGER IF EXISTS "insert_test_case_instruction_link_active" ON "test_case_instruction_link";
DROP TRIGGER IF EXISTS "insert_test_case_run_link_active" ON "test_case_run_link";
DROP TRIGGER IF EXISTS "insert_test_case_storage_link_active" ON "test_case_storage_link";
--DROP TRIGGER IF EXISTS "insert_test_case_task_link_active" ON "test_case_task_link";

INSERT INTO element_action (name) VALUES ('Count') ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'button'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'link'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'dropdown'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'radio'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'filedown'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'fileup'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'frame'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;

INSERT INTO element_type_element_action_link 
(element_type_id,element_action_id) 
VALUES ((select id from element_type where name = 'textbox'),(select id from element_action where name = 'Count')) 
ON CONFLICT DO NOTHING;