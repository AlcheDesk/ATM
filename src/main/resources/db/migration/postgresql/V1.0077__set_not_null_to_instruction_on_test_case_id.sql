DELETE FROM "instruction" WHERE test_case_id IS NULL;
ALTER TABLE "instruction" ALTER COLUMN test_case_id SET NOT NULL;