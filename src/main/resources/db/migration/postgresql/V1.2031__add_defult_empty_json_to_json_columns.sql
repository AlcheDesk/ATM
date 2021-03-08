ALTER TABLE "application" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "application" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "application" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "driver" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "driver" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "driver" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "driver_entry" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "driver_entry" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "driver_entry" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "driver_entry" ALTER COLUMN properties SET DEFAULT '{}'::json;
UPDATE "driver_entry" SET properties = '{}'::json WHERE properties IS NULL;
ALTER TABLE "driver_entry" ALTER COLUMN properties SET NOT NULL;

ALTER TABLE "element" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "element" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "element" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "run" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "run" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "run" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "storage" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "storage" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "storage" ALTER COLUMN parameter SET NOT NULL;


ALTER TABLE "dev_file" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "dev_file" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "dev_file" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "dev_instruction_result" ALTER COLUMN input_parameter SET DEFAULT '{}'::json;
UPDATE "dev_instruction_result" SET input_parameter = '{}'::json WHERE input_parameter IS NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN input_parameter SET NOT NULL;

ALTER TABLE "dev_instruction_result" ALTER COLUMN output_parameter SET DEFAULT '{}'::json;
UPDATE "dev_instruction_result" SET output_parameter = '{}'::json WHERE output_parameter IS NULL;
ALTER TABLE "dev_instruction_result" ALTER COLUMN output_parameter SET NOT NULL;

ALTER TABLE "prod_file" ALTER COLUMN parameter SET DEFAULT '{}'::json;
UPDATE "prod_file" SET parameter = '{}'::json WHERE parameter IS NULL;
ALTER TABLE "prod_file" ALTER COLUMN parameter SET NOT NULL;

ALTER TABLE "prod_instruction_result" ALTER COLUMN input_parameter SET DEFAULT '{}'::json;
UPDATE "prod_instruction_result" SET input_parameter = '{}'::json WHERE input_parameter IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN input_parameter SET NOT NULL;

ALTER TABLE "prod_instruction_result" ALTER COLUMN output_parameter SET DEFAULT '{}'::json;
UPDATE "prod_instruction_result" SET output_parameter = '{}'::json WHERE output_parameter IS NULL;
ALTER TABLE "prod_instruction_result" ALTER COLUMN output_parameter SET NOT NULL;