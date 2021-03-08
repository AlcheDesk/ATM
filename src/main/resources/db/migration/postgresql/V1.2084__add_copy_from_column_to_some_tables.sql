-- test case
ALTER TABLE "test_case" ADD COLUMN copy_from_id bigint;
ALTER TABLE "test_case" ADD CONSTRAINT test_case_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "test_case" ("id");
-- project
ALTER TABLE "project" ADD COLUMN copy_from_id bigint;
ALTER TABLE "project" ADD CONSTRAINT project_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "project" ("id");
-- driver pack
ALTER TABLE "driver_pack" ADD COLUMN copy_from_id bigint;
ALTER TABLE "driver_pack" ADD CONSTRAINT driver_pack_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "driver_pack" ("id");
-- instruction
ALTER TABLE "instruction" ADD COLUMN copy_from_id bigint;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "instruction" ("id");