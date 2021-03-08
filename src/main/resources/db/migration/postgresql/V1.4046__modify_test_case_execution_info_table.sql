ALTER TABLE "test_case_instruction_type_map" ALTER COLUMN instruction_types SET DEFAULT '{}';
UPDATE "test_case_instruction_type_map" SET instruction_types = '{}'::bigint[] WHERE instruction_types IS NULL;
ALTER TABLE "test_case_instruction_type_map" ALTER COLUMN instruction_types SET NOT NULL;

CREATE OR REPLACE FUNCTION "update_test_case_instruction_types_map_after_test_case_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into test_case_instruction_type_map (test_case_id, instruction_types) 
	values (NEW.id, 
	        (select array_agg(distinct(instruction_type_id)) from instruction where test_case_id = NEW.id)
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_insert_test_case_instruction_type_map"
  AFTER INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_instruction_types_map_after_test_case_insert();