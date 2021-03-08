ALTER TABLE "run_execution_info" DROP COLUMN "test_case_sha2";
ALTER TABLE "run_execution_info" DROP COLUMN "driver_pack_sha2";
ALTER TABLE "run_execution_info" DROP COLUMN "test_case_overwrite_sha2";
ALTER TABLE "run_execution_info" ADD COLUMN driver_pack_md5 uuid;
ALTER TABLE "run_execution_info" ADD COLUMN test_case_overwrite_md5 uuid;
ALTER TABLE "run_execution_info" ADD COLUMN test_case_md5 uuid;
--------------------------------------------------------------------------------------------------------
UPDATE "run_execution_info" SET
driver_pack_md5 = i.dpmd, test_case_overwrite_md5 = i.tcomd, test_case_md5 = i.tcmd
FROM (select id, md5(driver_pack::text)::uuid as dpmd, md5(test_case_overwrite::text)::uuid as tcomd, md5(test_case::text)::uuid as tcmd FROM run ) i
WHERE i.id = run_id;
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
	    test_case_sha2,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    source_ip,
	    driver_pack_sha2,
	    test_case_overwrite_sha2
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
		md5(NEW.test_case_overwrite::text)::uuid
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------