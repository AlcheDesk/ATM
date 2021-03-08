CREATE OR REPLACE FUNCTION "driver_pack_driver_link_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	--driver_pack_driver_type_map
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select 
	    CASE
	    	WHEN array_agg(distinct(d.driver_type_id)) = '{null}'::bigint[] THEN '{5}'::bigint[]
			WHEN array_agg(distinct(d.driver_type_id)) IS NULL THEN '{5}'::bigint[]
			ELSE array_cat(array_agg(distinct(d.driver_type_id)), '{5}'::bigint[])
	    END
	   as d_types from driver_pack_driver_link link inner join
       driver d on d.id = link.driver_id
       where link.driver_pack_id = NEW.driver_pack_id) types
	WHERE 
	  driver_pack_id = NEW.driver_pack_id;
	--driver_pack_driver_alias_map  
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
$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "driver_pack_driver_link_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	--driver_pack_driver_type_map
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select 
	    CASE
	    	WHEN array_agg(distinct(d.driver_type_id)) = '{null}'::bigint[] THEN '{5}'::bigint[]
			WHEN array_agg(distinct(d.driver_type_id)) IS NULL THEN '{5}'::bigint[]
			ELSE array_cat(array_agg(distinct(d.driver_type_id)), '{5}'::bigint[])
	    END
	    as d_types from driver_pack_driver_link link inner join
       driver d on d.id = link.driver_id
       where link.driver_pack_id = NEW.driver_pack_id
	  ) types
	WHERE 
	  driver_pack_id = NEW.driver_pack_id;
	--driver_pack_driver_alias_map
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
$body$ LANGUAGE plpgsql;