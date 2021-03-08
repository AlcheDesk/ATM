CREATE OR REPLACE FUNCTION "public"."driver_pack_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into driver_pack_driver_type_map (driver_pack_id, driver_types) 
	values (NEW.id,
	        '{5}'::bigint[]
	       );
	
	insert into driver_pack_driver_alias_map (driver_pack_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql