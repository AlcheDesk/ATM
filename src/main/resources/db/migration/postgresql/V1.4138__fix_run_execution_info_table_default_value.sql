ALTER TABLE "run_execution_info" ALTER COLUMN instruction_executed_count SET DEFAULT 0;
UPDATE "run_execution_info" SET instruction_executed_count = 0 WHERE instruction_executed_count IS NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN instruction_executed_count SET NOT NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN instruction_pass_count SET DEFAULT 0;
UPDATE "run_execution_info" SET instruction_pass_count = 0 WHERE instruction_pass_count IS NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN instruction_pass_count SET NOT NULL;
CREATE OR REPLACE FUNCTION "run_insert_insert_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_sha2,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    source_ip,
	    driver_pack_sha2,
	    test_case_overwrite_sha2
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		digest(NEW.test_case::text, 'sha256'),
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    0,
		0,
		NEW.trigger_source,
		digest(NEW.driver_pack::text, 'sha256'),
		digest(NEW.test_case_overwrite::text, 'sha256')
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;