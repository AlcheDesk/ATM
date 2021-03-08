ALTER TABLE "run_execution_info" ADD COLUMN run_result_overwritten INTEGER;
UPDATE "run_execution_info" SET run_result_overwritten = 0 WHERE run_result_overwritten IS NULL;
--------------------------------------------------------------------------------------------------------
UPDATE "run_execution_info" SET
run_result_overwritten = i.result_overwritten
FROM (select id, result_overwritten FROM run ) i
WHERE i.id = run_id;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "run_execution_info" ALTER COLUMN run_result_overwritten SET NOT NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN run_result_overwritten SET DEFAULT 0;
ALTER TABLE "run_execution_info" ALTER COLUMN "run_priority" DROP NOT NULL;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_insert_run_execution_info" ()  RETURNS trigger
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
	    test_case_overwrite_name,
	    test_case_name,
	    run_priority,
	    run_result_overwritten
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
		NEW.driver_pack->'name',
		NEW.test_case->'name',
		NEW.test_case_overwrite->'name',
		NEW.priority,
		NEW.result_overwritten
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_update_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id <> OLD.status_id THEN
			update run_execution_info set
			run_status_id = NEW.status_id,
			run_updated_at = NEW.updated_at
			where test_case_id = NEW.test_case_id and run_id = NEW.id;
	END IF;
	IF NEW.result_overwritten <> OLD.result_overwritten THEN
			update run_execution_info set
			run_result_overwritten = NEW.result_overwritten,
			run_updated_at = NEW.updated_at
			where test_case_id = NEW.test_case_id and run_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;