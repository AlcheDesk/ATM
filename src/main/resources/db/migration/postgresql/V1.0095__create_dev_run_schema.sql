--clean up triggers
DROP TRIGGER "prod_instruction_result_update_run_status" ON "prod_instruction_result";
ALTER TRIGGER "prod_step_log_update_step_log_created_at_updated_at" ON "prod_step_log" RENAME TO prod_step_log_update_created_at_updated_at;
ALTER TRIGGER "prod_file_update_execution_log_created_at_updated_at" ON "prod_file" RENAME TO prod_file_update_created_at_updated_at;
ALTER TRIGGER "prod_execution_log_update_execution_log_created_at_updated_at" ON "prod_execution_log" RENAME TO prod_execution_log_update_created_at_updated_at;


-- create new triggers
CREATE OR REPLACE FUNCTION dev_instruction_result_update_run_status() RETURNS trigger
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
--	RAISE NOTICE 'trigger by instruction result, new instruction result status is %', dev_instruction_result_new_status;
	
	--get the run id form the instrcutio id 
	select run_id into target_run_id from dev_instruction_result where dev_instruction_result.id = NEW.id;
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
    select type_id into new_dev_step_log_type_id from dev_step_log where dev_step_log.id = NEW.id;
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

--dev instruction result table
CREATE TABLE dev_instruction_result
    (
        id bigserial NOT NULL,
        target text NOT NULL,
        action text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        status_id bigint DEFAULT 1 NOT NULL,
        log text,
        finished BOOLEAN DEFAULT false NOT NULL,
        instruction json NOT NULL,
        data json,
        input text NOT NULL,
        run_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT ext_instruction_result_fk_run FOREIGN KEY (run_id) REFERENCES "run" ("id"),
        CONSTRAINT ext_instruction_result_fk_status FOREIGN KEY (status_id) REFERENCES "status" ("id")
    );

CREATE TRIGGER dev_instruction_result_finished_status_update
    BEFORE UPDATE 
    ON dev_instruction_result
    FOR EACH ROW
    EXECUTE PROCEDURE finished_update_status();

CREATE TRIGGER dev_instruction_result_trigger_run_status_update
    AFTER INSERT
    ON dev_instruction_result
    FOR EACH ROW
    EXECUTE PROCEDURE dev_instruction_result_update_run_status();

CREATE TRIGGER dev_instruction_result_update_created_at_updated_at
    BEFORE UPDATE 
    ON dev_instruction_result
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_column();

--dev execution log table
CREATE TABLE dev_execution_log
(
    id bigserial NOT NULL,
    message text NOT NULL,
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    log_level_id bigint NOT NULL DEFAULT 3,
    instruction_result_id bigint NOT NULL,
    run_id bigint NOT NULL,
    CONSTRAINT dev_execution_log_pkey PRIMARY KEY (id),
    CONSTRAINT dev_execution_log_fk_log_level FOREIGN KEY (log_level_id) REFERENCES log_level (id),
    CONSTRAINT dev_execution_log_fk_dev_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES dev_instruction_result (id),
    CONSTRAINT dev_execution_log_fk_run FOREIGN KEY (run_id) REFERENCES run (id)
);

CREATE TRIGGER dev_execution_log_update_created_at_updated_at
    BEFORE UPDATE 
    ON dev_execution_log
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_column();   

--dev step log table
CREATE TABLE dev_step_log
(    
    id bigserial NOT NULL,
    message text NOT NULL,
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    instruction_result_id bigint,
    step_log_type_id bigint NOT NULL,
    CONSTRAINT dev_step_log_pkey PRIMARY KEY (id),
    CONSTRAINT dev_step_log_fk_dev_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES dev_instruction_result (id),
    CONSTRAINT prod_step_log_fk_type FOREIGN KEY (step_log_type_id) REFERENCES step_log_type (id)
);

CREATE TRIGGER dev_step_log_trigger_dev_instruction_result_status_update
    AFTER INSERT
    ON dev_step_log
    FOR EACH ROW
    EXECUTE PROCEDURE dev_step_log_update_dev_instruction_result_status();

CREATE TRIGGER dev_step_log_update_created_at_updated_at
    BEFORE UPDATE 
    ON dev_step_log
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_column();
    
-- dev file table
CREATE TABLE dev_file
(   
    id bigserial NOT NULL,
    name text NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone NOT NULL DEFAULT now(),
    uuid uuid NOT NULL DEFAULT uuid_generate_v4(),
    parameter json,
    file_type_id bigint,
    instruction_result_id bigint,
    uri text,
    CONSTRAINT dev_file_pkey PRIMARY KEY (id),
    CONSTRAINT dev_file_fk_dev_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES dev_instruction_result (id),
    CONSTRAINT dev_file_fk_type FOREIGN KEY (file_type_id) REFERENCES file_type (id)
);

CREATE TRIGGER dev_file_update_created_at_updated_at
    BEFORE UPDATE 
    ON dev_file
    FOR EACH ROW
    EXECUTE PROCEDURE update_created_at_updated_at_column();