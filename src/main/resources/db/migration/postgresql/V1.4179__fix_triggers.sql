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
		NEW.project_id = (NEW.test_case->>'projectId')::bigint;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER TABLE "project_execution_info" DROP CONSTRAINT "project_execution_info_fk_project";
ALTER TABLE "project_execution_info" ADD CONSTRAINT project_execution_info_fk_project FOREIGN KEY (project_id, project_name) REFERENCES "project" ("id", "name")  DEFERRABLE INITIALLY DEFERRED;