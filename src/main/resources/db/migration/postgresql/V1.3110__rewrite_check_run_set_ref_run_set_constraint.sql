ALTER TABLE "run_set_test_case_link" DROP CONSTRAINT "run_set_test_case_link_check_test_case_id_not_null_or_ref_run_s";
ALTER TABLE run_set_test_case_link ADD CONSTRAINT test_case_id_not_null_or_ref_run_set_id_not_null CHECK ((test_case_id is not null and ref_run_set_id is null) or (test_case_id is null and ref_run_set_id is not null));
