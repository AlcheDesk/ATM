ALTER TABLE "run" disable TRIGGER "run_before_update_timestamp_with_uuid";
UPDATE "run_execution_info" SET
driver_pack_name = i.dpn, test_case_overwrite_name = i.tcon, test_case_name = i.tcn
FROM (select id, driver_pack->>'name' as dpn, test_case->>'name' as tcon, test_case_overwrite->>'name' as tcn FROM run ) i
WHERE i.id = run_id;
ALTER TABLE "run" enable TRIGGER "run_before_update_timestamp_with_uuid";
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "public"."run_insert_insert_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_md5,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    trigger_source,
	    driver_pack_md5,
	    test_case_overwrite_md5,
	    run_group_id,
	    driver_pack_name,
	    test_case_name,
	    test_case_overwrite_name,
	    run_priority,
	    run_result_overwritten,
	    run_project_id,
	    is_finished
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		md5(NEW.test_case::text)::uuid,
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    0,
		0,
		NEW.trigger_source,
		md5(NEW.driver_pack::text)::uuid,
		md5(NEW.test_case_overwrite::text)::uuid,
		NEW.group_id,
		NEW.driver_pack->>'name',
		NEW.test_case->>'name',
		NEW.test_case_overwrite->>'name',
		NEW.priority,
		NEW.result_overwritten,
		(NEW.test_case->>'projectId')::bigint,
		false
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' AND NEW.instruction IS NOT NULL AND NEW.instruction <> OLD.instruction THEN
		NEW.target = NEW.instruction->>'target';
	ELSIF TG_OP = 'UPDATE' AND NEW.instruction IS NULL THEN
	    NEW.target = NULL;
	END IF;
	
	IF TG_OP = 'INSERT' AND NEW.instruction IS NOT NULL THEN
		NEW.target = NEW.instruction->>'target';
	ELSIF TG_OP = 'INSERT' AND NEW.instruction IS NULL THEN
	    NEW.target = NULL;
	END IF;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "dev_instruction_generate_columns"
  BEFORE INSERT OR UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_generate_columns();
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "prod_instruction_generate_columns"
  BEFORE INSERT OR UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE instruction_result_generate_columns();
--------------------------------------------------------------------------------------------------------