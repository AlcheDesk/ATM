ALTER TABLE "instruction" ADD COLUMN driver_alias text;
ALTER TABLE "driver_pack_driver_link" ADD COLUMN driver_alias text;
--------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE driver_pack_driver_aliases_map
    (
        id bigserial NOT NULL,
        driver_pack_id bigint NOT NULL,
        driver_aliases text[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT driver_pack_driver_aliases_map_fk_driver_pack FOREIGN KEY (driver_pack_id)
        REFERENCES "driver_pack" ("id"),
        CONSTRAINT driver_pack_driver_aliases_map_ix_driver_pack UNIQUE (driver_pack_id)
    );
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_insert_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into driver_pack_driver_aliases_map (driver_pack_id, driver_aliases) 
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
	UPDATE driver_pack_driver_aliases_map
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
	UPDATE driver_pack_driver_aliases_map
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
CREATE OR REPLACE FUNCTION "driver_pack_update_delete_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM driver_pack_driver_aliases_map
		WHERE driver_pack_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_pack_before_update_delete_dpdam"
  BEFORE UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_update_delete_dpdam();
--------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE test_case_driver_aliases_map
    (
        id bigserial NOT NULL,
        test_case_id bigint NOT NULL,
        driver_aliases text[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT test_case_driver_aliases_map_fk_test_case FOREIGN KEY (test_case_id)
        REFERENCES "test_case" ("id"),
        CONSTRAINT test_case_driver_aliases_map_ix_test_case UNIQUE (test_case_id)
    );
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_insert_test_case_driver_aliases_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into test_case_driver_aliases_map (test_case_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_insert_tcdam"
  AFTER INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_insert_test_case_driver_aliases_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_update_delete_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_driver_aliases_map
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_before_update_delete_tcdam"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_update_delete_tcdam();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_insert_update_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.driver_alias IS NOT NULL THEN
		UPDATE test_case_driver_aliases_map
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
CREATE OR REPLACE FUNCTION "instruction_delete_update_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE AND OLD.driver_alias IS NOT NULL THEN
		UPDATE test_case_driver_aliases_map
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
    ELSIF OLD.driver_alias <> NEW.driver_alias AND NEW.driver_alias IS NULL THEN
    	UPDATE test_case_driver_aliases_map
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
	ELSIF OLD.driver_alias <> NEW.driver_alias AND NEW.driver_alias IS NOT NULL THEN
	    UPDATE test_case_driver_aliases_map
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

CREATE TRIGGER "instruction_before_delete_update_tcdam"
  BEFORE UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_delete_update_tcdam();
--------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------