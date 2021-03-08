----------------------------------------------------------------------------------------------------------------------------------
UPDATE "project_report_info" SET
passed_test_case_number = i.ptcn
FROM 
(
    select project_id as pi, 
    case when array_length(passed_test_case_ids, 1) is null then 0 else array_length(passed_test_case_ids, 1) end as ptcn
    FROM project_execution_info
) i
WHERE i.pi = project_id;
----------------------------------------------------------------------------------------------------------------------------------