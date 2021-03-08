ALTER TABLE "run_set_result" ADD COLUMN run_ids bigint[];
ALTER TABLE "run_set_result" ALTER COLUMN run_ids SET DEFAULT ARRAY[]::bigint[];
ALTER TABLE "run_set_result" ADD COLUMN passed_run_ids bigint[];
ALTER TABLE "run_set_result" ALTER COLUMN passed_run_ids SET DEFAULT ARRAY[]::bigint[];
ALTER TABLE "run_set_result" ADD COLUMN failed_run_ids bigint[];
ALTER TABLE "run_set_result" ALTER COLUMN failed_run_ids SET DEFAULT ARRAY[]::bigint[];
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_result_generate_columns" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'INSERT' THEN
      NEW.updated_at = now();
      NEW.created_at = now();
      NEW.end_at = NULL;
      NEW.is_finished = FALSE;
      NEW.start_at = NULL;
      NEW.total_run_number = 0;
      NEW.passed_run_number = 0;
      NEW.failed_run_number = 0;
      NEW.run_ids = ARRAY[]::bigint[];
      NEW.passed_run_ids = ARRAY[]::bigint[];
	  NEW.failed_run_ids =  ARRAY[]::bigint[];
      ----------------
      IF NEW.total_run_number IS NULL THEN
        NEW.total_run_number = 0;
      END IF;
      ----------------
      ----------------
      IF NEW.log IS NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' [NEW] Created.';
      ELSIF NEW.log IS NOT NULL THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' [NEW] Created with initial log:' || NEW.log;
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    
    --------------------------------------------------------------------------------------------------------
    IF TG_OP = 'UPDATE' THEN
      NEW.updated_at = now();
      NEW.created_at = OLD.created_at;
      NEW.uuid = OLD.uuid;
      NEW.total_run_number = case when array_length(NEW.run_ids, 1) is null then 0 else array_length(NEW.run_ids, 1) end;
      NEW.passed_run_number = case when array_length(NEW.passed_run_ids, 1) is null then 0 else array_length(NEW.passed_run_ids, 1) end;
      NEW.failed_run_number = case when array_length(NEW.failed_run_ids, 1) is null then 0 else array_length(NEW.failed_run_ids, 1) end;
      ----------------
      IF NEW.is_finished IS TRUE AND OLD.is_finished IS FALSE AND NEW.start_at IS NOT NULL THEN
        NEW.end_at = now();
      ELSIF OLD.is_finished IS TRUE THEN
        NEW.end_at = OLD.end_at;
        NEW.is_finished = OLD.is_finished;
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      IF OLD.status_id = 1 AND NEW.status_id <> 1 AND OLD.start_at IS NULL THEN
        NEW.start_at = now();
      ELSIF OLD.start_at IS NOT NULL THEN
        NEW.start_at = OLD.start_at;
      END IF;
      ----------------
      -- get the clean new log first
      IF NEW.log IS DISTINCT FROM OLD.log THEN
        NEW.log = replace(NEW.log, OLD.log, '');
        IF OLD.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = OLD.log || chr(10) || trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        ELSIF OLD.log IS NULL AND NEW.log IS NOT NULL AND NEW.log IS NOT NULL AND NEW.log <> '' THEN
          NEW.log = trim(both '"' from to_json(now())::text) || ' ' || NEW.log;
        END IF;
      END IF;
      ----------------
      IF OLD.start_at IS NULL AND OLD.end_at IS NULL AND OLD.is_finished IS FALSE AND NEW.is_finished IS TRUE THEN -- special case when the run set result start with failed condition
      	NEW.start_at = now();
      	NEW.end_at = now();
      	NEW.log = CASE 
        	        WHEN NEW.log IS NULL THEN 
        	          trim(both '"' from to_json(now())::text) || ' [SPECIAL] Special condition, the run set result directly entered into end status.'
        	        ELSE 
        	          NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [SPECIAL] Special condition, the run set result directly entered into end status.' 
        	      END;
      END IF;
      ---------------- [EXECUTION LOGIC] set the finished log and check if we need to set the status to PASS
      IF NEW.is_finished IS TRUE THEN        
        IF NEW.status_id = 2 AND OLD.status_id = 2 THEN
           NEW.status_id = 3;
           NEW.log = CASE 
        	           WHEN NEW.log IS NULL THEN 
        	             trim(both '"' from to_json(now())::text) || ' [PASS] this run set result set to PASS from WIP by finished signal.'
        	           ELSE 
        	             NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [PASS] this run set result set to PASS from WIP by finished signal.' 
        	         END;
        ELSIF NEW.status_id = 3 THEN
          NEW.log = CASE 
        	          WHEN NEW.log IS NULL THEN 
        	            trim(both '"' from to_json(now())::text) || ' [PASS] this run set result set to PASS.'
        	          ELSE 
        	            NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [PASS] this run set result set to PASS.' 
        	        END;
        END IF;
        NEW.log = NEW.log || chr(10) || trim(both '"' from to_json(now())::text) || ' [FINISHED] this run set result is finished.';
      END IF;
      ----------------
    END IF;
    --------------------------------------------------------------------------------------------------------
    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
