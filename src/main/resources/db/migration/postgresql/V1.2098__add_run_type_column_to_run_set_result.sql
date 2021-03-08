ALTER TABLE "run_set_result" ADD COLUMN run_type_id bigint;
UPDATE "run_set_result" target SET run_type_id = (SELECT run_type_id FROM run WHERE run_set_result_id = target.id LIMIT 1) WHERE run_type_id IS NULL;
UPDATE "run_set_result" target SET run_type_id = 1 WHERE run_type_id IS NULL;
ALTER TABLE "run_set_result" ALTER COLUMN run_type_id SET NOT NULL;
ALTER TABLE "run_set_result" ALTER COLUMN run_type_id SET DEFAULT 1;
ALTER TABLE "run_set_result" ADD CONSTRAINT run_set_result_fk_run_type FOREIGN KEY (run_type_id) REFERENCES "run_type" ("id");