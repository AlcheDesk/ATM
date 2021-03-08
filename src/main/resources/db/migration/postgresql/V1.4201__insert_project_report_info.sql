INSERT INTO project_report_info (
project_id, 
project_name, 
project_created_at
) 
SELECT 
project_id, 
project_name, 
project_created_at
FROM 
project_execution_info;
----------------------------------------------------------------------------------------------------------------------------------
UPDATE project_report_info SET total_test_case_number = i.ttcn,  executed_test_case_number = i.etcn FROM (
SELECT 
project_id as pi,
CASE when array_length(test_case_ids, 1) IS NULL THEN 0 ELSE array_length(test_case_ids, 1) END  as ttcn,
CASE when array_length(executed_test_case_ids, 1)  IS NULL THEN 0 ELSE array_length(executed_test_case_ids, 1) END as etcn
FROM 
project_execution_info) i
WHERE project_id = i.pi;