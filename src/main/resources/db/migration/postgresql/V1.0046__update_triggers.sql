CREATE OR REPLACE FUNCTION instruction_result_update_run_status() RETURNS trigger
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
	instruction_result_new_status text;
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
	
    select name into instruction_result_new_status from status where status.id = NEW.status_id;
--	RAISE NOTICE 'trigger by instruction result, new instruction result status is %', instruction_result_new_status;
	
	--get the run id form the instrcutio id 
	select run_id into target_run_id from instruction_result where instruction_result.id = NEW.id;
--	RAISE NOTICE 'trigger by instruction result, target run id is %', target_run_id; 
	
	--get the run status id form the run id
	select status_id into run_status_id from run where run.id = target_run_id;
    select name into run_status_name from status where status.id = run_status_id;
--    RAISE NOTICE 'trigger by instruction result, target run current status is %', run_status_name; 

	--check the current run status and the new instrcution result status
    IF run_status_id = new_status_id and new.status_id = new_status_id THEN -- 
		update run  set status_id = wip_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = new_status_id and new.status_id = error_status_id THEN -- 
		update run  set status_id = error_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 
	ELSIF  run_status_id = new_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = new_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to FAIL'; 
	ELSIF run_status_id = wip_status_id and new.status_id = pass_status_id THEN
		update run  set status_id = wip_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = wip_status_id and new.status_id = error_status_id THEN
		update run  set status_id = error_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 
	ELSIF run_status_id = wip_status_id and new.status_id = wip_status_id THEN
		update run  set status_id = wip_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to PASS'; 
	ELSIF run_status_id = wip_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to FAIL'; 
	ELSIF run_status_id = fail_status_id and new.status_id = error_status_id THEN
		update run  set status_id = error_status_id where id = target_run_id;
--		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 	
	END IF;
--    select status_id into new_run_status_id from run where run.id = target_run_id;
--    select name into new_run_status_name from status where status.id = run_status_id;
--    RAISE NOTICE 'target run new status is %', new_run_status_name; 
	RETURN NEW;	
END;
$$;


CREATE OR REPLACE FUNCTION "generate_instrcution_target" ()  RETURNS trigger
  VOLATILE
AS $$
DECLARE
    application_name text;
    section_name text;
    element_name text;
BEGIN
	IF NEW.element_id IS NULL THEN
		select name into application_name from application where id = NEW.application_id;
	--    RAISE NOTICE 'application name %', application_name;
		select name into section_name from section where id = NEW.section_id;
	--    RAISE NOTICE 'section name %', section_name;
	    NEW.target := application_name||'.'||section_name;	
	ELSE
		select name into application_name from application where id = NEW.application_id;
	--    RAISE NOTICE 'application name %', application_name;
		select name into section_name from section where id = NEW.section_id;
	--    RAISE NOTICE 'section name %', section_name;
		select name into element_name from element where id = NEW.element_id;
	--    RAISE NOTICE 'element name %', element_name;
	    NEW.target := application_name||'.'||section_name||'.'||element_name;	
	END IF;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;