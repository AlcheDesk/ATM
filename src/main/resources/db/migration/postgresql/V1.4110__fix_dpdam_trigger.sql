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
	  WHERE link.driver_alias IS NOT NULL AND link.driver_pack_id = NEW.driver_pack_id) names
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
	  WHERE link.driver_alias IS NOT NULL AND link.driver_pack_id = OLD.driver_pack_id) names
	WHERE 
	  driver_pack_id = OLD.driver_pack_id;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------