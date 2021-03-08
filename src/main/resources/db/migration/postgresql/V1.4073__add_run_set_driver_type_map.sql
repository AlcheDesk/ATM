CREATE TABLE run_set_driver_type_map
    (
        id bigserial NOT NULL,
        run_set_id bigint NOT NULL,
        driver_types bigint[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT run_set_driver_type_map_fk_run_set FOREIGN KEY (run_set_id)
        REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_driver_type_map_ix_run_set UNIQUE (run_set_id)
    );
-----------------------------------------------------------------------------
INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (53, 'run_set_driver_type_map');
-----------------------------------------------------------------------------
insert into run_set_driver_type_map (run_set_id, driver_types)
select tsitmap.run_set_id, array_agg(distinct(link.driver_type_id)) from driver_type_instruction_type_link link inner join
run_set_instruction_type_map tsitmap on link.instruction_type_id = ANY (tsitmap.instruction_types)
group by run_set_id;
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsitm_update_run_set_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
	UPDATE run_set_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(link.driver_type_id)) as d_types from driver_type_instruction_type_link link 
	   where link.instruction_type_id = ANY (NEW.instruction_types)
	  ) types
	WHERE 
	  run_set_id = NEW.run_set_id;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "rsitm_after_update_run_set_driver_type_map"
  AFTER UPDATE ON run_set_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE rsitm_update_run_set_driver_type_map();
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsitm_insert_run_set_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into run_set_driver_type_map (run_set_id, driver_types) 
	values (NEW.run_set_id, 
	        (select array_agg(distinct(link.driver_type_id)) from driver_type_instruction_type_link link where link.instruction_type_id = ANY (NEW.instruction_types))
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "rsitm_after_insert_run_set_driver_type_map"
  AFTER INSERT ON run_set_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE rsitm_insert_run_set_driver_type_map();
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsitm_delete_run_set_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	delete from run_set_driver_type_map where run_set_id = OLD.run_set_id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "rsitm_before_delete_run_set_driver_type_map"
  BEFORE DELETE ON run_set_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE rsitm_delete_run_set_driver_type_map();
---------------------------------------------------------------------------------------