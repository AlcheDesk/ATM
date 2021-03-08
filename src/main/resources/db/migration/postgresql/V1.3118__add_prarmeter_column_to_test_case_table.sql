ALTER TABLE "test_case" ADD COLUMN parameter json;

ALTER TABLE "test_case" ADD CONSTRAINT test_case_ck_test_case_type_parameter CHECK 
(
(test_case_type_id = 2 AND parameter IS NOT NULL)
OR 
(test_case_type_id <> 2 AND parameter IS NULL)
);