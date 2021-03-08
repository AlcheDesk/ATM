ALTER TRIGGER "alias_after_update_delete_rsal" ON "alias" RENAME TO alias_after_update_change_others;
ALTER FUNCTION "alias_delete_delete_rsal" ( ) RENAME TO alias_update_change_others;
ALTER TRIGGER "content_archive_before_insert_md5" ON "content_archive" RENAME TO content_archive_generate_columns;
ALTER FUNCTION "insert_content_archive_md5" ( ) RENAME TO content_archive_generate_columns;
ALTER TRIGGER "driver_before_delete_driver_pack_driver_link" ON "driver" RENAME TO driver_before_delete_change_others;
ALTER FUNCTION "driver_delete_delete_driver_pack_driver_link" ( ) RENAME TO driver_delete_change_others;
CREATE OR REPLACE FUNCTION "driver_pack_insert_insert_driver_pack_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into driver_pack_driver_type_map (driver_pack_id) 
	values (NEW.id);
	
	insert into driver_pack_driver_alias_map (driver_pack_id, driver_aliases) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER TRIGGER "driver_pack_after_insert_driver_pack_driver_type_map" ON "driver_pack" RENAME TO driver_pack_after_insert_change_others;
ALTER FUNCTION "driver_pack_insert_insert_driver_pack_driver_type_map" ( ) RENAME TO driver_pack_insert_change_others;
DROP TRIGGER "driver_pack_after_insert_insert_dpdam" ON "driver_pack";
DROP FUNCTION "driver_pack_insert_dpdam" ( );
DROP TRIGGER "driver_pack_before_update_delete_or_insert_dpdam" ON "driver_pack";
CREATE TRIGGER "driver_pack_after_update_change_others"
  AFTER UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_update_delete_or_insert_dpdam();
ALTER FUNCTION "driver_pack_update_delete_or_insert_dpdam" ( ) RENAME TO driver_pack_update_change_others;
--------------------------------------------------------------------------------------------------------
ALTER TRIGGER "driver_pack_driver_link_after_insert_dpdtm" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_after_insert_change_others;
DROP TRIGGER "dpdl_after_insert_or_update_update_dpdam" ON "driver_pack_driver_link";
DROP FUNCTION "driver_pack_driver_link_update_update_dpdam" ( );
CREATE OR REPLACE FUNCTION "dpdtl_insert_update_dpdtm" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	--driver_pack_driver_type_map
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(d.driver_type_id)) as d_types from driver_pack_driver_link link inner join
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
--------------------------------------------------------------------------------------------------------
ALTER FUNCTION "dpdtl_insert_update_dpdtm" ( ) RENAME TO driver_pack_driver_link_insert_change_others;
ALTER TRIGGER "driver_pack_driver_link_after_update_dpdtm" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_after_update_change_others;
ALTER FUNCTION "dpdtl_update_update_dpdtm" ( ) RENAME TO driver_pack_driver_link_update_change_others;
CREATE OR REPLACE FUNCTION "driver_pack_driver_link_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	--driver_pack_driver_type_map
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(d.driver_type_id)) as d_types from driver_pack_driver_link link inner join
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
--------------------------------------------------------------------------------------------------------
ALTER TRIGGER "driver_after_delete_update_dpdam" ON "driver_pack_driver_link" RENAME TO driver_pack_driver_link_after_delete_change_others;
ALTER FUNCTION "driver_pack_driver_link_delete_update_dpdam" ( ) RENAME TO driver_pack_driver_link_delete_change_others;
CREATE OR REPLACE FUNCTION "driver_pack_driver_link_delete_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
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
	  WHERE link.driver_alias IS NOT NULL AND link.driver_pack_id = OLD.driver_pack_id) names
	WHERE 
	  driver_pack_id = OLD.driver_pack_id;
	--driver_pack_driver_type_map
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(d.driver_type_id)) as d_types from driver_pack_driver_link link inner join
       driver d on d.id = link.driver_id
       where link.driver_pack_id = OLD.driver_pack_id) types
	WHERE 
	  driver_pack_id = OLD.driver_pack_id;
	RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
DROP TRIGGER "driver_pack_driver_link_before_delete_dpdtm" ON "driver_pack_driver_link";
DROP FUNCTION "dpdtl_delete_update_dpdtm" ( );
ALTER TABLE "driver_pack_driver_alias_map" DROP CONSTRAINT "driver_pack_driver_aliases_map_fk_driver_pack";
ALTER TABLE "driver_pack_driver_alias_map" ADD CONSTRAINT driver_pack_driver_aliases_map_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES "driver_pack" ("id") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "driver_pack_driver_type_map" DROP CONSTRAINT "driver_pack_driver_type_map_fk_driver_pack";
ALTER TABLE "driver_pack_driver_type_map" ADD CONSTRAINT driver_pack_driver_type_map_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES "driver_pack" ("id") DEFERRABLE INITIALLY DEFERRED;
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "delete_email_notification_target" ON "email_notification_target";
CREATE OR REPLACE FUNCTION "delete_before_email_notification_target" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    DELETE FROM notification_email_notification_target_link WHERE email_notification_target_id = OLD.id;
    RETURN NULL;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "email_notification_target_after_delete_change_others"
  AFTER DELETE ON email_notification_target
  FOR EACH ROW
EXECUTE PROCEDURE delete_before_email_notification_target();
ALTER FUNCTION "delete_before_email_notification_target" ( ) RENAME TO email_notification_target_delete_change_others;
DROP TRIGGER "email_notification_target_before_delete_delete_nentl" ON
"email_notification_target";
DROP FUNCTION "email_notification_target_delete_delete_nentl" ( );
ALTER TABLE "notification_email_notification_target_link" DROP CONSTRAINT "notification_email_notification_target_link_fk_email_notificati";
ALTER TABLE "notification_email_notification_target_link" DROP CONSTRAINT "notification_email_notification_target_link_fk_notification";
ALTER TABLE "notification_email_notification_target_link" ADD CONSTRAINT notification_email_notification_target_link_fk_ent 
FOREIGN KEY (email_notification_target_id) REFERENCES "email_notification_target" ("id") DEFERRABLE INITIALLY DEFERRED;
ALTER TABLE "notification_email_notification_target_link" ADD CONSTRAINT notification_email_notification_target_link_fk_notification 
FOREIGN KEY (notification_id) REFERENCES "notification" ("id") DEFERRABLE INITIALLY DEFERRED;