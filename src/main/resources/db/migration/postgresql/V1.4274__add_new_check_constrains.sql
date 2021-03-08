----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
ALTER TABLE "project_execution_info" ADD 
CONSTRAINT project_execution_info_ck_total_test_case_numbers CHECK ((total_test_case_number >= executed_test_case_number));
ALTER TABLE "project_execution_info" ADD 
CONSTRAINT project_execution_info_ck_active_test_case_numbers CHECK ((total_test_case_number >= active_test_case_number));
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------