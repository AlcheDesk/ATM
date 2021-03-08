ALTER TABLE "run" disable TRIGGER "run_before_update_timestamp_with_uuid";
ALTER TABLE "run_set_result" disable TRIGGER "run_set_result_before_update_timestamp_with_uuid";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_before_update_timestamp";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_before_update_timestamp";
UPDATE "dev_instruction_result" SET end_at = now() WHERE is_finished IS TRUE AND end_at IS NULL;
UPDATE "dev_instruction_result" SET end_at = NULL WHERE is_finished IS FALSE AND end_at IS NOT NULL;
UPDATE "prod_instruction_result" SET end_at = now() WHERE is_finished IS TRUE AND end_at IS NULL;
UPDATE "prod_instruction_result" SET end_at = NULL WHERE is_finished IS FALSE AND end_at IS NOT NULL;
UPDATE "run" SET end_at = now() WHERE is_finished IS TRUE AND end_at IS NULL;
UPDATE "run" SET end_at = NULL WHERE is_finished IS FALSE AND end_at IS NOT NULL;
UPDATE "run_set_result" SET end_at = now() WHERE is_finished IS TRUE AND end_at IS NULL;
UPDATE "run_set_result" SET end_at = NULL WHERE is_finished IS FALSE AND end_at IS NOT NULL;
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
	IF TG_OP = 'UPDATE' AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE THEN
		NEW.end_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.is_finished IS TRUE THEN
	    NEW.end_at = OLD.end_at;
	    NEW.is_finished = OLD.is_finished;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "run_generate_columns"
  BEFORE INSERT OR UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE run_generate_columns();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE THEN
		NEW.end_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.is_finished IS TRUE THEN
	    NEW.end_at = OLD.end_at;
	    NEW.is_finished = OLD.is_finished;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "run_set_result_generate_columns"
  BEFORE INSERT OR UPDATE ON run_set_result
  FOR EACH ROW
EXECUTE PROCEDURE run_set_result_generate_columns();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE THEN
		NEW.end_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.is_finished IS TRUE THEN
	    NEW.end_at = OLD.end_at;
	    NEW.is_finished = OLD.is_finished;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "dev_instruction_result_generate_columns"
  BEFORE INSERT OR UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE dev_instruction_result_generate_columns();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_instruction_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' AND NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE THEN
		NEW.end_at = now();
	ELSIF TG_OP = 'UPDATE' AND OLD.is_finished IS TRUE THEN
	    NEW.end_at = OLD.end_at;
	    NEW.is_finished = OLD.is_finished;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.end_at = NULL;
		NEW.is_finished = FALSE;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "prod_instruction_result_generate_columns"
  BEFORE INSERT OR UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE prod_instruction_result_generate_columns();
--------------------------------------------------------------------------------------------------------
ALTER TABLE "run" ADD CONSTRAINT run_ck_finished_end_at CHECK 
(
    (is_finished IS FALSE AND end_at IS NULL)
    OR  
    (is_finished IS TRUE AND end_at IS NOT NULL)
);
ALTER TABLE "run_set_result" ADD CONSTRAINT run_set_result_ck_finished_end_at CHECK 
(
    (is_finished IS FALSE AND end_at IS NULL)
    OR  
    (is_finished IS TRUE AND end_at IS NOT NULL)
);
ALTER TABLE "prod_instruction_result" ADD CONSTRAINT prod_instruction_result_ck_finished_end_at CHECK 
(
    (is_finished IS FALSE AND end_at IS NULL)
    OR  
    (is_finished IS TRUE AND end_at IS NOT NULL)
);
ALTER TABLE "dev_instruction_result" ADD CONSTRAINT dev_instruction_result_ck_finished_end_at CHECK 
(
    (is_finished IS FALSE AND end_at IS NULL)
    OR  
    (is_finished IS TRUE AND end_at IS NOT NULL)
);
--------------------------------------------------------------------------------------------------------