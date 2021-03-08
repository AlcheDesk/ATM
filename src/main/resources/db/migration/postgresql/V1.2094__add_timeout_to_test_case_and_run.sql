-- add the time_out column to the test_case table
ALTER TABLE "test_case" ADD COLUMN timeout INTEGER;
UPDATE "test_case" SET timeout = 60 WHERE timeout IS NULL;
ALTER TABLE "test_case" ALTER COLUMN timeout SET NOT NULL;
ALTER TABLE "test_case" ALTER COLUMN timeout SET DEFAULT 60;

-- add the time_out column to the run table
ALTER TABLE "run" ADD COLUMN timeout INTEGER;
UPDATE "run" SET timeout = 60 WHERE timeout IS NULL;
ALTER TABLE "run" ALTER COLUMN timeout SET NOT NULL;
ALTER TABLE "run" ALTER COLUMN timeout SET DEFAULT 60;
