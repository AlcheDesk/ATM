DROP FUNCTION "valid_ref_test_case_id" ( bigint, bigint );
DROP FUNCTION "verify_application_active" ( bigint );
DROP FUNCTION "verify_application_name" ( bigint, text );
DROP FUNCTION "verify_element_active" ( bigint );
DROP FUNCTION "verify_element_name" ( bigint, text );
DROP FUNCTION "verify_project_active" ( bigint );
DROP FUNCTION "verify_run_set_active" ( bigint );
DROP FUNCTION "verify_section_active" ( bigint );
DROP FUNCTION "verify_section_name" ( bigint, text );
DROP FUNCTION "verify_test_case_active" ( bigint );
DROP FUNCTION "verify_test_case_folder_active" ( bigint );
DROP FUNCTION "verify_test_case_name" ( bigint, text );
DROP FUNCTION "application_modify_trigger_instruction_target_udpate" ( );
DROP FUNCTION "generate_instrcution_target" ( );
DROP FUNCTION "update_modified_time_log" ( );
DROP FUNCTION "trigger_order_index_by_intruction_insert" ( );
DROP FUNCTION "trigger_order_index_by_intruction_update" ( );
DROP FUNCTION "section_modify_trigger_instruction_target_udpate" ( );
DROP FUNCTION "element_modify_trigger_instruction_target_udpate" ( );