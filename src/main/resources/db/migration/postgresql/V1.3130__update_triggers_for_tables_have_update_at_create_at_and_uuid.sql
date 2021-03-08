DROP TRIGGER "test_case_update_created_at_updated_at" ON "test_case";
DROP TRIGGER "test_case_update_uuid" ON "test_case";

DROP TRIGGER "run_update_created_at_updated_at" ON "run";
DROP TRIGGER "run_update_uuid" ON "run";

DROP TRIGGER "run_set_update_created_at_updated_at" ON "run_set";
DROP TRIGGER "run_set_update_uuid" ON "run_set";

DROP TRIGGER "run_set_result_update_created_at_updated_at" ON "run_set_result";
DROP TRIGGER "run_set_result_update_uuid" ON "run_set_result";

CREATE OR REPLACE FUNCTION "update_updated_at_created_at_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    NEW.uuid = OLD.uuid;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

CREATE TRIGGER "run_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

CREATE TRIGGER "run_set_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();

CREATE TRIGGER "run_set_result_update_created_at_updated_at_uuid"
  BEFORE UPDATE ON run_set_result
  FOR EACH ROW
EXECUTE PROCEDURE update_updated_at_created_at_uuid_column();
