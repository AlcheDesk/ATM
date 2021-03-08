ALTER TABLE "project_report_info" ADD COLUMN tenant_id bigint;
UPDATE "project_report_info" SET tenant_id = (SELECT tenant_id FROM project WHERE id = project_report_info.project_id) WHERE tenant_id IS NULL;
ALTER TABLE "project_report_info" ALTER COLUMN tenant_id SET NOT NULL;
ALTER TABLE "project_report_info" ALTER COLUMN tenant_id SET DEFAULT 1000;
UPDATE "project_execution_info" SET tenant_id = (SELECT tenant_id FROM project WHERE id = project_execution_info.project_id);
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into project_execution_info (
	    project_id,
	    project_name,
	    project_created_at,
	    project_updated_at,
	    total_test_case_number,
	    active_test_case_number,
	    executed_test_case_number,
	    passed_test_case_number,
	    active_test_case_ids,
	    executed_test_case_ids,
	    passed_test_case_ids,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.updated_at,
	    0,
	    0,
	    0,
	    0,
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[],
	    NEW.tenant_id
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	INSERT INTO project_report_info (
		project_id, 
		project_name, 
		project_created_at, 
		total_test_case_number, 
		active_test_case_number, 
		total_run_number, 
		total_execution_time, 
		executed_test_case_number, 
		failed_test_case_number, 
		passed_test_case_number, 
		pass_rate, 
		fail_rate,
		tenant_id
	) VALUES (
		NEW.project_id,
	    NEW.project_name,
	    NEW.project_created_at,
	    0,
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0,
	    NEW.tenant_id
	);

	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------