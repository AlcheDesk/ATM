UPDATE "test_case_overwrite"
SET   name = CONCAT(name, CAST(id AS varchar))
WHERE id IN 
(
SELECT a.id FROM test_case_overwrite a JOIN test_case_overwrite b ON a.id > b.id AND a.name = b.name AND a.test_case_id = b.test_case_id GROUP BY a.name, a.id
);

CREATE UNIQUE INDEX test_case_overwrite_unique_index_name_test_case_id_is_deleted
    ON test_case_overwrite (test_case_id, name) WHERE is_deleted IS FALSE;