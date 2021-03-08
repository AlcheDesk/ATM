UPDATE "project_report_info" SET
failed_test_case_number = total_test_case_number - failed_test_case_number,
pass_rate = case when executed_test_case_number <> 0 then CAST(passed_test_case_number AS numeric)/CAST(executed_test_case_number AS numeric) else 100.00 end,
fail_rate = case when executed_test_case_number <> 0 then CAST(failed_test_case_number AS numeric)/CAST(executed_test_case_number AS numeric) else 100.00 end;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_report_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEW.failed_test_case_number = NEW.total_test_case_number - NEW.failed_test_case_number;
		NEW.pass_rate = case when NEW.executed_test_case_number <> 0 then CAST(NEW.passed_test_case_number AS numeric)/CAST(NEW.executed_test_case_number AS numeric) else 100.00 end;
		NEW.fail_rate = case when NEW.executed_test_case_number <> 0 then CAST(NEW.failed_test_case_number AS numeric)/CAST(NEW.executed_test_case_number AS numeric) else 100.00 end;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.total_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.total_execution_time = 0;
		NEW.failed_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.passed_test_case_number = 0;
		NEW.pass_rate = 100.00;
		NEW.fail_rate = 100.00;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;