ALTER TABLE "run_execution_info" ADD COLUMN run_start_at TIMESTAMP WITH TIME zone;
ALTER TABLE "run_execution_info" ADD COLUMN run_end_at TIMESTAMP WITH TIME zone;
ALTER TABLE "run_execution_info" ADD COLUMN duration bigint;
ALTER TABLE "run_execution_info" ALTER COLUMN duration SET DEFAULT 0;