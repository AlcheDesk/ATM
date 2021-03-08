CREATE OR REPLACE FUNCTION "run_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id <> OLD.status_id OR 
	   NEW.is_finished <> OLD.is_finished OR 
	   NEW.result_overwritten <> OLD.result_overwritten OR
	   NEW.start_at <> OLD.start_at OR
	   NEw.end_at <> OLD.end_at THEN
			update run_execution_info set
			run_status_id = NEW.status_id,
			run_updated_at = NEW.updated_at,
			run_result_overwritten = NEW.result_overwritten,
			is_finished = NEW.is_finished,
			run_start_at = NEW.start_at,
			run_end_at = NEW.end_at
			where test_case_id = NEW.test_case_id and run_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------    
CREATE OR REPLACE FUNCTION "run_execution_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'UPDATE' THEN
        -- keep run_start_at
	    NEW.run_start_at = OLD.run_start_at;
	    -- keep duration
        NEW.duration = OLD.duration;

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