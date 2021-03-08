ALTER TABLE "test_case_task_link" ADD COLUMN created_at TIMESTAMP WITH TIME zone;
UPDATE "test_case_task_link" SET created_at = now() WHERE created_at IS NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN created_at SET DEFAULT now();
ALTER TABLE "test_case_task_link" ADD COLUMN updated_at TIMESTAMP WITH TIME zone;
UPDATE "test_case_task_link" SET updated_at = now() WHERE updated_at IS NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN updated_at SET NOT NULL;
ALTER TABLE "test_case_task_link" ALTER COLUMN updated_at SET DEFAULT now();
CREATE TRIGGER "test_case_task_link_update_created_at_updated_at"
  BEFORE UPDATE ON test_case_task_link
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();
    
ALTER TABLE "run_set_job_link" ADD COLUMN created_at TIMESTAMP WITH TIME zone;
UPDATE "run_set_job_link" SET created_at = now() WHERE created_at IS NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN created_at SET NOT NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN created_at SET DEFAULT now();
ALTER TABLE "run_set_job_link" ADD COLUMN updated_at TIMESTAMP WITH TIME zone;
UPDATE "run_set_job_link" SET updated_at = now() WHERE updated_at IS NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN updated_at SET NOT NULL;
ALTER TABLE "run_set_job_link" ALTER COLUMN updated_at SET DEFAULT now();
CREATE TRIGGER "run_set_job_link_update_created_at_updated_at"
  BEFORE UPDATE ON run_set_job_link
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();