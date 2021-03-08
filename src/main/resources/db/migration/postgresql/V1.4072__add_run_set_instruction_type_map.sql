CREATE TABLE run_set_instruction_type_map
    (
        id bigserial NOT NULL,
        run_set_id bigint NOT NULL,
        instruction_types bigint[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT run_set_instruction_type_map_fk_run_set FOREIGN KEY (run_set_id)
        REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_instruction_type_map_ix_run_set UNIQUE (run_set_id)
    );
-----------------------------------------------------------------------------
INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (52, 'run_set_instruction_type_map');
-----------------------------------------------------------------------------
insert into run_set_instruction_type_map (run_set_id, instruction_types)
select  rsit.run_set_id, array_agg(distinct(rsit.intruction_type_id)) as instruction_types from
(
   select link.run_set_id, unnest(tcitmap.instruction_types) as intruction_type_id
   from run_set_test_case_link link inner join
   test_case_instruction_type_map tcitmap on link.test_case_id = tcitmap.test_case_id
) as rsit
group by run_set_id;
---------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "tcitm_update_update_run_set_instruction_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
DECLARE
        run_set_array bigint[];
        run_set bigint;
BEGIN
	    select array_agg(distinct(run_set_id)) into run_set_array from run_set_test_case_link where test_case_id = NEW.test_case_id;
	    FOREACH run_set IN ARRAY run_set_array LOOP
				UPDATE run_set_instruction_type_map
				SET 
				  instruction_types = types.ins_types
				FROM
				   (
						select  array_agg(distinct(intruction_type_id)) as ins_types from
						(
							select link.run_set_id, unnest(tcitmap.instruction_types) as intruction_type_id
							from run_set_test_case_link link inner join
							test_case_instruction_type_map tcitmap on link.test_case_id = tcitmap.test_case_id
							where run_set_id = run_set
						) as ins_type_ids
					) as types
				WHERE 
				  run_set_id = run_set;
        END LOOP;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "tcitm_after_update_run_set_instruction_type_map"
  AFTER UPDATE ON test_case_instruction_type_map
  FOR EACH ROW
EXECUTE PROCEDURE tcitm_update_update_run_set_instruction_type_map();
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_insert_insert_run_set_instruction_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into run_set_instruction_type_map (run_set_id) 
	values (NEW.id);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_after_insert_run_set_instruction_type_map"
  AFTER INSERT ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_insert_insert_run_set_instruction_type_map();
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rstcl_insert_update_run_set_instruction_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	UPDATE run_set_instruction_type_map
	SET 
	  instruction_types = types.ins_types
	FROM
	   (
			select  array_agg(distinct(intruction_type_id)) as ins_types from
			(
				select link.run_set_id, unnest(tcitmap.instruction_types) as intruction_type_id
				from run_set_test_case_link link inner join
				test_case_instruction_type_map tcitmap on link.test_case_id = tcitmap.test_case_id
				where run_set_id = NEW.run_set_id
			) as ins_type_ids
		) as types
	WHERE 
	  run_set_id = NEW.run_set_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "rstcl_after_insert_run_set_instruction_type_map"
  AFTER INSERT ON run_set_test_case_link
  FOR EACH ROW
EXECUTE PROCEDURE rstcl_insert_update_run_set_instruction_type_map();
-------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rstcl_delete_update_run_set_instruction_type_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	UPDATE run_set_instruction_type_map
	SET 
	  instruction_types = case 
				            when types.ins_types IS NULL then '{}'::bigint[]
				            else types.ins_types
			             end
	FROM
	   (
			select  array_agg(distinct(intruction_type_id)) as ins_types from
			(
				select link.run_set_id, unnest(tcitmap.instruction_types) as intruction_type_id
				from run_set_test_case_link link inner join
				test_case_instruction_type_map tcitmap on link.test_case_id = tcitmap.test_case_id
				where run_set_id = OLD.run_set_id
			) as ins_type_ids
		) as types
	WHERE 
	  run_set_id = OLD.run_set_id;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "rstcl_after_delete_run_set_instruction_type_map"
  AFTER DELETE ON run_set_test_case_link
  FOR EACH ROW
EXECUTE PROCEDURE rstcl_delete_update_run_set_instruction_type_map();
---------------------------------------------------------------------------------------