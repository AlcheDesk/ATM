ALTER TABLE "run_set" ADD COLUMN uuid uuid;
UPDATE "run_set" SET uuid = uuid_generate_v4() WHERE uuid IS NULL;
ALTER TABLE "run_set" ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE "run_set" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();

ALTER TABLE "run_set_result" ADD COLUMN uuid uuid;
UPDATE "run_set_result" SET uuid = uuid_generate_v4() WHERE uuid IS NULL;
ALTER TABLE "run_set_result" ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();

ALTER TABLE "run" ADD COLUMN uuid uuid;
UPDATE "run" SET uuid = uuid_generate_v4() WHERE uuid IS NULL;
ALTER TABLE "run" ALTER COLUMN uuid SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN uuid SET DEFAULT uuid_generate_v4();

ALTER TABLE "run" ADD COLUMN test_case_uuid uuid;
UPDATE "run" SET test_case_uuid = tc.uuid FROM (SELECT * FROM test_case) tc WHERE tc.id = test_case_id;
ALTER TABLE "run" DROP CONSTRAINT "run_fk_test_case";
ALTER TABLE "test_case" ADD CONSTRAINT test_case_ix_id_uuid UNIQUE ("id", "uuid");
ALTER TABLE "run" ADD CONSTRAINT run_fk_test_case FOREIGN KEY ("test_case_id", "test_case_uuid") REFERENCES "test_case" ("id", "uuid");

ALTER TABLE "run" ADD COLUMN singleton BOOLEAN;
UPDATE "run" SET singleton = false WHERE singleton IS NULL;
ALTER TABLE "run" ALTER COLUMN singleton SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN singleton SET DEFAULT false;

-----------------------------------
CREATE OR REPLACE FUNCTION "update_uuid_column" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    NEW.uuid = OLD.uuid;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;


------------------------------------
CREATE TRIGGER "test_case_update_uuid"
  BEFORE UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_uuid_column();
------------------------------------
CREATE TRIGGER "run_set_update_uuid"
  BEFORE UPDATE ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE update_uuid_column();
------------------------------------
CREATE TRIGGER "run_set_result_update_uuid"
  BEFORE UPDATE ON run_set_result
  FOR EACH ROW
EXECUTE PROCEDURE update_uuid_column();
------------------------------------
CREATE TRIGGER "run_update_uuid"
  BEFORE UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE update_uuid_column();

