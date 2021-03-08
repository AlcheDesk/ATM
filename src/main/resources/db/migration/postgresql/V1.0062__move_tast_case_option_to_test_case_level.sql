ALTER TABLE "test_case_option_entry" DROP CONSTRAINT IF EXISTS "test_case_option_entry_fk_instruction";
ALTER TABLE "test_case_option_entry" DROP COLUMN "instruction_id";
ALTER TABLE "test_case_option_entry" ADD COLUMN test_case_id bigint;
ALTER TABLE "test_case_option_entry" ALTER COLUMN test_case_id SET NOT NULL;
ALTER TABLE "test_case_option_entry" ADD CONSTRAINT test_case_option_entry_fk_test_case FOREIGN KEY (test_case_id) REFERENCES "test_case" ("id");