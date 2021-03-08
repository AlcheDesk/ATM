-- removed default 
ALTER TABLE "run" ALTER COLUMN "start_at" DROP DEFAULT;
ALTER TABLE "run" ALTER COLUMN "end_at" DROP DEFAULT;
ALTER TABLE "dev_instruction_result" ALTER COLUMN "start_at" DROP DEFAULT;
ALTER TABLE "dev_instruction_result" ALTER COLUMN "end_at" DROP DEFAULT;
ALTER TABLE "prod_instruction_result" ALTER COLUMN "start_at" DROP DEFAULT;
ALTER TABLE "prod_instruction_result" ALTER COLUMN "end_at" DROP DEFAULT;

-- update run triggers
CREATE OR REPLACE FUNCTION dev_instruction_result_update_run_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	new_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	done_status_id bigint;
	dev_instruction_result_new_status text;
	target_run_id bigint; --the run linked the the instruction result
	run_status_id bigint; -- the run current status id
    new_run_status_id bigint;
    run_status_name text;
    new_run_status_name text;
BEGIN
	--load the status ids into variables
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
	select id into pass_status_id from status where name = 'PASS';
	select id into fail_status_id from status where name = 'FAIL';
	select id into error_status_id from status where name = 'ERROR';
	
    select name into dev_instruction_result_new_status from status where status.id = NEW.status_id;
	
	--get the run id form the instrcutio id 
	select run_id into target_run_id from dev_instruction_result where dev_instruction_result.id = NEW.id;
	
	--get the run status id form the run id
	select status_id into run_status_id from run where run.id = target_run_id;
    select name into run_status_name from status where status.id = run_status_id;

	--check the current run status and the new instrcution result status
    IF run_status_id = new_status_id and new.status_id = new_status_id THEN -- 
		update run set status_id = wip_status_id, start_at = now() where id = target_run_id;
		
	ELSIF run_status_id = new_status_id and new.status_id = error_status_id THEN -- 
		update run set status_id = error_status_id, start_at = now() where id = target_run_id;
		
	ELSIF  run_status_id = new_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id, start_at = now()  where id = target_run_id;
		
	ELSIF run_status_id = new_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id, start_at = now() where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = pass_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = error_status_id THEN
		update run set status_id = error_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
		
	ELSIF run_status_id = fail_status_id and new.status_id = error_status_id THEN
		update run set status_id = error_status_id where id = target_run_id;
		
	END IF;
	RETURN NEW;	
END;
$$;

CREATE OR REPLACE FUNCTION prod_instruction_result_update_run_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	new_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	done_status_id bigint;
	prod_instruction_result_new_status text;
	target_run_id bigint; --the run linked the the instruction result
	run_status_id bigint; -- the run current status id
    new_run_status_id bigint;
    run_status_name text;
    new_run_status_name text;
BEGIN
	--load the status ids into variables
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
	select id into pass_status_id from status where name = 'PASS';
	select id into fail_status_id from status where name = 'FAIL';
	select id into error_status_id from status where name = 'ERROR';
	
    select name into prod_instruction_result_new_status from status where status.id = NEW.status_id;
	
	--get the run id form the instrcutio id 
	select run_id into target_run_id from prod_instruction_result where prod_instruction_result.id = NEW.id;
	
	--get the run status id form the run id
	select status_id into run_status_id from run where run.id = target_run_id;
    select name into run_status_name from status where status.id = run_status_id;

	--check the current run status and the new instrcution result status
    IF run_status_id = new_status_id and new.status_id = new_status_id THEN -- 
		update run set status_id = wip_status_id, start_at = now() where id = target_run_id;
		
	ELSIF run_status_id = new_status_id and new.status_id = error_status_id THEN -- 
		update run set status_id = error_status_id, start_at = now() where id = target_run_id;
		
	ELSIF  run_status_id = new_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id, start_at = now()  where id = target_run_id;
		
	ELSIF run_status_id = new_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id, start_at = now() where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = pass_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = error_status_id THEN
		update run set status_id = error_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
		
	ELSIF run_status_id = wip_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
		
	ELSIF run_status_id = fail_status_id and new.status_id = error_status_id THEN
		update run set status_id = error_status_id where id = target_run_id;
		
	END IF;
	RETURN NEW;	
END;
$$;

--update instruction result triggers
CREATE OR REPLACE FUNCTION prod_step_log_update_prod_instruction_result_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	new_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	done_status_id bigint;
    
    fail_log_level_id bigint;
    error_log_level_id bigint;
    
    new_prod_step_log_type_id bigint;
	new_prod_step_log_level text;
    
	target_prod_instruction_result_id bigint; --the run linked the the instruction result
	target_prod_instruction_result_current_status_id bigint; -- the run current status id
    target_prod_instruction_result_status_name text;
BEGIN
	--load the status ids into variables
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
	select id into pass_status_id from status where name = 'PASS';
	select id into fail_status_id from status where name = 'FAIL';
	select id into error_status_id from status where name = 'ERROR';
    
    --get the log level id form the prod_step_log_id
    select step_log_type_id into new_prod_step_log_type_id from prod_step_log where prod_step_log.id = NEW.id;
    --get the log  leve of the new inserted step log
	  
	--get the status of the instrcution result 
	select status_id into target_prod_instruction_result_current_status_id from prod_instruction_result where prod_instruction_result.id = NEW.instruction_result_id;

	--check the current prod_instruction_result status and the new instrcution result status
	--update instruction from new to wip except execption and error type step log
    IF target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id <> 4 and new_prod_step_log_type_id <> 8 THEN -- 
		update prod_instruction_result  set status_id = wip_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id = 8 THEN -- 
		update prod_instruction_result  set status_id = error_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF  target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result set status_id = fail_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 8 THEN
		update prod_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_prod_instruction_result_current_status_id = fail_status_id and new_prod_step_log_type_id = 8 THEN
		update prod_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		
	END IF;
	RETURN NEW;	
END;
$$;

CREATE OR REPLACE FUNCTION dev_step_log_update_dev_instruction_result_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	new_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	done_status_id bigint;
    
    fail_log_level_id bigint;
    error_log_level_id bigint;
    
    new_dev_step_log_type_id bigint;
	new_dev_step_log_level text;
    
	target_dev_instruction_result_id bigint; --the run linked the the instruction result
	target_dev_instruction_result_current_status_id bigint; -- the run current status id
    target_dev_instruction_result_status_name text;
BEGIN
	--load the status ids into variables
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
	select id into pass_status_id from status where name = 'PASS';
	select id into fail_status_id from status where name = 'FAIL';
	select id into error_status_id from status where name = 'ERROR';
    
    --get the log level id form the dev_step_log_id
    select step_log_type_id into new_dev_step_log_type_id from dev_step_log where dev_step_log.id = NEW.id;
    --get the log  leve of the new inserted step log
	  
	--get the status of the instrcution result 
	select status_id into target_dev_instruction_result_current_status_id from dev_instruction_result where dev_instruction_result.id = NEW.instruction_result_id;

	--check the current dev_instruction_result status and the new instrcution result status
	--update instruction from new to wip except execption and error type step log
    IF target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id <> 4 and new_dev_step_log_type_id <> 8 THEN -- 
		update dev_instruction_result  set status_id = wip_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id = 8 THEN -- 
		update dev_instruction_result  set status_id = error_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF  target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result set status_id = fail_status_id, start_at = now() where id = NEW.instruction_result_id;
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 8 THEN
		update dev_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_dev_instruction_result_current_status_id = fail_status_id and new_dev_step_log_type_id = 8 THEN
		update dev_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		
	END IF;
	RETURN NEW;	
END;
$$;