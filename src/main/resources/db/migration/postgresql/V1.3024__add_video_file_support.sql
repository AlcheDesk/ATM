ALTER TABLE "dev_file" ADD COLUMN run_id bigint;
ALTER TABLE "dev_file" ALTER COLUMN "instruction_result_id" DROP NOT NULL;
ALTER TABLE "dev_file" ADD CONSTRAINT dev_file_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id");

ALTER TABLE "prod_file" ADD COLUMN run_id bigint;
ALTER TABLE "prod_file" ALTER COLUMN "instruction_result_id" DROP NOT NULL;
ALTER TABLE "prod_file" ADD CONSTRAINT prod_file_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id");

ALTER TABLE "run" ADD COLUMN is_recorded bigint;