ALTER TABLE "step_log" RENAME CONSTRAINT "log_entry_pkey" TO step_log_pkey;
ALTER TABLE "instruction_result" RENAME CONSTRAINT "result_pkey" TO instruction_result_pkey;
ALTER TABLE "instruction_result" RENAME CONSTRAINT "result_fkey_status" TO instruction_result_fkey_status;
ALTER TABLE "run" RENAME CONSTRAINT "run_fkey_status" TO run_fk_status;
ALTER TABLE "driver" RENAME CONSTRAINT "engine_pkey" TO driver_pkey;
ALTER TABLE "driver" RENAME CONSTRAINT "engine_name_ukey" TO driver_uk_name;
ALTER TABLE "driver_driver_property_link" RENAME CONSTRAINT "engine_module_property_link_pkey" TO driver_driver_property_link_pkey;
ALTER TABLE "driver_driver_property_link" RENAME CONSTRAINT "engine_module_property_ukey" TO driver_driver_property_link_uk_driver_driver_property;
ALTER TABLE "driver_property" RENAME CONSTRAINT "engine_property_pkey" TO driver_property_pkey;
ALTER TABLE "driver_property" RENAME CONSTRAINT "name_value_ukey" TO driver_property_uk_name_value;
ALTER TABLE "element" RENAME CONSTRAINT "element_fkey_color" TO element_fk_color;
ALTER TABLE "element" RENAME CONSTRAINT "element_locator_type_id" TO element_fk_element_locator_type;
ALTER TABLE "element" RENAME CONSTRAINT "element_type_id_fkey" TO element_fk_element_type;
ALTER TABLE "element_type" RENAME CONSTRAINT "element_type_name_ukey" TO element_type_uk_name;
ALTER TABLE "element_locator_type" RENAME CONSTRAINT "element_locator_type_name_ukey" TO element_locator_type_uk_name;
ALTER TABLE "element_action" RENAME CONSTRAINT "element_action_name_ukey" TO element_action_uk_name;
ALTER TABLE "element_type_element_action_link" RENAME CONSTRAINT "element_element_action_link_pkey" TO ele_type_ele_action_link_pkey;
ALTER TABLE "element_type_element_action_link" RENAME CONSTRAINT "element_type_element_action_ukey" TO ele_type_ele_action_link_uk_ele_type_ele_action;
ALTER TABLE "element_type_element_locator_type_link" RENAME CONSTRAINT
    "element_tyep_element_locator_type_fkey_element_locator_type" TO
    ele_type_ele_locator_type_link_fk_ele_locator_type;
ALTER TABLE "element_type_element_locator_type_link" RENAME CONSTRAINT
    "element_tyep_element_locator_type_fkey_element_type" TO
    ele_type_ele_locator_type_link_fk_ele_type;
ALTER TABLE "element_type_element_locator_type_link" RENAME CONSTRAINT
    "element_type_element_locator_type_ukey" TO
    ele_type_ele_locator_type_link_uk_ele_type_ele_locator_type;
ALTER TABLE "element_type_instruction_option_link" ADD CONSTRAINT
    ele_type_ins_opt_link_uk_ele_type_ins_opt UNIQUE ("element_type_id", "instruction_option_id");
ALTER TABLE "element_type_instruction_option_link" RENAME CONSTRAINT
    "element_type_step_option_link_pkey" TO element_type_instruction_option_link_pkey;
ALTER TABLE "execution_log" RENAME CONSTRAINT "execution_log_fk_level" TO
    execution_log_fk_log_level;
ALTER TABLE "group" RENAME CONSTRAINT "group_name_key" TO group_uk_name;
ALTER TABLE "instruction" RENAME CONSTRAINT "instruction_fkey_application" TO
    instruction_fk_application;
ALTER TABLE "instruction" RENAME CONSTRAINT "instruction_fkey_color" TO instruction_fk_color;
ALTER TABLE "instruction" RENAME CONSTRAINT "instruction_fkey_element" TO
    instruction_fk_element;
ALTER TABLE "instruction" RENAME CONSTRAINT "instruction_fkey_project" TO
    instruction_fk_project;
ALTER TABLE "instruction" RENAME CONSTRAINT "instruction_fkey_section" TO
    instruction_fk_section;
ALTER TABLE "instruction" RENAME CONSTRAINT
    "instruction_ck_application_id_and_section_id_and_element_id" TO
    ins_ck_application_id_and_section_id_and_element_id;
ALTER TABLE "instruction_option" RENAME CONSTRAINT "step_option_pkey" TO
    instruction_option_pkey;
ALTER TABLE "instruction_option_entry" RENAME CONSTRAINT "step_option_entry_pkey" TO
    instruction_option_entry_pkey;
ALTER TABLE "instruction_type" RENAME CONSTRAINT "instructiontype_uk_name" TO
    instruction_type_uk_name;  
