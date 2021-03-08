ALTER TABLE "run_execution_info" ADD COLUMN run_group_id bigint;
ALTER TABLE "run_execution_info" RENAME COLUMN "source_ip" TO trigger_source;
ALTER TABLE "run_execution_info" DROP CONSTRAINT "run_execution_info_fk_run_set_result_id";
ALTER TABLE "run_execution_info" DROP CONSTRAINT "run_execution_info_fk_test_case_id";
ALTER TABLE "run" ADD CONSTRAINT run_ix_group UNIQUE ("id", "group_id");
ALTER TABLE "run" ADD CONSTRAINT run_ix_test_case UNIQUE ("id", "test_case_id");
ALTER TABLE "run" ADD CONSTRAINT run_ix_run_set_result UNIQUE ("id", "run_set_result_id");
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_group_id FOREIGN KEY ("run_id", run_group_id) REFERENCES "run" ("id", "group_id");
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_test_case_id FOREIGN KEY ("run_id", "test_case_id") REFERENCES "run" ("id", "test_case_id");
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_run_set_result_id FOREIGN KEY ("run_id", "run_set_result_id") REFERENCES "run" ("id", "run_set_result_id");
--------------------------------------------------------------------------------------------------------
UPDATE "run_execution_info" SET
run_group_id = i.gid
FROM (select id, group_id as gid FROM run ) i
WHERE i.id = run_id;
ALTER TABLE "run_execution_info" ALTER COLUMN run_group_id SET NOT NULL;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_insert_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $$
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
	    run_group_id
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
		NEW.group_id
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;