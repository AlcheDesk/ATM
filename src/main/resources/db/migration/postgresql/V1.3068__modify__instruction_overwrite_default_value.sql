ALTER TABLE "instruction_overwrite" ALTER COLUMN data SET DEFAULT '[]'::json;
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_ix_tco_tc_ins UNIQUE ("test_case_overwrite_id", "test_case_id", "instruction_id");
ALTER TABLE "instruction_overwrite" RENAME CONSTRAINT "instruction_overwrite_ix_test_case_overwrite" TO instruction_overwrite_ix_tco_tc_ins_ele;
UPDATE "instruction_overwrite" SET data = '[]'::json WHERE data::text = '{}';