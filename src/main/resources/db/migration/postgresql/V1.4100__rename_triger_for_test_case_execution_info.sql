ALTER TRIGGER "dev_instruction_result_after_insert_test_case_execution_info" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_insert_tcei;
ALTER TRIGGER "dev_instruction_result_after_update_test_case_execution_info" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_update_tcei;
ALTER TRIGGER "prod_instruction_result_after_insert_test_case_execution_info" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_insert_tcei;
ALTER TRIGGER "prod_instruction_result_after_update_test_case_execution_info" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_update_tcei;
ALTER TRIGGER "run_after_insert_test_case_execution_info" ON "run" RENAME TO run_after_insert_tcei;
ALTER TRIGGER "run_after_update_test_case_execution_info" ON "run" RENAME TO run_after_update_tcei;
ALTER TRIGGER "test_case_after_insert_test_case_execution_info" ON "test_case" RENAME TO test_case_after_insert_tcei;
ALTER TRIGGER "test_case_z_before_update_test_case_execution_info" ON "test_case" RENAME TO test_case_before_update_tcei;

ALTER TRIGGER "test_case_after_insert_test_case_instruction_type_map" ON "test_case" RENAME TO test_case_after_insert_tcitm;
ALTER TRIGGER "instruction_before_update_test_case_instruction_type_map" ON "instruction" RENAME TO instruction_before_update_tcitm;

ALTER TRIGGER "application_update_log" ON "application" RENAME TO application_before_update_log;
ALTER TRIGGER "dev_instruction_result_update_log" ON "dev_instruction_result" RENAME TO dev_instruction_result_before_update_log;
ALTER TRIGGER "driver_update_log" ON "driver" RENAME TO driver_before_update_log;
ALTER TRIGGER "driver_pack_update_log" ON "driver_pack" RENAME TO driver_pack_before_update_log;
ALTER TRIGGER "element_update_log" ON "element" RENAME TO element_before_update_log;
ALTER TRIGGER "instruction_update_log" ON "instruction" RENAME TO instruction_before_update_log;
ALTER TRIGGER "instruction_overwrite_update_log" ON "instruction_overwrite" RENAME TO instruction_overwrite_before_update_log;
ALTER TRIGGER "module_update_log" ON "module" RENAME TO module_before_update_log;
ALTER TRIGGER "notification_update_log" ON "notification" RENAME TO notification_before_update_log;
ALTER TRIGGER "prod_instruction_result_update_log" ON "prod_instruction_result" RENAME TO prod_instruction_result_before_update_log;
ALTER TRIGGER "project_update_log" ON "project" RENAME TO project_before_update_log;
ALTER TRIGGER "run_update_log" ON "run" RENAME TO run_before_update_log;
ALTER TRIGGER "run_set_update_log" ON "run_set" RENAME TO run_set_before_update_log;
ALTER TRIGGER "run_set_result_update_log" ON "run_set_result" RENAME TO run_set_result_before_update_log;
ALTER TRIGGER "section_update_log" ON "section" RENAME TO section_before_update_log;
ALTER TRIGGER "system_requirement_pack_update_log" ON "system_requirement_pack" RENAME TO system_requirement_pack_before_update_log;
ALTER TRIGGER "template_update_log" ON "template" RENAME TO template_before_update_log;
ALTER TRIGGER "test_case_update_log" ON "test_case" RENAME TO test_case_before_update_log;
ALTER TRIGGER "test_case_overwrite_update_log" ON "test_case_overwrite" RENAME TO test_case_overwrite_before_update_log;
ALTER TRIGGER "test_case_share_folder_update_log" ON "test_case_share_folder" RENAME TO test_case_share_folder_before_update_log;

ALTER TRIGGER "tcitm_after_insert_test_case_driver_type_map" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_after_insert_tcdtm;
ALTER TRIGGER "tcitm_after_update_test_case_driver_type_map" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_after_update_tcdtm;
ALTER TRIGGER "tcitm_before_delete_test_case_driver_type_map" ON "test_case_instruction_type_map" RENAME TO test_case_instruction_type_map_before_delete_tcdtm;