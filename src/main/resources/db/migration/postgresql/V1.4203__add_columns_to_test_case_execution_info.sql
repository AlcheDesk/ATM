UPDATE "run_execution_info" SET run_start_at = (SELECT start_at FROM run WHERE id = run_id) WHERE run_start_at IS NULL;
UPDATE "run_execution_info" SET run_end_at = (SELECT end_at FROM run WHERE id = run_id) WHERE run_end_at IS NULL;
UPDATE "run_execution_info" SET duration = EXTRACT(EPOCH FROM (run_end_at - run_start_at)) WHERE duration IS NULL AND run_start_at IS NOT NULL AND run_end_at IS NOT NULL;
UPDATE "run_execution_info" SET duration = 0 WHERE duration IS NULL;
CREATE OR REPLACE FUNCTION "run_execution_info_section_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
        -- keep run_start_at
	    NEW.run_start_at = OLD.run_start_at;
	    -- keep duration
        NEW.duration = OLD.duration;
	    -- generate run_end_at
	    IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE THEN
	        NEW.run_end_at = now();
	    ELSE
	        NEW.run_end_at = NULL;
	    ENd IF;
	    -- calculate the duration
		IF OLD.run_start_at IS NOT NULL AND 
		   NEW.run_end_at IS NOT NULL AND 
		   NEW.is_finished IS TRUE AND 
		   NEW.run_end_at >= OLD.run_start_at AND 
		   OLD.duration = 0 THEN
			NEW.duration = EXTRACT(EPOCH FROM (NEW.run_end_at - OLD.run_start_at));
	    END IF;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.run_start_at = NULL;
		NEW.run_end_at = NULL;
		NEW.duration = 0;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "run_execution_info_section_generate_columns"
  BEFORE INSERT OR UPDATE ON run_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE run_execution_info_section_generate_columns();
----------------------------------------------------------------------------------------------------------------------------------    
ALTER TABLE "test_case_execution_info" ADD COLUMN test_case_is_deleted BOOLEAN;
UPDATE "test_case_execution_info" SET test_case_is_deleted = (SELECT is_deleted FROM test_case WHERE id = test_case_id) WHERE test_case_is_deleted IS NULL;
ALTER TABLE "test_case_execution_info" ALTER COLUMN test_case_is_deleted SET NOT NULL;
CREATE OR REPLACE FUNCTION "test_case_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN 
	  IF NEW.project_id IS NOT NULL AND NEW.project_id IS DISTINCT FROM OLD.project_id THEN
		NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	  ELSIF NEW.project_id IS NULL THEN
	    NEW.project_name = NULL;
	  END IF;
	END IF;
	 
	IF TG_OP = 'INSERT' THEN
	    NEW.is_deleted = FALSE;
		IF NEW.project_id IS NOT NULL THEN
			NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
		ELSE
		    NEW.project_name = NULL;
		END IF;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------   