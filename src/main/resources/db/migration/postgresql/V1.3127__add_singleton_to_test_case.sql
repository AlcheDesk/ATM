ALTER TABLE "test_case" ADD COLUMN singleton BOOLEAN;
UPDATE "test_case" SET singleton = false WHERE singleton IS NULL;
ALTER TABLE "test_case" ALTER COLUMN singleton SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN singleton SET DEFAULT false;
