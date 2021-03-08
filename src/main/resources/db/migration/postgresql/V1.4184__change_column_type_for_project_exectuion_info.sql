ALTER TABLE "project_execution_info" DROP COLUMN "test_case_ids";
ALTER TABLE "project_execution_info" DROP COLUMN "executed_test_case_ids";
ALTER TABLE "project_execution_info" ADD COLUMN test_case_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL;
ALTER TABLE "project_execution_info" ADD COLUMN executed_test_case_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL;

UPDATE "project_execution_info" SET
total_test_case_number = i.ttcn, test_case_ids = i.tcis
FROM (select project_id as pi, count(DISTINCT(id)) as ttcn, array_agg(distinct(id)) as tcis FROM test_case group by project_id) i
WHERE i.pi = project_id;

UPDATE "project_execution_info" SET
executed_test_case_number = i.etcn, executed_test_case_ids = i.etci
FROM (select project_id as pi, count(DISTINCT(test_case_id)) as etcn, array_agg(distinct(test_case_id)) as etci FROM run group by project_id) i
WHERE i.pi = project_id;