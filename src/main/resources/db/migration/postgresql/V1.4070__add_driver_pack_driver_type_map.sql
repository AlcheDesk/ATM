CREATE TABLE driver_pack_driver_type_map
    (
        id bigserial NOT NULL,
        driver_pack_id bigint NOT NULL,
        driver_types bigint[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT driver_pack_driver_type_map_fk_driver_pack FOREIGN KEY (driver_pack_id)
        REFERENCES "driver_pack" ("id"),
        CONSTRAINT driver_pack_driver_type_map_ix_driver_pack UNIQUE (driver_pack_id)
    );
-----------------------------------------------------------------------------
INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (51, 'driver_pack_driver_type_map');
-----------------------------------------------------------------------------
insert into driver_pack_driver_type_map (driver_pack_id, driver_types)
select link.driver_pack_id, array_agg(distinct(d.driver_type_id)) from driver_pack_driver_link link inner join
driver d on d.id = link.driver_id
group by driver_pack_id;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dpdtl_update_update_driver_pack_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
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
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dpdtl_after_update_driver_pack_driver_type_map"
  AFTER UPDATE ON driver_pack_driver_link
  FOR EACH ROW
EXECUTE PROCEDURE dpdtl_update_update_driver_pack_driver_type_map();
----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dpdtl_insert_update_driver_pack_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(d.driver_type_id)) as d_types from driver_pack_driver_link link inner join
       driver d on d.id = link.driver_id
       where link.driver_pack_id = NEW.driver_pack_id) types
	WHERE 
	  driver_pack_id = NEW.driver_pack_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dpdtl_insert_update_driver_pack_driver_type_map"
  AFTER INSERT ON driver_pack_driver_link
  FOR EACH ROW
EXECUTE PROCEDURE dpdtl_insert_update_driver_pack_driver_type_map();
---------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dpdtl_delete_update_driver_pack_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE driver_pack_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(d.driver_type_id)) as d_types from driver_pack_driver_link link inner join
       driver d on d.id = link.driver_id
       where link.driver_pack_id = OLD.driver_pack_id) types
	WHERE 
	  driver_pack_id = OLD.driver_pack_id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dpdtl_delete_update_driver_pack_driver_type_map"
  BEFORE DELETE ON driver_pack_driver_link
  FOR EACH ROW
EXECUTE PROCEDURE dpdtl_delete_update_driver_pack_driver_type_map();
---------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "driver_pack_insert_insert_driver_pack_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	insert into driver_pack_driver_type_map (driver_pack_id) 
	values (NEW.id);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_pack_after_insert_driver_pack_driver_type_map"
  AFTER INSERT ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE driver_pack_insert_insert_driver_pack_driver_type_map();
---------------------------------------------------------------------------------------