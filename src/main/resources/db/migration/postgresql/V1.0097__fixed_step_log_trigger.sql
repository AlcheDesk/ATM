CREATE OR REPLACE FUNCTION prod_step_log_update_prod_instruction_result_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE
--	i int;
--	na_status_id bigint;
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
    
--    select id into fail_log_level_id from log_level where log_level.name = 'FAIL';
--    select id into error_log_level_id from log_level where log_level.name = 'ERROR';
    
    --get the log level id form the prod_step_log_id
    select step_log_type_id into new_prod_step_log_type_id from prod_step_log where prod_step_log.id = NEW.id;
    --get the log  leve of the new inserted step log
--    select name into new_prod_step_log_level from log_level where log_level.id = new_prod_step_log_type_id;
--	RAISE NOTICE 'trigger by step log, new step log level is %', new_prod_step_log_level;
	  
	--get the status of the instrcution result 
--    RAISE NOTICE 'trigger by step log, target instrcution result id is %', NEW.instruction_result_id;
	select status_id into target_prod_instruction_result_current_status_id from prod_instruction_result where prod_instruction_result.id = NEW.instruction_result_id;
--    select name into target_prod_instruction_result_status_name from status where status.id = target_prod_instruction_result_current_status_id;
--    RAISE NOTICE 'trigger by step log, target instrcution result current status is %', target_prod_instruction_result_status_name; 

	--check the current prod_instruction_result status and the new instrcution result status
	--update instruction from new to wip except execption and error type step log
    IF target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id <> 4 and new_prod_step_log_type_id <> 8 THEN -- 
		update prod_instruction_result  set status_id = wip_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id = 8 THEN -- 
		update prod_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF  target_prod_instruction_result_current_status_id = new_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL';
		
--	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id =  THEN
--		update prod_instruction_result set status_id = wip_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 8 THEN
		update prod_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR';
		
	ELSIF target_prod_instruction_result_current_status_id = fail_status_id and new_prod_step_log_type_id = 8 THEN
		update prod_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF target_prod_instruction_result_current_status_id = wip_status_id and new_prod_step_log_type_id = 4 THEN
		update prod_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	END IF;
	RETURN NEW;	
END;

$$;

--==========================================================================================
CREATE OR REPLACE FUNCTION dev_step_log_update_dev_instruction_result_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE
--	i int;
--	na_status_id bigint;
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
    
--    select id into fail_log_level_id from log_level where log_level.name = 'FAIL';
--    select id into error_log_level_id from log_level where log_level.name = 'ERROR';
    
    --get the log level id form the dev_step_log_id
    select step_log_type_id into new_dev_step_log_type_id from dev_step_log where dev_step_log.id = NEW.id;
    --get the log  leve of the new inserted step log
--    select name into new_dev_step_log_level from log_level where log_level.id = new_dev_step_log_type_id;
--	RAISE NOTICE 'trigger by step log, new step log level is %', new_dev_step_log_level;
	  
	--get the status of the instrcution result 
--    RAISE NOTICE 'trigger by step log, target instrcution result id is %', NEW.instruction_result_id;
	select status_id into target_dev_instruction_result_current_status_id from dev_instruction_result where dev_instruction_result.id = NEW.instruction_result_id;
--    select name into target_dev_instruction_result_status_name from status where status.id = target_dev_instruction_result_current_status_id;
--    RAISE NOTICE 'trigger by step log, target instrcution result current status is %', target_dev_instruction_result_status_name; 

	--check the current dev_instruction_result status and the new instrcution result status
	--update instruction from new to wip except execption and error type step log
    IF target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id <> 4 and new_dev_step_log_type_id <> 8 THEN -- 
		update dev_instruction_result  set status_id = wip_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id = 8 THEN -- 
		update dev_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF  target_dev_instruction_result_current_status_id = new_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL';
		
--	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id =  THEN
--		update dev_instruction_result set status_id = wip_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 8 THEN
		update dev_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR';
		
	ELSIF target_dev_instruction_result_current_status_id = fail_status_id and new_dev_step_log_type_id = 8 THEN
		update dev_instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF target_dev_instruction_result_current_status_id = wip_status_id and new_dev_step_log_type_id = 4 THEN
		update dev_instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	END IF;
	RETURN NEW;	
END;
$$;