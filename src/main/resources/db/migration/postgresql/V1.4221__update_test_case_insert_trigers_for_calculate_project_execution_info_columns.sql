UPDATE "project_execution_info" SET
passed_test_case_number = i.ttcn, passed_test_case_ids = i.tcis
FROM 
(
    select project_id as pi, 
    count(DISTINCT(test_case_id)) as ttcn, 
    case when array_agg(distinct(test_case_id)) is null then ARRAY[]::bigint[] else array_agg(distinct(test_case_id)) end as tcis 
    FROM run where status_id = 3 and test_case_id not in (select id from test_case where is_deleted is true) group by project_id
) i
WHERE i.pi = project_id;
----------------------------------------------------------------------------------------------------------------------------------
UPDATE "project_execution_info" SET
executed_test_case_number = i.etcn, executed_test_case_ids = i.etci
FROM 
(
      select project_id as pi, 
      count(DISTINCT(test_case_id)) as etcn, 
      array_agg(distinct(test_case_id)) as etci 
      FROM run where test_case_id not in (select id from test_case where is_deleted is true) group by project_id
) i
WHERE i.pi = project_id;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	IF NEW.test_case_is_deleted IS TRUE AND OLD.test_case_is_deleted IS FALSE THEN
		UPDATE project_execution_info SET 
		total_test_case_number = total_test_case_number - 1, 
		test_case_ids = array_remove(test_case_ids, NEW.test_case_id),
		executed_test_case_ids = array_remove(executed_test_case_ids, NEW.test_case_id),
		passed_test_case_ids = array_remove(passed_test_case_ids, NEW.test_case_id)
		WHERE project_id = NEW.project_id;
	END IF;
	
	IF NEW.test_case_is_deleted IS FALSE AND OLD.test_case_is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET 
	    total_test_case_number = total_test_case_number + 1, 
	    test_case_ids = array_append(test_case_ids, NEW.test_case_id) 
	    WHERE project_id = NEW.project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;