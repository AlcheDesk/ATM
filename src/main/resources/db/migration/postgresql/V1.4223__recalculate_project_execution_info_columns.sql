UPDATE "project_execution_info" SET
total_test_case_number = i.ttcn, test_case_ids = i.tcis
FROM 
(
    select project_id as pi, 
    count(DISTINCT(id)) as ttcn, 
    case when array_agg(distinct(id)) is null then ARRAY[]::bigint[] else array_agg(distinct(id)) end as tcis 
    FROM test_case where is_deleted is false group by project_id
) i
WHERE i.pi = project_id;
----------------------------------------------------------------------------------------------------------------------------------
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