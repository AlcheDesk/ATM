insert into test_case_instruction_type_map (test_case_id, instruction_types)
select test_case_id, array_agg(distinct(instruction_type_id)) as ins_types from instruction
where is_deleted is false
group by test_case_id;

CREATE OR REPLACE FUNCTION "update_before_test_case_instruction_types_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.test_case_id IS NOT NULL AND NEW.test_case_id <> OLD.test_case_id THEN				  
				UPDATE test_case_instruction_type_map
				SET 
				  instruction_types = types.ins_types
				FROM
				  (SELECT array_agg(DISTINCT(instruction_type_id)) AS ins_types FROM instruction ins
				  WHERE ins.is_deleted IS FALSE AND ins.id != OLD.id AND ins.test_case_id = OLD.test_case_id) types
				WHERE 
				  test_case_id = OLD.test_case_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "update_after_test_case_instruction_types_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	UPDATE test_case_instruction_type_map
	SET 
	  instruction_types = types.ins_types
	FROM
	  (SELECT array_agg(DISTINCT(instruction_type_id)) AS ins_types FROM instruction ins
	  WHERE ins.is_deleted IS FALSE AND ins.test_case_id = NEW.test_case_id) types
	WHERE 
	  test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_before_update_test_case_instruction_type_map"
  BEFORE UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE update_before_test_case_instruction_types_map();

CREATE TRIGGER "instruction_after_insert_or_update_test_case_instruction_type_map"
  AFTER INSERT OR UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE update_after_test_case_instruction_types_map();
