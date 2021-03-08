DROP FUNCTION "test_case_update_delete_tcdam";
DROP TRIGGER "instruction_before_update_update_tcdam" ON instruction;
DROP FUNCTION "instruction_update_update_tcdam";
DELETE FROM "test_case_driver_alias_map" WHERE test_case_id IN (SELECT id FROM test_case WHERE is_deleted IS TRUE);
DELETE FROM "driver_pack_driver_alias_map" WHERE driver_pack_id IN (SELECT id FROM driver_pack WHERE is_deleted IS TRUE);

CREATE OR REPLACE FUNCTION "instruction_update_update_tcdam" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	    UPDATE test_case_driver_alias_map
		SET 
		  driver_aliases = names.aliases
		FROM
		  (SELECT 
		  	CASE
				WHEN array_agg(distinct(driver_alias)) = '{null}'::text[] THEN '{}'::text[]
				WHEN array_agg(distinct(driver_alias)) IS NULL THEN '{}'::text[]
				ELSE array_agg(distinct(driver_alias))
			END
		  AS aliases FROM instruction ins
		  WHERE ins.driver_alias IS NOT NULL AND ins.is_deleted = FALSE AND ins.test_case_id = NEW.test_case_id) names
		WHERE 
		  test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_after_update_update_tcdam"
  AFTER UPDATE ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_update_update_tcdam();