ALTER TABLE "run" disable TRIGGER "run_generate_columns";
UPDATE run_task_link target SET run_uuid = (SELECT uuid FROM run WHERE id = target.run_id) WHERE target.run_uuid IS NULL;
ALTER TABLE "run_task_link" ALTER COLUMN run_uuid SET NOT NULL;
ALTER TABLE "run" enable TRIGGER "run_generate_columns";