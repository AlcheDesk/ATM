ALTER TABLE "run" ADD COLUMN project_id bigint;
ALTER TABLE "run" disable TRIGGER "run_before_update_log";
UPDATE "run" SET project_id = (select tc.project_id from test_case tc where tc.id = test_case_id);
ALTER TABLE "run" enable TRIGGER "run_before_update_log";
ALTER TABLE "run" ALTER COLUMN project_id SET NOT NULL;
ALTER TABLE "run" ADD CONSTRAINT run_fk_project FOREIGN KEY (project_id) REFERENCES "project" ("id");
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
		NEW.end_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.is_finished IS TRUE THEN
	    NEW.end_at = OLD.end_at;
	    NEW.is_finished = OLD.is_finished;
	    NEW.start_at = OLD.start_at;
	END IF;
	
	-- start_at
	IF TG_OP = 'UPDATE' AND OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
	    NEW.start_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.start_at IS NOT NULL THEN
	    NEW.start_at = OLD.start_at;
	END IF;
	
	-- test_case_id
	IF TG_OP = 'UPDATE' THEN
	    NEW.test_case_id = OLD.test_case_id;
	    NEW.project_id = OLD.project_id;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
		NEW.start_at = NULL;
		NEW.project_id = (NEW.test_case->>'project_id')::bigint;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
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
		NEW.driver_pack->>'name',
		NEW.test_case->>'name',
		NEW.test_case_overwrite->>'name',
		NEW.priority,
		NEW.result_overwritten,
		NEW.project_id,
		false
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_update_update_test_case" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.name IS NOT NULL AND NEW.name <> OLD.name THEN
		UPDATE test_case SET project_name = NEW.name WHERE project_id = NEW.id;
		UPDATE project_execution_info SET project_name = NEW.name WHERE project_id = NEW.id;
	ELSIF TG_OP = 'UPDATE' AND NEW.name IS NULL THEN
	    UPDATE test_case SET project_name = NULL WHERE project_id = NEW.id;
	    UPDATE project_execution_info SET project_name = NULL WHERE project_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "project_update_update_test_case" ( ) RENAME TO project_update_update_others;
ALTER TRIGGER "project_after_update_update_test_case" ON "project" RENAME TO project_after_update_update_others;
--------------------------------------------------------------------------------------------------------