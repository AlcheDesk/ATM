ALTER TABLE "project_execution_info" DROP CONSTRAINT "project_execution_info_ck_test_case_numbers";
ALTER TABLE "project_execution_info" ADD COLUMN total_test_case_number INTEGER DEFAULT 0 NOT NULL;
ALTER TABLE "project_execution_info" ADD COLUMN test_case_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL;
--ALTER TABLE "project_execution_info" ALTER COLUMN total_test_case_number SET DEFAULT 0;

UPDATE "project_execution_info" SET
total_test_case_number = i.ttcn, test_case_ids = i.tcis
FROM (select project_id as pi, count(DISTINCT(id)) as ttcn, array_agg(distinct(id)) as tcis FROM test_case group by project_id) i
WHERE i.pi = project_id;

ALTER TABLE "project_report_info" ADD COLUMN total_test_case_number INTEGER DEFAULT 0 NOT NULL;
--ALTER TABLE "project_report_info" ALTER COLUMN total_test_case_number SET DEFAULT 0;

UPDATE "project_report_info" SET
total_test_case_number = i.ttcn
FROM (select project_id as pi, count(DISTINCT(id)) as ttcn FROM test_case group by project_id) i
WHERE i.pi = project_id;

--ALTER TABLE "project_report_info" ALTER COLUMN total_test_case_number SET NOT NULL;
--ALTER TABLE "project_execution_info" ALTER COLUMN total_test_case_number SET NOT NULL;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF TG_OP = 'INSERT' THEN
	    IF NEW.project_id IS NOT NULL THEN
	      NEW.project_name = (SELECT name FROM project WHERE id = NEW.project_id);
	    END IF;
	    NEW.total_test_case_number = 0;
		NEW.active_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.test_case_ids = ARRAY[]::bigint[];
		NEW.active_test_case_ids = ARRAY[]::bigint[];
		NEW.executed_test_case_ids =  ARRAY[]::bigint[];
	END IF;
	
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEW.active_test_case_number = case when array_length(NEW.active_test_case_ids, 1) is null then 0 else array_length(NEW.active_test_case_ids, 1) end;
		NEW.total_test_case_number = case when array_length(NEW.test_case_ids, 1) is null then 0 else array_length(NEW.test_case_ids, 1) end;
		NEW.executed_test_case_number = case when array_length(NEW.executed_test_case_ids, 1) is null then 0 else array_length(NEW.executed_test_case_ids, 1) end;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	INSERT INTO project_report_info (
		project_id, 
		project_name, 
		project_created_at, 
		total_test_case_number, 
		active_test_case_number, 
		total_run_number, 
		total_execution_time, 
		executed_test_case_number, 
		failed_test_case_number, 
		passed_test_case_number, 
		pass_rate, 
		fail_rate
	) VALUES (
		NEW.project_id,
	    NEW.project_name,
	    NEW.project_created_at,
	    0,
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0, 
	    0
	);

	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.project_name IS NOT NULL AND NEW.project_name <> OLD.project_name THEN
		UPDATE project_report_info SET project_name = NEW.project_name WHERE project_id = NEW.project_id;
	ELSIF TG_OP = 'UPDATE' THEN
	    UPDATE project_report_info SET 
	    project_name = NEW.project_name,
	    total_test_case_number = case when array_length(NEW.test_case_ids, 1) is null then 0 else array_length(NEW.test_case_ids, 1) end,
	    executed_test_case_number = case when array_length(NEW.executed_test_case_ids, 1) is null then 0 else array_length(NEW.executed_test_case_ids, 1) end,
	    active_test_case_number = case when array_length(NEW.active_test_case_ids, 1) is null then 0 else array_length(NEW.active_test_case_ids, 1) end,
	    passed_test_case_number = case when array_length(NEW.passed_test_case_ids, 1) is null then 0 else array_length(NEW.passed_test_case_ids, 1) end
	    WHERE project_id = NEW.project_id;		
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into project_execution_info (
	    project_id,
	    project_name,
	    project_created_at,
	    project_updated_at,
	    total_test_case_number,
	    active_test_case_number,
	    executed_test_case_number,
	    passed_test_case_number,
	    active_test_case_ids,
	    executed_test_case_ids,
	    passed_test_case_ids
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at,
	    NEW.updated_at,
	    0,
	    0,
	    0,
	    0,
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[],
	    ARRAY[]::bigint[]
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "project_report_info_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	-- is_finished and end_at
	IF TG_OP = 'UPDATE' THEN
		NEW.project_created_at = OLD.project_created_at;
		NEW.failed_test_case_number = NEW.active_test_case_number - NEW.passed_test_case_number;
		NEW.pass_rate = case when NEW.executed_test_case_number <> 0 then CAST(NEW.passed_test_case_number AS numeric)/CAST(NEW.executed_test_case_number AS numeric) else 100.00 end;
		NEW.fail_rate = case when NEW.executed_test_case_number <> 0 then CAST(NEW.failed_test_case_number AS numeric)/CAST(NEW.executed_test_case_number AS numeric) else 100.00 end;
	END IF;
	
	IF TG_OP = 'INSERT' THEN
	    NEW.total_test_case_number = 0;
		NEW.active_test_case_number = 0;
		NEW.total_run_number = 0;
		NEW.total_execution_time = 0;
		NEW.failed_test_case_number = 0;
		NEW.executed_test_case_number = 0;
		NEW.passed_test_case_number = 0;
		NEW.pass_rate = 100.00;
		NEW.fail_rate = 100.00;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_execution_info_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- project_execution_info
	UPDATE project_execution_info SET 
	active_test_case_number = active_test_case_number + 1, 
	total_test_case_number = total_test_case_number + 1, 
	active_test_case_ids = array_append(active_test_case_ids, NEW.test_case_id), 
	test_case_ids = array_append(test_case_ids, NEW.test_case_id) 
	WHERE project_id = NEW.test_case_project_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN	
	IF NEW.test_case_is_deleted IS TRUE AND OLD.test_case_is_deleted IS FALSE THEN
		UPDATE project_execution_info SET 
		active_test_case_number = active_test_case_number - 1, 
		active_test_case_ids = array_remove(active_test_case_ids, NEW.test_case_id),
		executed_test_case_ids = array_remove(executed_test_case_ids, NEW.test_case_id),
		passed_test_case_ids = array_remove(passed_test_case_ids, NEW.test_case_id)
		WHERE project_id = NEW.test_case_project_id;
	END IF;
	
	IF NEW.test_case_is_deleted IS FALSE AND OLD.test_case_is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET 
	    active_test_case_number = active_test_case_number + 1, 
	    active_test_case_ids = array_append(active_test_case_ids, NEW.test_case_id) 
	    WHERE project_id = NEW.test_case_project_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------