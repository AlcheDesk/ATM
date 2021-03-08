CREATE OR REPLACE FUNCTION "update_test_case_instruction_types_map_after_test_case_insert" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
        type_array bigint[];
BEGIN
	select array_agg(distinct(instruction_type_id)) into type_array from instruction where test_case_id = NEW.id;
	insert into test_case_instruction_type_map (test_case_id, instruction_types) 
	values (NEW.id, 
	          case 
	            when type_array IS NULL then '{}'::bigint[]
	            else type_array
	          end
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;