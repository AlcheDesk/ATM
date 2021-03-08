ALTER TABLE "run_set" ADD COLUMN aliases text[];
UPDATE "run_set" rs SET 
aliases = rsanm.alias_names
FROM run_set_alias_name_map rsanm 
WHERE rsanm.run_set_id = rs.id;
ALTER TABLE "run_set" ALTER COLUMN aliases SET DEFAULT array[]::text[];
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_alias_name_map_insert_update_run_set" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update run_set set aliases = NEW.alias_names
	where id = NEW.run_set_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_alias_name_map_after_insert_update_run_set"
  AFTER INSERT ON run_set_alias_name_map
  FOR EACH ROW
EXECUTE PROCEDURE run_set_alias_name_map_insert_update_run_set();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_alias_name_map_update_run_set" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.alias_names <> OLD.alias_names THEN
	    update run_set set aliases = NEW.alias_names
		where id = NEW.run_set_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_alias_name_map_after_update_update_run_set"
  AFTER UPDATE ON run_set_alias_name_map
  FOR EACH ROW
EXECUTE PROCEDURE run_set_alias_name_map_update_run_set();
------------------------------------------------------------------------------