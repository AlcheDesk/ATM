------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsal_update_run_set_alias_name_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE run_set_alias_name_map
	SET 
	  alias_names = types.names
	FROM
	  (SELECT 
	  	CASE
			WHEN array_agg(distinct(name)) = '{null}'::text[] THEN '{}'::text[]
			WHEN array_agg(distinct(name)) IS NULL THEN '{}'::text[]
			ELSE array_agg(distinct(name))
		END
	  AS names FROM alias al
	  left join run_set_alias_link link
      on al.id = link.alias_id
	  WHERE al.is_deleted IS FALSE AND link.run_set_id = NEW.run_set_id) types
	WHERE 
	  run_set_id = NEW.run_set_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsal_delete_update_run_set_alias_name_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE run_set_alias_name_map
	SET 
	  alias_names = types.names
	FROM
	  (SELECT 
	  	CASE
			WHEN array_agg(distinct(name)) = '{null}'::text[] THEN '{}'::text[]
			WHEN array_agg(distinct(name)) IS NULL THEN '{}'::text[]
			ELSE array_agg(distinct(name))
		END
	  AS names FROM alias al
	  left join run_set_alias_link link
      on al.id = link.alias_id
	  WHERE al.is_deleted IS FALSE AND link.run_set_id = OLD.run_set_id) types
	WHERE 
	  run_set_id = OLD.run_set_id;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;
-------------------------------------------------------------------------------
--clean up
DELETE FROM "run_set_alias_link" WHERE run_set_id IN (SELECT id FROM run_set WHERE is_deleted IS TRUE);
DELETE FROM "run_set_alias_link" WHERE alias_id IN (SELECT id FROM alias WHERE is_deleted IS TRUE);
-- add trigger
CREATE OR REPLACE FUNCTION "run_set_delete_delete_rsal" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_alias_link
		WHERE run_set_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_after_update_delete_rsal"
  AFTER UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_delete_delete_rsal();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "alias_delete_delete_rsal" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM run_set_alias_link
		WHERE alias_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "alias_after_update_delete_rsal"
  AFTER UPDATE ON alias
  FOR EACH ROW
EXECUTE PROCEDURE alias_delete_delete_rsal();
------------------------------------------------------------------------------