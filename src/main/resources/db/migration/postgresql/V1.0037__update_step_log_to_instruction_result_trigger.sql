CREATE OR REPLACE FUNCTION step_log_update_instruction_result_status() RETURNS trigger
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
    
    new_step_log_level_id bigint;
	new_step_log_level text;
    
	target_instruction_result_id bigint; --the run linked the the instruction result
	tsrget_instruction_result_status_id bigint; -- the run current status id
    target_instruction_result_status_name text;
BEGIN
	--load the status ids into variables
	select id into new_status_id from status where name = 'NEW';
	select id into wip_status_id from status where name = 'WIP';
	select id into pass_status_id from status where name = 'PASS';
	select id into fail_status_id from status where name = 'FAIL';
	select id into error_status_id from status where name = 'ERROR';
    
    select id into fail_log_level_id from log_level where log_level.name = 'FAIL';
    select id into error_log_level_id from log_level where log_level.name = 'ERROR';
    
    --get the log level id form the step_log_id
    select level_id into new_step_log_level_id from step_log where step_log.id = NEW.id;
    --get the log  leve of the new inserted step log
    select name into new_step_log_level from log_level where log_level.id = new_step_log_level_id;
	RAISE NOTICE 'trigger by step log, new step log level is %', new_step_log_level;
	  
	--get the status of the instrcution result 
    RAISE NOTICE 'trigger by step log, target instrcution result id is %', NEW.instruction_result_id;
	select status_id into tsrget_instruction_result_status_id from instruction_result where instruction_result.id = NEW.instruction_result_id;
    select name into target_instruction_result_status_name from status where status.id = tsrget_instruction_result_status_id;
    RAISE NOTICE 'trigger by step log, target instrcution result current status is %', target_instruction_result_status_name; 

	--check the current instruction_result status and the new instrcution result status
	--update instruction from new to wip exception fail and error step log
    IF tsrget_instruction_result_status_id = new_status_id and new_step_log_level_id <> fail_log_level_id and new_step_log_level_id <> error_log_level_id THEN -- 
		update instruction_result  set status_id = wip_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF tsrget_instruction_result_status_id = new_status_id and new_step_log_level_id = error_log_level_id THEN -- 
		update instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF  tsrget_instruction_result_status_id = new_status_id and new_step_log_level_id = fail_log_level_id THEN
		update instruction_result set status_id = fail_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL';
		
--	ELSIF tsrget_instruction_result_status_id = wip_status_id and new_step_log_level_id =  THEN
--		update instruction_result set status_id = wip_status_id where id = NEW.instruction_result_id;
--		RAISE NOTICE 'trigger by step log, update instruction result status to WIP'; 
		
	ELSIF tsrget_instruction_result_status_id = wip_status_id and new_step_log_level_id = fail_log_level_id THEN
		update instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	ELSIF tsrget_instruction_result_status_id = wip_status_id and new_step_log_level_id = error_log_level_id THEN
		update instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR';
		
	ELSIF tsrget_instruction_result_status_id = fail_status_id and new_step_log_level_id = error_log_level_id THEN
		update instruction_result  set status_id = error_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to ERROR'; 
		
	ELSIF tsrget_instruction_result_status_id = wip_status_id and new_step_log_level_id = fail_status_id THEN
		update instruction_result  set status_id = fail_status_id where id = NEW.instruction_result_id;
		RAISE NOTICE 'trigger by step log, update instruction result status to FAIL'; 
		
	END IF;
	RETURN NEW;	
END;

$$;