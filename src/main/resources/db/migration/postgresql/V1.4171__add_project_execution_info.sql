CREATE TABLE project_execution_info
    (
        project_id bigint NOT NULL,
        project_name text NOT NULL,
        project_created_at TIMESTAMP WITH TIME zone NOT NULL,
        project_updated_at TIMESTAMP WITH TIME zone NOT NULL,
        total_test_case_number INTEGER DEFAULT 0 NOT NULL,
        executed_test_case_number INTEGER DEFAULT 0 NOT NULL,
        test_case_ids INTEGER[] DEFAULT array[]::INT[] NOT NULL,
        executed_test_case_ids INTEGER[] DEFAULT array[]::INT[] NOT NULL,
        PRIMARY KEY (project_id),
        CONSTRAINT project_execution_info_fk_project FOREIGN KEY (project_id, project_name) REFERENCES "project" ("id", "name"),
        CONSTRAINT project_execution_info_ck_test_case_numbers CHECK (total_test_case_number >= executed_test_case_number),
        CONSTRAINT project_execution_info_ck_test_case_ids CHECK (test_case_ids @> executed_test_case_ids)
    );
--------------------------------------------------------------------------------------------------------
INSERT INTO "project_execution_info" (project_id, project_name, project_created_at, project_updated_at)
select id as pi, name as pn, created_at as pc, updated_at as pu FROM project;

UPDATE "project_execution_info" SET
total_test_case_number = i.ttcn, test_case_ids = i.tcis
FROM (select project_id as pi, count(DISTINCT(id)) as ttcn, array_agg(distinct(id)) as tcis FROM test_case group by project_id) i
WHERE i.pi = project_id;

UPDATE "project_execution_info" SET
executed_test_case_number = i.etcn, executed_test_case_ids = i.etci
FROM (select project_id as pi, count(DISTINCT(test_case_id)) as etcn, array_agg(distinct(test_case_id)) as etci FROM run group by project_id) i
WHERE i.pi = project_id;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into project_execution_info (
	    test_case_id,
	    test_case_name,
	    test_case_created_at,
	    tenant_id
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.tenant_id
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
--------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_after_insert_change_others"
  AFTER INSERT ON project
  FOR EACH ROW
EXECUTE PROCEDURE project_insert_change_others();
--------------------------------------------------------------------------------------------------------