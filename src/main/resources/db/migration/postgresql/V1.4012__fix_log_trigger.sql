CREATE OR REPLACE FUNCTION "update_log_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF OLD.log IS NOT NULL THEN
		NEW.log = OLD.log || chr(10) || NEW.log;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;