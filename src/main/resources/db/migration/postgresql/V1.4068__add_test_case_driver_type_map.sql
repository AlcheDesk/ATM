CREATE TABLE test_case_driver_type_map
    (
        id bigserial NOT NULL,
        test_case_id bigint NOT NULL,
        driver_types bigint[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT test_case_driver_type_map_fk_test_case FOREIGN KEY (test_case_id)
        REFERENCES "test_case" ("id"),
        CONSTRAINT test_case_driver_type_map_ix_test_case UNIQUE (test_case_id)
    );
-----------------------------------------------------------------------------
INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (50, 'test_case_driver_type_map');
-----------------------------------------------------------------------------
insert into test_case_driver_type_map (test_case_id, driver_types)
select tsitmap.test_case_id, array_agg(distinct(link.driver_type_id)) from driver_type_instruction_type_link link inner join
test_case_instruction_type_map tsitmap on link.instruction_type_id = ANY (tsitmap.instruction_types)
group by test_case_id;

CREATE OR REPLACE FUNCTION "test_case_instruction_types_map_update_test_case_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
	UPDATE test_case_driver_type_map
	SET 
	  driver_types = types.d_types
	FROM
	  (select array_agg(distinct(link.driver_type_id)) as d_types from driver_type_instruction_type_link link 
	   where link.instruction_type_id = ANY (NEW.instruction_types)
	  ) types
	WHERE 
	  test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "tcitm_after_update_test_case_driver_type_map"
  AFTER UPDATE ON test_case_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE test_case_instruction_types_map_update_test_case_driver_type_map();

CREATE OR REPLACE FUNCTION "test_case_instruction_types_map_insert_test_case_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into test_case_driver_type_map (test_case_id, driver_types) 
	values (NEW.test_case_id, 
	        (select array_agg(distinct(link.driver_type_id)) from driver_type_instruction_type_link link where link.instruction_type_id = ANY (NEW.instruction_types))
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "tcitm_after_insert_test_case_driver_type_map"
  AFTER INSERT ON test_case_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE test_case_instruction_types_map_insert_test_case_driver_type_map();

CREATE OR REPLACE FUNCTION "test_case_instruction_types_map_delete_test_case_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	delete from test_case_driver_type_map where test_case_id = OLD.test_case_id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "tcitm_before_delete_test_case_driver_type_map"
  BEFORE DELETE ON test_case_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE test_case_instruction_types_map_delete_test_case_driver_type_map();
---------------------------------------------------------------------------------------