ALTER TABLE "driver_pack_driver_aliases_map" RENAME TO driver_pack_driver_alias_map;
ALTER TABLE "test_case_driver_aliases_map" RENAME TO test_case_driver_alias_map;
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
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_update_delete_dpdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM driver_pack_driver_alias_map
		WHERE driver_pack_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_insert_test_case_driver_alias_map" ()  RETURNS trigger
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
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_update_delete_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.is_deleted IS TRUE THEN
		DELETE FROM test_case_driver_alias_map
		WHERE test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
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
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_delete_update_tcdam" ()  RETURNS trigger
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
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
insert into test_case_driver_alias_map (test_case_id)
select tc.id as test_case_id from test_case tc;
insert into driver_pack_driver_alias_map (driver_pack_id)
select dp.id as driver_pack_id from driver_pack dp;