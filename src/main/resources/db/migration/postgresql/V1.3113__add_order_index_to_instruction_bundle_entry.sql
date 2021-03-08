ALTER TABLE "instruction_bundle_entry" ADD COLUMN order_index INTEGER;
UPDATE "instruction_bundle_entry" SET order_index = 0 WHERE order_index IS NULL;
ALTER TABLE "instruction_bundle_entry" ALTER COLUMN order_index SET NOT NULL;
