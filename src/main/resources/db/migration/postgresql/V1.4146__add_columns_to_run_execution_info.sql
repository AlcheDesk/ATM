ALTER TABLE "run_execution_info" ADD COLUMN run_priority bigint;
--------------------------------------------------------------------------------------------------------
UPDATE "run_execution_info" SET
run_priority = i.priority
FROM (select id, priority FROM run ) i
WHERE i.id = run_id;
--------------------------------------------------------------------------------------------------------
ALTER TABLE "run_execution_info" ALTER COLUMN run_priority SET NOT NULL;
ALTER TABLE "run" ADD CONSTRAINT run_ix_priority UNIQUE ("id", "priority");
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_priority FOREIGN KEY ("run_id", run_priority) REFERENCES "run" ("id", "priority");
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_insert_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_md5,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    trigger_source,
	    driver_pack_md5,
	    test_case_overwrite_md5,
	    run_group_id,
	    driver_pack_name,
	    test_case_overwrite_name,
	    test_case_name,
	    run_priority
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		md5(NEW.test_case::text)::uuid,
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    0,
		0,
		NEW.trigger_source,
		md5(NEW.driver_pack::text)::uuid,
		md5(NEW.test_case_overwrite::text)::uuid,
		NEW.group_id,
		NEW.driver_pack->'name',
		NEW.test_case->'name',
		NEW.test_case_overwrite->'name',
		NEW.priority
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;