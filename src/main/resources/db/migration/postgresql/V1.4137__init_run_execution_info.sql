ALTER TABLE "run_execution_info" DROP COLUMN "total_instruction_result_count";
ALTER TABLE "run_execution_info" RENAME COLUMN "drivers_sha2" TO driver_pack_sha2;
ALTER TABLE "run_execution_info" DROP CONSTRAINT "run_execution_info_fk_test_case_id";
ALTER TABLE "run_execution_info" ADD CONSTRAINT run_execution_info_fk_test_case_id FOREIGN KEY ("test_case_id") REFERENCES "test_case" ("id");
--------------------------------------------------------------------------------------------------------
insert into run_execution_info (
        run_id,
        run_name,
        run_type_id,
        run_status_id,
        run_created_at,
        run_updated_at,
        test_case_id,
        test_case_sha2,
        run_set_result_id,
        executable_instruction_number,
        instruction_executed_count,
        instruction_pass_count,
        source_ip,
        driver_pack_sha2,
        test_case_overwrite_sha2
)
 select   
    r.id,
	r.name,
	r.run_type_id,
	r.status_id,
	r.created_at,
	r.updated_at,
	r.test_case_id,
	digest(r.test_case::text, 'sha256'),
	r.run_set_result_id,
	case when r.executable_instruction_number is null then 0 else r.executable_instruction_number end,
	count(dir.id) + count(pir.id) as instruction_executed_count,
	count(case dir.status_id when 3 then 1 else null end) + count(case pir.status_id when 3 then 1 else null end),
	r.trigger_source,
	digest(r.driver_pack::text, 'sha256'),
	digest(r.test_case_overwrite::text, 'sha256')
    from run r
    left join dev_instruction_result dir on r.id = dir.run_id
    left join prod_instruction_result pir on r.id = pir.run_id
    group by r.id;
--------------------------------------------------------------------------------------------------------
DROP TRIGGER "run_after_insert_tcei" ON "run";    
DROP FUNCTION "run_insert_update_test_case_execution_info" ( );
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_insert_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 THEN
		update test_case_execution_info set
		total_run_count = total_run_count + 1,
		latest_run_status_id = NEW.run_status_id,
		latest_run_updated_at = NEW.run_updated_at,
		latest_run_instruction_executed_count = 0,
		latest_run_instruction_pass_count = 0,
		latest_run_source_ip = NEW.source_ip,
		latest_run_created_at = NEW.run_created_at,
		latest_run_id = NEW.run_id,
		latest_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	ELSE
		update test_case_execution_info set
		total_dev_run_count = total_dev_run_count + 1,
		latest_dev_run_status_id = NEW.run_status_id,
		latest_dev_run_updated_at = NEW.run_updated_at,
		latest_dev_run_instruction_executed_count = 0,
		latest_dev_run_instruction_pass_count = 0,
		latest_dev_run_source_ip = NEW.source_ip,
		latest_dev_run_created_at = NEW.run_created_at,
		latest_dev_run_id = NEW.run_id,
		latest_dev_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "run_execution_info_after_insert_update_tcei"
  AFTER INSERT ON run_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE run_execution_info_insert_update_test_case_execution_info();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_insert_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_sha2,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    source_ip,
	    driver_pack_sha2,
	    test_case_overwrite_sha2
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		digest(NEW.test_case::text, 'sha256'),
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    null,
		null,
		NEW.trigger_source,
		digest(NEW.driver_pack::text, 'sha256'),
		digest(NEW.test_case_overwrite::text, 'sha256')
	);
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER "run_after_insert_insert_run_execution_info"
  AFTER INSERT ON run
  FOR EACH ROW
EXECUTE PROCEDURE run_insert_insert_run_execution_info();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 AND 
	   (NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count) THEN
			update test_case_execution_info set
			latest_run_status_id = NEW.run_status_id,
			latest_run_updated_at = NEW.run_updated_at,
			latest_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_run_instruction_pass_count = NEW.instruction_pass_count
			where test_case_id = NEW.test_case_id and latest_run_id = NEW.run_id;
	ELSIF NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count THEN
		    update test_case_execution_info set
			latest_dev_run_status_id = NEW.run_status_id,
			latest_dev_run_updated_at = NEW.run_updated_at,
			latest_dev_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_dev_run_instruction_pass_count = NEW.instruction_pass_count
			where test_case_id = NEW.test_case_id and latest_dev_run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "run_execution_info_after_update_update_test_case_execution_info"
  AFTER UPDATE ON run_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE run_execution_info_update_test_case_execution_info();
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_status_id <> OLD.run_status_id OR NEW.instruction_executed_count <> OLD.instruction_executed_count OR NEW.instruction_pass_count <> OLD.instruction_pass_count THEN
			update run_execution_info set
			run_status_id = NEW.run_status_id,
			run_updated_at = NEW.run_updated_at,
			instruction_executed_count = NEW.instruction_executed_count,
			instruction_pass_count = NEW.instruction_pass_count
			where test_case_id = NEW.test_case_id and run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "run_update_update_test_case_execution_info" ( ) RENAME TO run_execution_info_update_update_tcei;
DROP TRIGGER "run_after_update_tcei" ON "run";
CREATE TRIGGER "run_execution_info_after_update_update_rei"
  AFTER UPDATE ON run_execution_info
  FOR EACH ROW
EXECUTE PROCEDURE run_execution_info_update_update_tcei();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_update_run_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id <> OLD.status_id THEN
			update run_execution_info set
			run_status_id = NEW.status_id,
			run_updated_at = NEW.updated_at
			where test_case_id = NEW.test_case_id and run_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "run_after_update_update_run_execution_info"
  AFTER UPDATE ON run
  FOR EACH ROW
EXECUTE PROCEDURE run_update_update_run_execution_info();
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_ir_insert_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	update run_execution_info set
	instruction_executed_count = instruction_executed_count + 1
	where run_id = NEW.run_id;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "dev_ir_insert_update_test_case_execution_info" ( ) RENAME TO dev_ir_insert_update_run_execution_info;
ALTER TRIGGER "dev_instruction_result_after_insert_tcei" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_insert_rei;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_ir_insert_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	update run_execution_info set
	instruction_executed_count = instruction_executed_count + 1
	where run_id = NEW.run_id;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "prod_ir_insert_update_test_case_execution_info" ( ) RENAME TO prod_ir_insert_update_run_execution_info;
--------------------------------------------------------------------------------------------------------
ALTER TRIGGER "prod_instruction_result_after_insert_tcei" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_insert_rei;
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "dev_ir_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update run_execution_info set
		instruction_pass_count = instruction_pass_count + 1
		where run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "dev_ir_update_update_test_case_execution_info" ( ) RENAME TO dev_ir_update_update_run_execution_info;
--------------------------------------------------------------------------------------------------------
ALTER TRIGGER "dev_instruction_result_after_update_tcei" ON "dev_instruction_result" RENAME TO dev_instruction_result_after_update_rei;
--------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "prod_ir_update_update_test_case_execution_info" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 3 AND NEW.status_id <> OLD.status_id THEN
		update run_execution_info set
		instruction_pass_count = instruction_pass_count + 1
		where run_id = NEW.run_id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
ALTER FUNCTION "prod_ir_update_update_test_case_execution_info" ( ) RENAME TO prod_ir_update_update_run_execution_info;
--------------------------------------------------------------------------------------------------------
ALTER TRIGGER "prod_instruction_result_after_update_tcei" ON "prod_instruction_result" RENAME TO prod_instruction_result_after_update_rei;
--------------------------------------------------------------------------------------------------------