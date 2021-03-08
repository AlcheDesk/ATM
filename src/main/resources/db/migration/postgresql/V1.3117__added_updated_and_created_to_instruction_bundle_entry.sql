ALTER TABLE "instruction_bundle_entry" ADD COLUMN created_at TIMESTAMP WITH TIME zone;
UPDATE "instruction_bundle_entry" SET created_at = now() WHERE created_at IS NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN created_at SET DEFAULT now();
ALTER TABLE "instruction_bundle_entry" ADD COLUMN updated_at TIMESTAMP WITH TIME zone;
UPDATE "instruction_bundle_entry" SET updated_at = now() WHERE updated_at IS NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN updated_at SET NOT NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN updated_at SET DEFAULT now();

CREATE TRIGGER "instruction_bundle_entry_insert_created_at_updated_at"
  BEFORE INSERT ON instruction_bundle_entry
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

CREATE TRIGGER "instruction_bundle_entry_update_created_at_updated_at"
  BEFORE UPDATE ON instruction_bundle_entry
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();

DROP TRIGGER "instruction_bundle_insert_created_at" ON "instruction_bundle";
DROP TRIGGER "instruction_bundle_update_created_at" ON "instruction_bundle";

CREATE TRIGGER "instruction_bundle_insert_created_at_updated_at"
  BEFORE INSERT ON instruction_bundle
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

CREATE TRIGGER "instruction_bundle_update_created_at_updated_at"
  BEFORE UPDATE ON instruction_bundle
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();