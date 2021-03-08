DROP TRIGGER "driver_pack_after_insert_insert_dpdam" ON driver_pack;
DROP TRIGGER "dpdl_after_insert_or_update_update_dpdam" ON driver_pack_driver_link;
DROP TRIGGER "driver_after_delete_update_dpdam" ON driver_pack_driver_link;
DROP TRIGGER "driver_pack_before_update_delete_dpdam" ON driver_pack;
DROP TRIGGER "test_case_after_insert_tcdam" ON test_case;
DROP TRIGGER "test_case_before_update_delete_tcdam" ON test_case;
DROP TRIGGER "instruction_after_insert_update_tcdam" ON instruction;
DROP TRIGGER "instruction_before_delete_update_tcdam" ON instruction;

DROP FUNCTION "driver_pack_insert_dpdam";
DROP FUNCTION "driver_pack_driver_link_update_update_dpdam";
DROP FUNCTION "driver_pack_driver_link_delete_update_dpdam";
DROP FUNCTION "driver_pack_update_delete_dpdam";
DROP FUNCTION "test_case_insert_test_case_driver_aliases_map";
DROP FUNCTION "test_case_update_delete_tcdam";
DROP FUNCTION "instruction_insert_update_tcdam";
DROP FUNCTION "instruction_delete_update_tcdam";
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_insert_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into driver_pack_driver_alias_map (driver_pack_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_pack_after_insert_insert_dpdam"
  AFTER INSERT ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_insert_dpdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_driver_link_update_update_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE driver_pack_driver_alias_map
	SET 
	  driver_aliases = names.aliases
	FROM
	  (SELECT 
	  	CASE
			WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
			WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
			ELSE array_agg(distinct(driver_alias))
		END
	  AS aliases FROM driver_pack_driver_link link
	  WHERE link.driver_pack_id = NEW.driver_pack_id) names
	WHERE 
	  driver_pack_id = NEW.driver_pack_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dpdl_after_insert_or_update_update_dpdam"
  AFTER INSERT OR UPDATE ON driver_pack_driver_link
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_driver_link_update_update_dpdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_driver_link_delete_update_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE driver_pack_driver_alias_map
	SET 
	  driver_aliases = names.aliases
	FROM
	  (SELECT 
	  	CASE
			WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
			WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
			ELSE array_agg(distinct(driver_alias))
		END
	  AS aliases FROM driver_pack_driver_link link
	  WHERE link.driver_pack_id = OLD.driver_pack_id) names
	WHERE 
	  driver_pack_id = OLD.driver_pack_id;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_after_delete_update_dpdam"
  AFTER DELETE ON driver_pack_driver_link
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_driver_link_delete_update_dpdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_update_delete_or_insert_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM driver_pack_driver_alias_map
		WHERE driver_pack_id = NEW.id;
    ELSIF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
    	insert into driver_pack_driver_alias_map (driver_pack_id, driver_aliases) 
	    values (NEW.id, 
	        '{}'::text[]
	       );
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_pack_before_update_delete_or_insert_dpdam"
  BEFORE UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_update_delete_or_insert_dpdam();
--------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_insert_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into test_case_driver_alias_map (test_case_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_insert_tcdam"
  AFTER INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_insert_tcdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_update_delete_or_insert_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM test_case_driver_alias_map
		WHERE test_case_id = NEW.id;
    ELSIF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
    	insert into test_case_driver_alias_map (test_case_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_before_update_delete_or_insert_tcdam"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_update_delete_or_insert_tcdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_insert_update_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.driver_alias IS NOT NULL THEN
		UPDATE test_case_driver_alias_map
		SET 
		  driver_aliases = names.aliases
		FROM
		  (SELECT 
		  	CASE
				WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
				WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
				ELSE array_agg(distinct(driver_alias))
			END
		  AS aliases FROM instruction ins
		  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
		  test_case_id = NEW.test_case_id;
    END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_after_insert_update_tcdam"
  AFTER INSERT ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_insert_update_tcdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_update_update_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF (NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE AND OLD.driver_alias IS NOT NULL) 
	   OR
	   (OLD.driver_alias <> NEW.driver_alias AND NEW.driver_alias IS NULL) THEN
		UPDATE test_case_driver_alias_map
		SET 
		  driver_aliases = names.aliases
		FROM
		  (SELECT 
		  	CASE
				WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
				WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
				ELSE array_agg(distinct(driver_alias))
			END
		  AS aliases FROM instruction ins
		  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.id <> NEW.id AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
		  test_case_id = NEW.test_case_id;
	ELSIF (NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE AND NEW.driver_alias IS NOT NULL) 
	      OR
	       (OLD.driver_alias <> NEW.driver_alias AND NEW.driver_alias IS NOT NULL)  THEN
	    UPDATE test_case_driver_alias_map
		SET 
		  driver_aliases = names.aliases
		FROM
		  (SELECT 
		  	CASE
				WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[] || NEW.driver_alias
				WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[] || NEW.driver_alias
				ELSE array_agg(distinct(driver_alias)) || NEW.driver_alias
			END
		  AS aliases FROM instruction ins
		  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.id <> NEW.id AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
		  test_case_id = NEW.test_case_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION "test_case_update_delete_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		DELETE FROM test_case_driver_alias_map
		WHERE test_case_id = NEW.id;
    ELSIF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
    	insert into test_case_driver_aliases_map (test_case_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_before_update_update_tcdam"
  BEFORE UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_update_update_tcdam();
------------------------------------------------------------------------------
