ALTER TABLE "test_case_execution_info" ALTER COLUMN "latest_run_source_ip" TYPE text;
ALTER TABLE "test_case_execution_info" ALTER COLUMN total_run_count SET DEFAULT 0;
ALTER TABLE "test_case_execution_info" ALTER COLUMN latest_run_status_id SET DEFAULT 7;

insert into test_case_execution_info (
	test_case_id,
	test_case_name,
	test_case_created_at,
	total_run_count,
	latest_run_status_id,
	latest_run_updated_at,
	latest_run_instruction_total_count,
	latest_run_instruction_pass_count,
	latest_run_source_ip,
	latest_run_created_at,
	latest_run_id
)
select 
	r.test_case_id,
	(select name from test_case where id = r.test_case_id) as test_case_name, 
	(select created_at from test_case where id = r.test_case_id) as test_case_created_at, 
	count(r.id) as total_run_count, 
	(select status_id from run where id = max(r.id)) as latest_run_status_id, 
	(select updated_at from run where id = max(r.id)) as latest_run_updated_at, 
	((select count(id) from dev_instruction_result where run_id = max(r.id)) + (select count(id) from prod_instruction_result where run_id = max(r.id))) as latest_run_instruction_total_count,
	((select count(id) from dev_instruction_result where run_id = max(r.id) and status_id = 3 ) + (select count(id) from prod_instruction_result where run_id = max(r.id) and status_id = 3 )) as latest_run_instruction_pass_count,
	(select trigger_source from run where id = max(r.id)) as latest_run_source_ip,
	(select created_at from run where id = max(r.id)) as latest_run_created_at,
	max(r.id) as latest_run_id
    from run r
    group by test_case_id;

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_test_case_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	insert into test_case_execution_info (
	    test_case_id,
	    test_case_name,
	    test_case_created_at
	) values (
	    NEW.id,
	    NEW.name,
	    NEW.created_at
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_after_insert_test_case_execution_info"
  AFTER INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_test_case_insert();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_run_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	total_run_count = total_run_count + 1,
	latest_run_status_id = NEW.status_id,
	latest_run_updated_at = NEW.updated_at,
	latest_run_instruction_total_count = 0,
	latest_run_instruction_pass_count = 0,
	latest_run_source_ip = NEW.trigger_source,
	latest_run_created_at = NEW.created_at,
	latest_run_id = NEW.id
	where test_case_id = NEW.test_case_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_after_insert_test_case_execution_info"
  AFTER INSERT ON run
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_run_insert();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_run_instruction_total_count = latest_run_instruction_total_count + 1
	where test_case_id = NEW.test_case_id AND latest_run_id = NEW.run_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_instruction_result_after_insert_test_case_execution_info"
  AFTER INSERT ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_dev_instruction_result_insert();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_prod_instruction_result_insert" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	update test_case_execution_info set
	latest_run_instruction_total_count = latest_run_instruction_total_count + 1
	where test_case_id = NEW.test_case_id AND latest_run_id = NEW.run_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "prod_instruction_result_after_insert_test_case_execution_info"
  AFTER INSERT ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_prod_instruction_result_insert();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_dev_instruction_result_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.status_id = 3 THEN
		update test_case_execution_info set
		latest_run_instruction_pass_count = latest_run_instruction_pass_count + 1
		where test_case_id = NEW.test_case_id AND latest_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "dev_instruction_result_after_update_test_case_execution_info"
  AFTER UPDATE ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_dev_instruction_result_update();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_prod_instruction_result_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.status_id = 3 THEN
		update test_case_execution_info set
		latest_run_instruction_pass_count = latest_run_instruction_pass_count + 1
		where test_case_id = NEW.test_case_id AND latest_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "prod_instruction_result_after_update_test_case_execution_info"
  AFTER UPDATE ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_prod_instruction_result_update();

--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "update_test_case_execution_info_after_run_update" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	update test_case_execution_info set
	latest_run_status_id = NEW.status_id,
	latest_run_updated_at = NEW.updated_at
	where test_case_id = NEW.test_case_id and latest_run_id = NEW.id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_after_update_test_case_execution_info"
  AFTER UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE update_test_case_execution_info_after_run_update();

