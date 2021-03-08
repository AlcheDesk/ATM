-- clean up instruction_option_entry
DELETE FROM instruction_option_entry WHERE NOT ((is_value_required IS TRUE AND value IS NOT NULL) OR (is_value_required IS FALSE AND value IS NULL)); 
-- add check constraint
ALTER TABLE "instruction_option_entry" ADD CONSTRAINT instruction_option_entry_ck_is_value_required_value CHECK ((is_value_required IS TRUE AND value IS NOT NULL) OR (is_value_required IS FALSE AND value IS NULL));

--add is_value_required column
ALTER TABLE "test_case_option_entry" ADD COLUMN is_value_required BOOLEAN;
UPDATE "test_case_option_entry" SET is_value_required = FALSE WHERE is_value_required IS NULL;
ALTER TABLE "test_case_option_entry" ALTER COLUMN is_value_required SET NOT NULL;
ALTER TABLE "test_case_option_entry" ALTER COLUMN is_value_required SET DEFAULT false;
-- clean up test_case_option_entry
DELETE FROM test_case_option_entry WHERE NOT ((is_value_required IS TRUE AND value IS NOT NULL) OR (is_value_required IS FALSE AND value IS NULL)); 
-- add check constraint
ALTER TABLE "test_case_option_entry" ADD CONSTRAINT test_case_option_entry_ck_is_value_required_value CHECK ((is_value_required IS TRUE AND value IS NOT NULL) OR (is_value_required IS FALSE AND value IS NULL));
