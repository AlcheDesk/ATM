ALTER FUNCTION test_case_instruction_types_map_delete_test_case_driver_type_ma() RENAME TO tcitm_delete_delete_test_case_driver_type_map;
ALTER FUNCTION test_case_instruction_types_map_insert_test_case_driver_type_ma() RENAME TO tcitm_insert_insert_test_case_driver_type_map;
ALTER FUNCTION test_case_instruction_types_map_update_test_case_driver_type_ma() RENAME TO tcitm_update_update_test_case_driver_type_map;
ALTER FUNCTION update_test_case_instruction_types_map_after_test_case_insert() RENAME TO test_case_insert_update_test_case_instruction_type_map;
----
ALTER FUNCTION update_test_case_execution_info_after_dev_instruction_result_in() RENAME TO dev_ir_insert_update_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_after_dev_instruction_result_up() RENAME TO dev_ir_update_update_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_after_prod_instruction_result_i() RENAME TO prod_ir_insert_update_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_after_prod_instruction_result_u() RENAME TO prod_ir_update_update_test_case_execution_info;
----
ALTER FUNCTION update_test_case_execution_info_after_run_insert() RENAME TO run_insert_update_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_after_run_update() RENAME TO run_update_update_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_after_test_case_insert() RENAME TO test_case_insert_insert_test_case_execution_info;
ALTER FUNCTION update_test_case_execution_info_before_test_case_update() RENAME TO test_case_update_update_test_case_execution_info;