ALTER TABLE "log_level" ADD CONSTRAINT log_level_uk_name UNIQUE ("name");
ALTER TABLE "project" RENAME CONSTRAINT "project_name_ukey" TO project_uk_name;
ALTER TABLE "project_application_link" RENAME CONSTRAINT
    "project_to_application_link_fkey_application" TO project_to_application_link_fk_application;
ALTER TABLE "project_application_link" RENAME CONSTRAINT
    "project_to_application_link_fkey_project" TO project_to_application_link_fk_project;
ALTER TABLE "project_application_link" RENAME CONSTRAINT "project_application_ukey" TO
    project_application_link_uk_application_project;
ALTER TABLE "project_test_case_link" RENAME CONSTRAINT
    "project_to_test_case_link_project_id_fkey" TO project_to_test_case_link_fk_project;
ALTER TABLE "project_test_case_link" RENAME CONSTRAINT
    "project_to_test_case_link_test_case_id_fkey" TO project_to_test_case_link_fk_test_case;
ALTER TABLE "project_test_case_link" RENAME CONSTRAINT "project_test_case_ukey" TO
    project_test_case_link_uk_project_test_case;
ALTER TABLE "property" RENAME CONSTRAINT "properties_pkey" TO property_pkey;
ALTER TABLE "run_set" RENAME CONSTRAINT "run_set_fkey" TO run_set_fk_group;
ALTER TABLE "run_set" RENAME CONSTRAINT "run_set_name_ukey" TO run_set_uk_name;
ALTER TABLE "run_set_job_link" RENAME CONSTRAINT "run_set_job_fkey_run_set" TO run_set_job_link_fk_run_set;
ALTER TABLE "run_set_test_case_link" RENAME CONSTRAINT "run_set_fkey" TO run_set_fk_run_set;
ALTER TABLE "run_set_test_case_link" RENAME CONSTRAINT "test_case_fkey" TO
    test_case_fk_test_case;
ALTER TABLE "run_set_test_case_link" RENAME CONSTRAINT "run_set_test_case_ukey" TO
    run_set_test_case_link_uk_run_set_test_case;
ALTER TABLE "run_set_type" ADD CONSTRAINT run_set_type_uk_name UNIQUE ("name");
ALTER TABLE "section_element_link" RENAME CONSTRAINT "section_to_element_link_pkey" TO
    section_element_link_pkey;
ALTER TABLE "section_element_link" RENAME CONSTRAINT "section_to_element_link_fkey_element"
    TO section_element_link_fk_element;
ALTER TABLE "section_element_link" RENAME CONSTRAINT "section_to_element_link_fkey_section"
    TO section_element_link_fk_section;
ALTER TABLE "section_element_link" RENAME CONSTRAINT "section_element_ukey" TO
    section_element_link_uk_section_element;
ALTER TABLE "status" ADD CONSTRAINT status_uk_name UNIQUE ("name");
ALTER TABLE "test_case" RENAME CONSTRAINT "test_case_group_id_fkey" TO test_case_fk_group;
ALTER TABLE "test_case" RENAME CONSTRAINT "test_case_status_id_fkey" TO test_case_fk_status;
ALTER TABLE "test_case" RENAME CONSTRAINT "testcase_fk_type" TO test_case_fk_type;
ALTER TABLE "test_case" RENAME CONSTRAINT "test_case_name_ukey" TO test_case_uk_name;
ALTER TABLE "test_case_folder_type" RENAME CONSTRAINT "test_case_folder_uk_name" TO
    test_case_folder_type_uk_name;
ALTER TABLE "test_case_folder" RENAME CONSTRAINT "test_case_folder_name_ukey" TO
    test_case_folder_uk_name;
ALTER TABLE "test_case_folder_test_case_link" RENAME CONSTRAINT
    "folder_to_test_case_link_pkey" TO test_folder_test_case_link_pkey;
ALTER TABLE "test_case_folder_test_case_link" RENAME CONSTRAINT
    "test_case_folder_to_test_case_link_fkey_test_case" TO
    test_case_folder_test_case_link_fk_test_case;
ALTER TABLE "test_case_folder_test_case_link" RENAME CONSTRAINT
    "test_case_folder_to_test_case_link_fkey_test_case_folder" TO
    test_case_folder_test_case_link_fk_test_case_folder;
ALTER TABLE "test_case_folder_test_case_link" RENAME CONSTRAINT
    "test_case_test_case_folder_ukey" TO test_case_test_case_folder_uk_test_case_test_case_folder;
ALTER TABLE "test_case_option" RENAME CONSTRAINT "logic_option_pkey" TO
    test_case_option_pkey;
ALTER TABLE "test_case_task_link" RENAME CONSTRAINT "test_case_task_fkey_test_case" TO
    test_case_task_fk_test_case;
ALTER TABLE "test_case_task_type" RENAME CONSTRAINT "task_type_pkey" TO
    test_case_task_type_pkey;