CREATE OR REPLACE FUNCTION "public"."keep_log" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF OLD.log IS NOT NULL  AND NEW.log IS NOT NULL THEN
		NEW.log = OLD.log || chr(10) || NEW.log;        
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;