ALTER TABLE "run_set" ADD COLUMN priority INTEGER;
UPDATE "run_set" SET priority = 10 WHERE priority IS NULL;
ALTER TABLE "run_set" ALTER COLUMN priority SET NOT NULL;
ALTER TABLE "run_set" ALTER COLUMN priority SET DEFAULT 10;