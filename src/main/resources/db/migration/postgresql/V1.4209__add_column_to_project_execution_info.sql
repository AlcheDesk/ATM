ALTER TABLE "project_execution_info" ADD COLUMN passed_test_case_ids bigint[];
ALTER TABLE "project_execution_info" ALTER COLUMN passed_test_case_ids SET DEFAULT ARRAY[]::bigint[];
ALTER TABLE "project_execution_info" ADD COLUMN passed_test_case_number INTEGER;
ALTER TABLE "project_execution_info" ALTER COLUMN passed_test_case_number SET DEFAULT 0;

UPDATE "project_execution_info" SET
passed_test_case_number = i.ttcn, passed_test_case_ids = i.tcis
FROM 
(
    select project_id as pi, 
    count(DISTINCT(test_case_id)) as ttcn, 
    case when array_agg(distinct(test_case_id)) is null then ARRAY[]::bigint[] else array_agg(distinct(test_case_id)) end as tcis 
    FROM run where status_id = 3 group by project_id
) i
WHERE i.pi = project_id;

UPDATE "project_execution_info" SET passed_test_case_number = 0, passed_test_case_ids = ARRAY[]::bigint[] WHERE passed_test_case_number is NULL;

ALTER TABLE "project_execution_info" ALTER COLUMN passed_test_case_ids SET NOT NULL;
ALTER TABLE "project_execution_info" ALTER COLUMN passed_test_case_number SET NOT NULL;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "public"."project_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into project_execution_info (
	    project_id,
	    project_name,
	    project_created_at,
	    project_updated_at,
	    total_test_case_number,
	    executed_test_case_number,
	    passed_test_case_number,
	    test_case_ids,
	    executed_test_case_ids,
	    passed_test_case_ids
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.updated_at,
	    0,
	    0,
	    0,
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[]
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;