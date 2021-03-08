CREATE TABLE project_report_info
    (
        project_id bigint NOT NULL,
        project_name text NOT NULL,
        project_created_at TIMESTAMP WITH TIME zone NOT NULL,
        total_test_case_number INTEGER DEFAULT 0 NOT NULL,
        total_run_number INTEGER DEFAULT 0 NOT NULL,
        totalExecutionTime bigint DEFAULT 0 NOT NULL,
        executedTestCaseNumber INTEGER DEFAULT 0 NOT NULL,
        failedTestCaseNumber INTEGER DEFAULT 0 NOT NULL,
        passedTestCaseNumber INTEGER DEFAULT 0 NOT NULL,
        passRate DECIMAL(5,2) DEFAULT 0 NOT NULL,
        failRate DECIMAL(5,2) DEFAULT 0 NOT NULL,
        PRIMARY KEY (project_id),
        CONSTRAINT project_report_info_fk_project FOREIGN KEY (project_id, project_name) REFERENCES "project" ("id", "name"),
        CONSTRAINT project_report_info_ix_project UNIQUE (project_id)
    );
----------------------------------------------------------------------------------------------------------------------------------    
CREATE OR REPLACE FUNCTION "project_report_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' THEN
	
	END IF;
	
	IF TG_OP = 'INSERT' THEN
		NEW.total_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.totalExecutionTime = 0;
		NEW.failedTestCaseNumber = 0;
		NEW.executedTestCaseNumber = 0;
		NEW.passedTestCaseNumber = 0;
		NEW.passRate = 0;
		NEW.failRate = 0;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE TRIGGER "project_report_info_generate_columns"
  BEFORE INSERT OR UPDATE ON project_report_info
  FOR EACH ROW
EXECUTE PROCEDURE project_report_info_generate_columns();