UPDATE "run_set_result" SET
total_run_number = i.trn,
run_ids = i.rds
FROM 
(
    select run_set_result_id as rsri, 
    count(DISTINCT(id)) as trn, 
    case when array_agg(distinct(id)) is null then ARRAY[]::bigint[] else array_agg(distinct(id)) end as rds    
    FROM run group by run_set_result_id
) i
WHERE i.rsri = id;

UPDATE "run_set_result" SET
passed_run_number = i.tprn,
passed_run_ids = i.prds
FROM 
(
    select run_set_result_id as rsri, 
    count(DISTINCT(id)) as tprn, 
    case when array_agg(distinct(id)) is null then ARRAY[]::bigint[] else array_agg(distinct(id)) end as prds    
    FROM run where is_finished is true and status_id = 3 group by run_set_result_id
) i
WHERE i.rsri = id;

UPDATE "run_set_result" SET
failed_run_number = i.ftrn,
failed_run_ids = i.frds
FROM 
(
    select run_set_result_id as rsri, 
    count(DISTINCT(id)) as ftrn, 
    case when array_agg(distinct(id)) is null then ARRAY[]::bigint[] else array_agg(distinct(id)) end as frds    
    FROM run where is_finished is true and status_id <> 3 group by run_set_result_id
) i
WHERE i.rsri = id;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
    -- update run_execution_info, sync with the run
    IF NEW.status_id <> OLD.status_id OR 
       NEW.is_finished <> OLD.is_finished OR 
       NEW.result_overwritten <> OLD.result_overwritten OR
       NEW.start_at <> OLD.start_at OR
       NEw.end_at <> OLD.end_at THEN
            update run_execution_info set
            run_status_id = NEW.status_id,
            run_updated_at = NEW.updated_at,
            run_result_overwritten = NEW.result_overwritten,
            is_finished = NEW.is_finished,
            run_start_at = NEW.start_at,
            run_end_at = NEW.end_at
            where test_case_id = NEW.test_case_id and run_id = NEW.id;
    END IF;
    
    -- [EXECUTION LOGIC]
    IF NEW.run_set_result_id IS NOT NULL THEN
      -- update run set result status
      IF NEW.status_id IS NOT NULL AND NEW.status_id = 2 THEN -- update run set result from NEW to WIP
        UPDATE run_set_result SET 
        status_id = 2, 
        log = '[WIP] Update status to WIP by run :' || NEW.id || ' status : WIP'
        WHERE id = NEW.run_set_result_id AND status_id = 1 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 5 THEN -- update run set result to ERROR
        UPDATE run_set_result SET 
        status_id = 5, 
        log = '[ERROR] Update status to ERROR by run :' || NEW.id || ' status : ERROR'
        WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 4 THEN -- update run set result from to FAIL or just log the FAIL signal
        UPDATE run_set_result SET 
        log = '[ERROR_FAIL] Keep status as ERROR but got a FAIL run :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by run :' || NEW.id || ' status : FAIL'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 8 THEN -- update run set result from to FAIL by run TIMEOUT
        UPDATE run_set_result SET 
        log = '[ERROR_TIMEOUT] Keep status as ERROR but got a TIMEOUT run :' || NEW.id || ' status : TIMEOUT'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 4, 
        log = '[FAIL] Update status to FAIL by run :' || NEW.id || ' status : TIMEOUT'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ----==========
      ELSIF NEW.status_id IS NOT NULL AND NEW.status_id = 9 THEN -- update run set result from to FAIL by run TIMEOUT
        UPDATE run_set_result SET 
        log = '[ERROR_TERMINATED] Keep status as ERROR but got a TERMINATED run :' || NEW.id || ' status : TERMINATED'
        WHERE id = NEW.run_set_result_id AND status_id = 5 AND run_type_id = NEW.run_type_id;
        
        UPDATE run_set_result SET 
        status_id = 9, 
        log = '[TERMINATED] Update status to TERMINATED by run :' || NEW.id || ' status : TERMINATED'
        WHERE id = NEW.run_set_result_id AND status_id <> 5 AND run_type_id = NEW.run_type_id;
      ELSE
        UPDATE run_set_result SET 
        log = '[NO RESULT CHANGE] Received run update signal from run :' || NEW.id  || 
              ' new status : ' || (SELECT name from status WHERE id = NEW.status_id) || 
              ' old status : ' || (SELECT name from status WHERE id = OLD.status_id) ||
              ' finished : ' || NEW.is_finished
        WHERE id = NEW.run_set_result_id;
      END IF;
      ----==========
      
      -- update run set result to finished
      IF NEW.is_finished IS DISTINCT FROM OLD.is_finished AND NEW.is_finished IS TRUE THEN
        -- check if all run is finished
        IF (SELECT COUNT(id) FROM run WHERE is_finished IS FALSE AND run_set_result_id = NEW.run_set_result_id) = 0 THEN
          UPDATE run_set_result SET is_finished = TRUE WHERE id = NEW.run_set_result_id;
        END IF;
      END IF;
    END IF;

    RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	insert into run_execution_info (
	    run_id,
	    run_name,
	    run_type_id,
	    run_status_id,
	    run_created_at,
	    run_updated_at,
	    test_case_id,
	    test_case_md5,
	    run_set_result_id,
	    executable_instruction_number,
	    instruction_executed_count,
	    instruction_pass_count,
	    trigger_source,
	    driver_pack_md5,
	    test_case_overwrite_md5,
	    run_group_id,
	    driver_pack_name,
	    test_case_name,
	    test_case_overwrite_name,
	    run_priority,
	    run_result_overwritten,
	    run_project_id,
	    is_finished
	) values (  
	    NEW.id,
		NEW.name,
		NEW.run_type_id,
		NEW.status_id,
		NEW.created_at,
		NEW.updated_at,
		NEW.test_case_id,
		md5(NEW.test_case::text)::uuid,
		NEW.run_set_result_id,
		NEW.executable_instruction_number,
	    0,
		0,
		NEW.trigger_source,
		md5(NEW.driver_pack::text)::uuid,
		md5(NEW.test_case_overwrite::text)::uuid,
		NEW.group_id,
		NEW.driver_pack->>'name',
		NEW.test_case->>'name',
		NEW.test_case_overwrite->>'name',
		NEW.priority,
		NEW.result_overwritten,
		NEW.project_id,
		false
	);
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_insert_change_others" ()  RETURNS trigger
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
		latest_run_instruction_fail_count = 0,
		latest_run_trigger_source = NEW.trigger_source,
		latest_run_created_at = NEW.run_created_at,
		latest_run_id = NEW.run_id,
		latest_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
		
		-- project_execution_info
	    UPDATE project_execution_info SET 
	    executed_test_case_ids = ARRAY(SELECT DISTINCT UNNEST(executed_test_case_ids || NEW.test_case_id) ORDER BY 1) 
	    WHERE project_id = NEW.run_project_id;

	    -- project_report_info
	    UPDATE project_report_info SET 
	    total_run_number = total_run_number + 1 
	    WHERE project_id = NEW.run_project_id;
	ELSE
		update test_case_execution_info set
		total_dev_run_count = total_dev_run_count + 1,
		latest_dev_run_status_id = NEW.run_status_id,
		latest_dev_run_updated_at = NEW.run_updated_at,
		latest_dev_run_instruction_executed_count = 0,
		latest_dev_run_instruction_pass_count = 0,
		latest_dev_run_instruction_fail_count = 0,
		latest_dev_run_trigger_source = NEW.trigger_source,
		latest_dev_run_created_at = NEW.run_created_at,
		latest_dev_run_id = NEW.run_id,
		latest_dev_run_executable_instruction_number = NEW.executable_instruction_number
		where test_case_id = NEW.test_case_id;
	END IF;
	
    -- run set result 
    IF NEW.run_set_result_id IS NOT NULL THEN
      UPDATE run_set_result SET run_ids = array_append(run_ids, NEW.run_id)  WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
    END IF;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_execution_info_update_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.run_type_id = 1 AND 
	   (NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR
	    NEW.instruction_fail_count <> OLD.instruction_fail_count) THEN
	        ---------------------
			update test_case_execution_info set
			latest_run_status_id = NEW.run_status_id,
			latest_run_updated_at = NEW.run_updated_at,
			latest_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_run_instruction_pass_count = NEW.instruction_pass_count,
			latest_run_instruction_fail_count = NEW.instruction_fail_count
			where test_case_id = NEW.test_case_id and latest_run_id = NEW.run_id;
			
			-- project_execution_info
			IF NEW.run_status_id = 3 AND OLD.run_status_id = 2 THEN
	            UPDATE project_execution_info SET 
	            passed_test_case_ids = ARRAY(SELECT DISTINCT UNNEST(passed_test_case_ids || NEW.test_case_id) ORDER BY 1) 
	            WHERE project_id = NEW.run_project_id;
		    END IF;
	---------------------		
	ELSIF NEW.run_status_id <> OLD.run_status_id OR 
	    NEW.instruction_executed_count <> OLD.instruction_executed_count OR 
	    NEW.instruction_pass_count <> OLD.instruction_pass_count OR 
	    NEW.instruction_fail_count <> OLD.instruction_fail_count THEN
    ---------------------
		    update test_case_execution_info set
			latest_dev_run_status_id = NEW.run_status_id,
			latest_dev_run_updated_at = NEW.run_updated_at,
			latest_dev_run_instruction_executed_count = NEW.instruction_executed_count,
			latest_dev_run_instruction_pass_count = NEW.instruction_pass_count,
			latest_dev_run_instruction_fail_count = NEW.instruction_fail_count
			where test_case_id = NEW.test_case_id and latest_dev_run_id = NEW.run_id;
    ---------------------
	END IF;
	
	--update number for run set result
    IF NEW.run_set_result_id IS NOT NULL AND NEW.is_finished IS DISTINCT FROM OLD.is_finished AND NEW.is_finished IS TRUE THEN
      IF NEW.status_id = 3 THEN
	    UPDATE run_set_result SET 
	    passed_run_ids = ARRAY(SELECT DISTINCT UNNEST(passed_run_ids || NEW.id) ORDER BY 1) 
	    WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      ELSE
    	UPDATE run_set_result SET 
	    failed_run_ids = ARRAY(SELECT DISTINCT UNNEST(failed_run_ids || NEW.id) ORDER BY 1) 
	    WHERE id = NEW.run_set_result_id AND run_type_id = NEW.run_type_id;
      END IF;
    END IF;
    ----------------------------------
	
	-- update project chart info
	IF NEW.run_type_id = 1 THEN
	    IF NEW.is_finished IS DISTINCT FROM OLD.is_finished AND NEW.is_finished IS TRUE AND NEW.run_start_at IS NOT NULL AND NEW.run_end_at IS NOT NULL THEN
	        UPDATE "project_report_info" SET total_execution_time = total_execution_time + EXTRACT(EPOCH FROM (NEW.run_end_at - NEW.run_start_at)) WHERE project_id = NEW.run_project_id;
	    END IF;
	END IF;
	
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------