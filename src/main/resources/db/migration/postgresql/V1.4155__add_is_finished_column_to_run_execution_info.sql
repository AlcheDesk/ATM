ALTER TABLE "run_execution_info" ADD COLUMN is_finished BOOLEAN;
--------------------------------------------------------------------------------------------------------
UPDATE "run_execution_info" SET
is_finished = i.finish
FROM (select id, is_finished as finish FROM run ) i
WHERE i.id = run_id;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "run_execution_info" ALTER COLUMN is_finished SET NOT NULL;
ALTER TABLE "run_execution_info" ALTER COLUMN is_finished SET DEFAULT false;
ALTER TABLE "run" ADD CONSTRAINT run_ix_finished UNIQUE ("id", "is_finished");
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_finish FOREIGN KEY ("run_id", is_finished) 
REFERENCES "run" ("id", "is_finished")  DEFERRABLE INITIALLY DEFERRED;
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
		NEW.driver_pack->'name',
		NEW.test_case->'name',
		NEW.test_case_overwrite->'name',
		NEW.priority,
		NEW.result_overwritten,
		(NEW.test_case->>'projectId')::bigint,
		false
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_update_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id <> OLD.status_id OR NEW.is_finished <> OLD.is_finished OR NEW.result_overwritten <> OLD.result_overwritten THEN
			update run_execution_info set
			run_status_id = NEW.status_id,
			run_updated_at = NEW.updated_at,
			run_result_overwritten = NEW.result_overwritten,
			is_finished = NEW.is_finished
			where test_case_id = NEW.test_case_id and run_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;