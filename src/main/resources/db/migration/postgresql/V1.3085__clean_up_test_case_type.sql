-- clean up test case
DELETE FROM test_case WHERE test_case_type_id NOT IN (SELECT id FROM test_case_task_type WHERE name = 'Mixed Test');

-- rename test case task type table
ALTER TABLE "test_case_task_type" RENAME TO test_case_type;
ALTER SEQUENCE test_case_task_type_id_seq RENAME TO test_case_type_id_seq;
ALTER TABLE "test_case" DROP CONSTRAINT "test_case_fk_type";

-- clean up the test case type table
DELETE FROM test_case_type WHERE id IS NOT NULL;
INSERT INTO test_case_type (id, name, is_predefined, is_active ) VALUES (1, 'JSON', true, true);
INSERT INTO test_case_type (id, name, is_predefined, is_active ) VALUES (2, 'JMeter', true, true);
INSERT INTO test_case_type (id, name, is_predefined, is_active ) VALUES (3, 'Android', true, true);
ALTER TABLE "test_case" ADD CONSTRAINT test_case_fk_type FOREIGN KEY ("test_case_type_id") REFERENCES "test_case_type" ("id");
