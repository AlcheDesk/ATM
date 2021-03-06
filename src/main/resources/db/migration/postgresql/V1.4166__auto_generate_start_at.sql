ALTER TABLE "run" disable TRIGGER "run_before_update_timestamp_with_uuid";
ALTER TABLE "run_set_result" disable TRIGGER "run_set_result_before_update_timestamp_with_uuid";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_before_update_timestamp";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_before_update_timestamp";
UPDATE "run" SET start_at = end_at WHERE start_at IS NULL AND end_at IS NOT NULL;
UPDATE "run_set_result" SET start_at = end_at WHERE start_at IS NULL AND end_at IS NOT NULL;
UPDATE "dev_instruction_result" SET start_at = end_at WHERE start_at IS NULL AND end_at IS NOT NULL;
UPDATE "prod_instruction_result" SET start_at = end_at WHERE start_at IS NULL AND end_at IS NOT NULL;
ALTER TABLE "run" enable TRIGGER "run_before_update_timestamp_with_uuid";
ALTER TABLE "run_set_result" enable TRIGGER "run_set_result_before_update_timestamp_with_uuid";
ALTER TABLE "dev_instruction_result" enable TRIGGER "dev_instruction_result_before_update_timestamp";
ALTER TABLE "prod_instruction_result" enable TRIGGER "prod_instruction_result_before_update_timestamp";
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
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
		NEW.start_at = NULL;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_result_generate_columns" ()  RETURNS trigger
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
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
		NEW.start_at = NULL;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_instruction_result_generate_columns" ()  RETURNS trigger
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
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
		NEW.start_at = NULL;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_instruction_result_generate_columns" ()  RETURNS trigger
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
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
		NEW.start_at = NULL;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "run" ADD CONSTRAINT run_ck_start_at_end_at CHECK 
(
   NOT (start_at IS NULL AND end_at IS NOT NULL)
);
ALTER TABLE "run_set_result" ADD CONSTRAINT run_set_result_ck_start_at_end_at CHECK 
(
   NOT (start_at IS NULL AND end_at IS NOT NULL)
);
ALTER TABLE "prod_instruction_result" ADD CONSTRAINT prod_instruction_result_ck_start_at_end_at CHECK 
(
   NOT (start_at IS NULL AND end_at IS NOT NULL)
);
ALTER TABLE "dev_instruction_result" ADD CONSTRAINT dev_instruction_result_ck_start_at_end_at CHECK 
(
   NOT (start_at IS NULL AND end_at IS NOT NULL)
);
--------------------------------------------------------------------------------------------------------