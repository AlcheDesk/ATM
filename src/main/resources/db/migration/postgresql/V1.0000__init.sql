--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.0

-- Started on 2017-10-28 02:50:20

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 1 (class 3079 OID 12425)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2998 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 2 (class 3079 OID 18453)
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- TOC entry 2999 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


SET search_path = public, pg_catalog;

--
-- TOC entry 296 (class 1255 OID 17401)

--

CREATE FUNCTION access_update_none() RETURNS void
    LANGUAGE plpgsql
    AS $$DECLARE
	i integer;
 execution_ids bigint[] := Array(select executionResult.execution_id from execution_to_result_link executionResult inner join instruction_result results on executionResult.result_id = results.ID where results.id = 1);
BEGIN
raise info 'array size (%)', array_lower(execution_ids,1);
   FOREACH i IN array execution_ids
   loop
        raise info 'array value (%)', execution_ids[i];
   end loop;
END; $$;




--
-- TOC entry 317 (class 1255 OID 17403)

--

CREATE FUNCTION execution_status_trigger_test() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
	i bigint;
	na_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	execution_ids bigint[];
    execution_status_ids bigint[];
    result_status_ids bigint[];
    execution_result_status_list bigint[];
BEGIN
--load the status ids into variables
select id into na_status_id from status where name = 'N/A';
select id into wip_status_id from status where name = 'WIP';
select id into pass_status_id from status where name = 'PASS';
select id into fail_status_id from status where name = 'FAIL';
select id into error_status_id from status where name = 'ERROR';

RAISE NOTICE 'N/A (%)',na_status_id;
RAISE NOTICE 'wip (%)',wip_status_id;
RAISE NOTICE 'pass (%)',pass_status_id;
RAISE NOTICE 'fail (%)',fail_status_id;
RAISE NOTICE 'error (%)',error_status_id;

--get the list of exectuions that linked to this result and the current status of those executions
select array_agg(execution_id), array_agg(exe_status_id),array_agg(result_status_id) 
into strict execution_ids, execution_status_ids, result_status_ids
from ( 	select exe.id execution_id , exe.status_id exe_status_id, result.status_id result_status_id 
      	from execution_to_result_link executionResult 
			inner join instruction_result result on executionResult.result_id = result.ID
			inner join execution exe on exe.id = executionResult.execution_id
		where result.id = 130
     ) as conditions;
	RAISE NOTICE ' execution id array list  (%)',array_to_string(execution_ids,'##');
	 -- loop through the linked executions to theck if we need to update the status of those executions
     FOR i IN 1 .. array_upper(execution_ids, 1)
--    FOREACH i IN ARRAY execution_ids
    LOOP 
    	RAISE NOTICE 'process execution id (%)',execution_ids[i];
        RAISE NOTICE 'i value %', i;
		-- get all distincted linked resutl status for this execution to check if the status is really a pass.
    	select array_agg(status_id) 
		from 	(select distinct status_id 
				from instruction_result result 
				inner join execution_to_result_link executionResult 
					on executionResult.result_id = result.ID 
					where executionResult.execution_id = execution_ids[i]
				) 
		as status_list 
		into strict execution_result_status_list ;
			RAISE NOTICE ' array list  (%)',array_to_string(execution_result_status_list,'##');
        --check the current execution status and the new result status
		-- update to error if any error in the resutls, which is before the fail
		IF execution_result_status_list @> array[error_status_id] THEN
        	RAISE NOTICE '1 (%)',execution_ids[i];
			update execution  set status_id = error_status_id where id = execution_ids[i];
		-- udpate to fail if there ia any fial in the results
		ELSIF  execution_result_status_list @> array[fail_status_id] THEN
        RAISE NOTICE '2 (%)',execution_ids[i];
			update execution set status_id = fail_status_id where id = execution_ids[i];
		-- update to wip when the results contains N/A and pass only, has both
		ELSIF array_upper(execution_result_status_list,1) = 2 and execution_result_status_list @> array[na_status_id,pass_status_id] THEN
        	RAISE NOTICE '3(%)',execution_ids[i];
			update execution set status_id = wip_status_id where id = execution_ids[i];
		-- udpate to pass when the resutls status has pass only
		ELSIF array_upper(execution_result_status_list,1) = 1 and execution_result_status_list @> array[pass_status_id] THEN
        	RAISE NOTICE '4 (%)',execution_ids[i];
			-- update from wip to fail
			update execution  set status_id = pass_status_id where id = execution_ids[i];		
		ELSE
        	RAISE NOTICE '5 (%)',execution_ids[i];
			-- else we update to error
			update execution set status_id = error_status_id where id = execution_ids[i];		
		END IF;       
    END LOOP; 
END;

$$;




--
-- TOC entry 311 (class 1255 OID 17404)

--

CREATE FUNCTION execution_status_update() RETURNS void
    LANGUAGE plpgsql
    AS $$
DECLARE
i int;
	new_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	execution_ids bigint[];
    execution_status_ids bigint[];
    result_status_ids bigint[];
    execution_result_status_list bigint[];
BEGIN
select id into new_status_id from status where name = 'NEW';
select id into wip_status_id from status where name = 'WIP';
select id into pass_status_id from status where name = 'PASS';
select id into fail_status_id from status where name = 'FAIL';
select id into error_status_id from status where name = 'ERROR';

select array_agg(execution_id), array_agg(exe_status_id),array_agg(result_status_id) 
into strict execution_ids, execution_status_ids, result_status_ids
from ( 	select exe.id execution_id , exe.status_id exe_status_id, result.status_id result_status_id 
      	from execution_to_result_link executionResult 
			inner join instruction_result result on executionResult.result_id = result.ID
			inner join execution exe on exe.id = executionResult.execution_id
		where result.id = 1
     ) as conditions;

    FOREACH i IN ARRAY execution_ids
    LOOP 
    	select array_agg(status_id) from (select distinct status_id from instruction_result result inner join execution_to_result_link executionResult on executionResult.result_id = result.ID where executionResult.execution_id = execution_ids[i]) as status_list into strict execution_result_status_list ;
	    RAISE NOTICE  ' array list (%)', array_to_string(execution_result_status_list,','); 
        --check the current execution status and the new result status
		IF execution_status_ids[i] = new_status_id and result_status_ids[i] = wip_status_id THEN
			RAISE NOTICE  ' new update to (%)', 'wip'; 
		ELSIF execution_status_ids[i] = wip_status_id and result_status_ids[i] = pass_status_id THEN
			RAISE NOTICE  ' wip update to (%)', 'pass'; 
		ELSIF execution_status_ids[i] = wip_status_id and result_status_ids[i] = fail_status_id THEN
			RAISE NOTICE  ' wip update to (%)', 'fail'; 		
		END IF;       
    END LOOP; 

RAISE NOTICE 'new (%)',new_status_id;
RAISE NOTICE 'wip (%)',wip_status_id;
RAISE NOTICE 'pass (%)',pass_status_id;
RAISE NOTICE 'fail (%)',fail_status_id;
RAISE NOTICE 'error (%)',error_status_id;

RAISE NOTICE 'execution array (%)',array_to_string(execution_ids, ',');
RAISE NOTICE 'execution status array (%)',array_to_string(execution_status_ids, ',');
RAISE NOTICE 'result status array (%)',array_to_string(result_status_ids, ',');
RAISE NOTICE 'array (%)',execution_ids[2]::text;

END; 
$$;




--
-- TOC entry 325 (class 1255 OID 18518)

--

CREATE FUNCTION finished_update_status() RETURNS trigger
    LANGUAGE plpgsql
    AS $$

DECLARE
	wip_status_id bigint;
    pass_status_id bigint;

BEGIN
	select id into wip_status_id from status where name = 'WIP';
    select id into pass_status_id from status where name = 'PASS';

    IF OLD.status_id = wip_status_id and OLD.finished = false and NEW.finished = true THEN
    	NEW.status_id = pass_status_id;
    END IF;
    RETURN NEW;   
END;

$$;




--
-- TOC entry 312 (class 1255 OID 17405)

--

CREATE FUNCTION insert_active_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    IF NEW.active IS NULL THEN
    	NEW.active = true;
    END IF;
    RETURN NEW;   
END;
$$;




--
-- TOC entry 309 (class 1255 OID 17406)

--

CREATE FUNCTION insert_updated_at_created_at_active_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    NEW.updated_at = now();
    NEW.created_at = now();
    IF NEW.active IS NULL THEN
    	NEW.active = true;
    END IF;
    RETURN NEW;   
END;
$$;




--
-- TOC entry 310 (class 1255 OID 17407)

--

CREATE FUNCTION insert_updated_at_created_at_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    NEW.updated_at = now();
    NEW.created_at = now();
    RETURN NEW;   
END;
$$;




--
-- TOC entry 326 (class 1255 OID 17402)

--

CREATE FUNCTION instruction_result_update_run_status() RETURNS trigger
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
	RAISE NOTICE 'trigger by instruction result, new instruction result status is %', instruction_result_new_status;
	
	--get the run id form the instrcutio id 
	select run_id into target_run_id from run_instruction_result_link where instruction_result_id = OLD.id;
	RAISE NOTICE 'trigger by instruction result, target run id is %', target_run_id; 
	
	--get the run status id form the run id
	select status_id into run_status_id from run where run.id = target_run_id;
    select name into run_status_name from status where status.id = run_status_id;
    RAISE NOTICE 'trigger by instruction result, target run current status is %', run_status_name; 

	--check the current run status and the new instrcution result status
    IF run_status_id = new_status_id and new.status_id = new_status_id THEN -- 
		update run  set status_id = wip_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = new_status_id and new.status_id = error_status_id THEN -- 
		update run  set status_id = error_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 
	ELSIF  run_status_id = new_status_id and new.status_id = wip_status_id THEN
		update run set status_id = wip_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = new_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to FAIL'; 
	ELSIF run_status_id = wip_status_id and new.status_id = pass_status_id THEN
		update run  set status_id = wip_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to WIP'; 
	ELSIF run_status_id = wip_status_id and new.status_id = error_status_id THEN
		update run  set status_id = error_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 
	ELSIF run_status_id = wip_status_id and new.status_id = wip_status_id THEN
		update run  set status_id = wip_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to PASS'; 
	ELSIF run_status_id = wip_status_id and new.status_id = fail_status_id THEN
		update run set status_id = fail_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to FAIL'; 
	ELSIF run_status_id = fail_status_id and new.status_id = error_status_id THEN
		update run  set status_id = error_status_id where id = target_run_id;
		RAISE NOTICE 'trigger by instruction result, update run status to ERROR'; 	
	END IF;
--    select status_id into new_run_status_id from run where run.id = target_run_id;
--    select name into new_run_status_name from status where status.id = run_status_id;
--    RAISE NOTICE 'target run new status is %', new_run_status_name; 
	RETURN NEW;	
END;

$$;




--
-- TOC entry 328 (class 1255 OID 17408)

--

CREATE FUNCTION run_status_trigger() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
DECLARE
	i int;
	na_status_id bigint;
    wip_status_id bigint;
    pass_status_id bigint;
    fail_status_id bigint;
    error_status_id bigint;
	run_ids bigint[];
    run_status_ids bigint[];
    result_status_ids bigint[];
    run_result_status_list bigint[];
BEGIN
--load the status ids into variables
select id into na_status_id from status where name = 'N/A';
select id into wip_status_id from status where name = 'WIP';
select id into pass_status_id from status where name = 'PASS';
select id into fail_status_id from status where name = 'FAIL';
select id into error_status_id from status where name = 'ERROR';

--get the list of exectuions that linked to this result and the current status of those runs
select array_agg(run_id), array_agg(exe_status_id),array_agg(result_status_id) 
into strict run_ids, run_status_ids, result_status_ids
from ( 	select exe.id run_id , exe.status_id exe_status_id, result.status_id result_status_id 
      	from run_to_result_link runResult 
			inner join result result on runResult.result_id = result.id
			inner join run exe on exe.id = runResult.run_id
		where result.id = new.id
     ) as conditions;

	 -- loop through the linked runs to theck if we need to update the status of those runs
     FOR i IN 1 .. array_upper(run_ids, 1)
--    FOREACH i IN ARRAY run_ids
    LOOP 
		-- get all distincted linked resutl status for this run to check if the status is really a pass.
    	select array_agg(status_id) 
		from 	(select distinct status_id 
				from result result 
				inner join run_to_result_link runResult 
					on runResult.result_id = result.ID 
					where runResult.run_id = run_ids[i]
				) 
		as status_list 
		into strict run_result_status_list ;

        --check the current run status and the new result status
		-- update to error if any error in the resutls, which is before the fail
		IF run_result_status_list @> array[error_status_id] THEN
			update run  set status_id = error_status_id where id = run_ids[i];
		-- udpate to fail if there ia any fial in the results
		ELSIF  run_result_status_list @> array[fail_status_id] THEN
			update run set status_id = fail_status_id where id = run_ids[i];
		-- update to wip when the results contains N/A and pass only, has both
		ELSIF array_ndims(run_result_status_list) = 2 and run_result_status_list @> array[na_status_id,pass_status_id] THEN
			update run set status_id = wip_status_id where id = run_ids[i];
		-- udpate to pass when the resutls status has pass only
		ELSIF array_ndims(run_result_status_list) = 1 and run_result_status_list @> array[pass_status_id] THEN
			-- update from wip to fail
			update run  set status_id = pass_status_id where id = run_ids[i];		
		ELSE
			-- else we update to error
			update run set status_id = error_status_id where id = run_ids[i];		
		END IF;       
    END LOOP; 
  RETURN NEW;
END;

$$;




--
-- TOC entry 315 (class 1255 OID 17409)

--

CREATE FUNCTION status_na_insert() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
--updated the modified time of the record
  select stat.id into NEW.status_id from status stat where stat.name = 'N/A';
--check if the log needs to be updated
  RETURN NEW;
END;

$$;




--
-- TOC entry 316 (class 1255 OID 18502)

--

CREATE FUNCTION step_log_update_instruction_result_status() RETURNS trigger
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
    select level_id into new_step_log_level_id from step_log where step_log.id = NEW.step_log_id;
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




--
-- TOC entry 313 (class 1255 OID 17410)

--

CREATE FUNCTION update_created_at_updated_at_column() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
    NEW.updated_at = now();
    NEW.created_at = OLD.created_at;
    RETURN NEW;   
END;
$$;




--
-- TOC entry 314 (class 1255 OID 17411)

--

CREATE FUNCTION update_modified_time_log() RETURNS trigger
    LANGUAGE plpgsql
    AS $$BEGIN
	if NEW.log is not null and NEW.log <> '' then
		NEW.log := OLD.log||chr(10)||current_timestamp||chr(32)||NEW.log;
	end if;
  RETURN NEW;
END;
$$;




SET default_tablespace = '';

--
-- TOC entry 186 (class 1259 OID 17412)

--

CREATE TABLE application (
    id bigint NOT NULL,
    name text NOT NULL,
    comment text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    log text,
    parameter json,
    owner_id bigint
);




--
-- TOC entry 187 (class 1259 OID 17421)

--

CREATE SEQUENCE application_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3008 (class 0 OID 0)
-- Dependencies: 187

--

ALTER SEQUENCE application_id_seq OWNED BY application.id;


--
-- TOC entry 188 (class 1259 OID 17423)

--

CREATE TABLE application_section_link (
    id bigint NOT NULL,
    application_id bigint NOT NULL,
    section_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 189 (class 1259 OID 17427)

--

CREATE SEQUENCE application_section_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3009 (class 0 OID 0)
-- Dependencies: 189

--

ALTER SEQUENCE application_section_link_id_seq OWNED BY application_section_link.id;


--
-- TOC entry 282 (class 1259 OID 20520)

--

CREATE TABLE atm_object_type (
    name text NOT NULL,
    id bigint NOT NULL
);




--
-- TOC entry 281 (class 1259 OID 20518)

--

CREATE SEQUENCE atm_object_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3010 (class 0 OID 0)
-- Dependencies: 281

--

ALTER SEQUENCE atm_object_type_id_seq OWNED BY atm_object_type.id;


--
-- TOC entry 190 (class 1259 OID 17429)

--

CREATE TABLE element (
    id bigint NOT NULL,
    name text NOT NULL,
    comment text,
    locator_value text,
    html_position_x text,
    html_position_y text,
    active boolean DEFAULT true NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    log text,
    element_type_id bigint,
    element_locator_type_id bigint,
    owner_id bigint
);




--
-- TOC entry 191 (class 1259 OID 17438)

--

CREATE TABLE element_action (
    id bigint NOT NULL,
    name text NOT NULL
);




--
-- TOC entry 192 (class 1259 OID 17444)

--

CREATE SEQUENCE element_action_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3011 (class 0 OID 0)
-- Dependencies: 192

--

ALTER SEQUENCE element_action_id_seq OWNED BY element_action.id;


--
-- TOC entry 193 (class 1259 OID 17446)

--

CREATE TABLE element_element_type_link (
    id bigint NOT NULL,
    element_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    element_type_id bigint NOT NULL
);




--
-- TOC entry 194 (class 1259 OID 17450)

--

CREATE SEQUENCE element_element_type_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3012 (class 0 OID 0)
-- Dependencies: 194

--

ALTER SEQUENCE element_element_type_link_id_seq OWNED BY element_element_type_link.id;


--
-- TOC entry 195 (class 1259 OID 17452)

--

CREATE SEQUENCE element_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3013 (class 0 OID 0)
-- Dependencies: 195

--

ALTER SEQUENCE element_id_seq OWNED BY element.id;


--
-- TOC entry 278 (class 1259 OID 18554)

--

CREATE TABLE element_locator_type (
    id bigint NOT NULL,
    name text NOT NULL
);




--
-- TOC entry 277 (class 1259 OID 18552)

--

CREATE SEQUENCE element_locator_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3014 (class 0 OID 0)
-- Dependencies: 277

--

ALTER SEQUENCE element_locator_type_id_seq OWNED BY element_locator_type.id;


--
-- TOC entry 196 (class 1259 OID 17454)

--

CREATE TABLE element_type (
    id bigint NOT NULL,
    name text NOT NULL
);




--
-- TOC entry 197 (class 1259 OID 17460)

--

CREATE TABLE element_type_element_action_link (
    element_type_id bigint NOT NULL,
    element_action_id bigint NOT NULL,
    active boolean NOT NULL,
    id bigint NOT NULL
);




--
-- TOC entry 276 (class 1259 OID 18442)

--

CREATE SEQUENCE element_type_element_action_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3015 (class 0 OID 0)
-- Dependencies: 276

--

ALTER SEQUENCE element_type_element_action_link_id_seq OWNED BY element_type_element_action_link.id;


--
-- TOC entry 280 (class 1259 OID 18567)

--

CREATE TABLE element_type_element_locator_type_link (
    id bigint NOT NULL,
    element_type_id bigint NOT NULL,
    element_locator_type_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 279 (class 1259 OID 18565)

--

CREATE SEQUENCE element_type_element_locator_type_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3016 (class 0 OID 0)
-- Dependencies: 279

--

ALTER SEQUENCE element_type_element_locator_type_link_id_seq OWNED BY element_type_element_locator_type_link.id;


--
-- TOC entry 198 (class 1259 OID 17463)

--

CREATE SEQUENCE element_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3017 (class 0 OID 0)
-- Dependencies: 198

--

ALTER SEQUENCE element_type_id_seq OWNED BY element_type.id;


--
-- TOC entry 199 (class 1259 OID 17465)

--

CREATE TABLE engine (
    id bigint NOT NULL,
    name text NOT NULL,
    comment text,
    active boolean NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    log text,
    owner_id bigint
);




--
-- TOC entry 200 (class 1259 OID 17473)

--

CREATE SEQUENCE engine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3018 (class 0 OID 0)
-- Dependencies: 200

--

ALTER SEQUENCE engine_id_seq OWNED BY engine.id;


--
-- TOC entry 201 (class 1259 OID 17475)

--

CREATE TABLE engine_module_property_link (
    id bigint NOT NULL,
    engine_id bigint NOT NULL,
    module_property_id bigint NOT NULL,
    active boolean DEFAULT true
);




--
-- TOC entry 202 (class 1259 OID 17479)

--

CREATE SEQUENCE engine_module_property_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3019 (class 0 OID 0)
-- Dependencies: 202

--

ALTER SEQUENCE engine_module_property_link_id_seq OWNED BY engine_module_property_link.id;


--
-- TOC entry 203 (class 1259 OID 17481)

--

CREATE TABLE environment (
    id bigint NOT NULL,
    name text NOT NULL,
    value text NOT NULL
);




--
-- TOC entry 204 (class 1259 OID 17487)

--

CREATE SEQUENCE environment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3020 (class 0 OID 0)
-- Dependencies: 204

--

ALTER SEQUENCE environment_id_seq OWNED BY environment.id;


--
-- TOC entry 205 (class 1259 OID 17489)

--

CREATE TABLE error_code (
    id bigint NOT NULL,
    message text NOT NULL,
    name text NOT NULL,
    code text
);




--
-- TOC entry 206 (class 1259 OID 17495)

--

CREATE SEQUENCE error_code_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3021 (class 0 OID 0)
-- Dependencies: 206

--

ALTER SEQUENCE error_code_id_seq OWNED BY error_code.id;


--
-- TOC entry 207 (class 1259 OID 17497)

--

CREATE TABLE file (
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    uuid uuid NOT NULL,
    parameter json,
    type_id bigint
);




--
-- TOC entry 292 (class 1259 OID 24426)

--

CREATE SEQUENCE group_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 293 (class 1259 OID 24428)

--

CREATE TABLE "group" (
    id bigint DEFAULT nextval('group_id_seq'::regclass) NOT NULL,
    name text NOT NULL,
    preloaded boolean DEFAULT false NOT NULL
);





--
-- TOC entry 208 (class 1259 OID 17508)

--

CREATE TABLE instruction (
    comment text,
    action text NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    input text,
    element_id bigint,
    active boolean DEFAULT true NOT NULL,
    project_id bigint,
    application_id bigint,
    section_id bigint,
    order_index bigint NOT NULL,
    log text,
    data json,
    target text
);




--
-- TOC entry 211 (class 1259 OID 17523)

--

CREATE SEQUENCE instruction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3022 (class 0 OID 0)
-- Dependencies: 211

--

ALTER SEQUENCE instruction_id_seq OWNED BY instruction.id;



--
-- TOC entry 212 (class 1259 OID 17525)

--

CREATE TABLE instruction_result (
    id bigint NOT NULL,
    target text,
    action text,
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone DEFAULT now(),
    status_id bigint DEFAULT 1 NOT NULL,
    log text,
    finished boolean DEFAULT false NOT NULL,
    instruction json,
    data json,
    input text
);




--
-- TOC entry 209 (class 1259 OID 17517)

--

CREATE TABLE instruction_result_file_link (
    id bigint NOT NULL,
    instruction_result_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    file_uuid uuid NOT NULL
);




--
-- TOC entry 210 (class 1259 OID 17521)

--

CREATE SEQUENCE instruction_result_file_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3023 (class 0 OID 0)
-- Dependencies: 210

--

ALTER SEQUENCE instruction_result_file_link_id_seq OWNED BY instruction_result_file_link.id;


--
-- TOC entry 213 (class 1259 OID 17535)

--

CREATE SEQUENCE instruction_result_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3024 (class 0 OID 0)
-- Dependencies: 213

--

ALTER SEQUENCE instruction_result_id_seq OWNED BY instruction_result.id;


--
-- TOC entry 214 (class 1259 OID 17537)

--

CREATE TABLE instruction_result_step_log_link (
    instruction_result_id bigint NOT NULL,
    step_log_id bigint NOT NULL,
    id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 215 (class 1259 OID 17541)

--

CREATE SEQUENCE instruction_result_step_log_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3025 (class 0 OID 0)
-- Dependencies: 215

--

ALTER SEQUENCE instruction_result_step_log_link_id_seq OWNED BY instruction_result_step_log_link.id;


--
-- TOC entry 216 (class 1259 OID 17543)

--

CREATE TABLE instruction_step_option_link (
    id bigint NOT NULL,
    instruction_id bigint,
    step_option_id bigint,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 217 (class 1259 OID 17547)

--

CREATE SEQUENCE instruction_step_option_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3026 (class 0 OID 0)
-- Dependencies: 217

--

ALTER SEQUENCE instruction_step_option_link_id_seq OWNED BY instruction_step_option_link.id;


--
-- TOC entry 218 (class 1259 OID 17549)

--

CREATE TABLE instruction_test_case_option_link (
    id bigint NOT NULL,
    instruction_id bigint NOT NULL,
    test_case_option_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 219 (class 1259 OID 17553)

--

CREATE SEQUENCE instruction_test_case_option_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3027 (class 0 OID 0)
-- Dependencies: 219

--

ALTER SEQUENCE instruction_test_case_option_link_id_seq OWNED BY instruction_test_case_option_link.id;


--
-- TOC entry 220 (class 1259 OID 17555)

--

CREATE TABLE log_level (
    id bigint NOT NULL,
    name text NOT NULL
);




--
-- TOC entry 221 (class 1259 OID 17561)

--

CREATE SEQUENCE log_level_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3028 (class 0 OID 0)
-- Dependencies: 221

--

ALTER SEQUENCE log_level_id_seq OWNED BY log_level.id;


--
-- TOC entry 222 (class 1259 OID 17563)

--

CREATE TABLE module (
    name text NOT NULL,
    id bigint NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    comment text,
    preset boolean
);




--
-- TOC entry 223 (class 1259 OID 17572)

--

CREATE SEQUENCE module_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3029 (class 0 OID 0)
-- Dependencies: 223

--

ALTER SEQUENCE module_id_seq OWNED BY module.id;


--
-- TOC entry 224 (class 1259 OID 17574)

--

CREATE TABLE module_module_property_link (
    id bigint NOT NULL,
    module_id bigint NOT NULL,
    module_property_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 225 (class 1259 OID 17578)

--

CREATE SEQUENCE module_module_property_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3030 (class 0 OID 0)
-- Dependencies: 225

--

ALTER SEQUENCE module_module_property_link_id_seq OWNED BY module_module_property_link.id;


--
-- TOC entry 226 (class 1259 OID 17580)

--

CREATE TABLE module_property (
    id bigint NOT NULL,
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    value text NOT NULL,
    comment text
);




--
-- TOC entry 227 (class 1259 OID 17589)

--

CREATE TABLE project (
    id bigint NOT NULL,
    name text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    comment text,
    active boolean DEFAULT true NOT NULL,
    log text,
    owner_id bigint,
    type_id bigint
);




--
-- TOC entry 228 (class 1259 OID 17598)

--

CREATE TABLE project_application_link (
    id bigint NOT NULL,
    project_id bigint NOT NULL,
    application_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 229 (class 1259 OID 17602)

--

CREATE SEQUENCE project_application_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3031 (class 0 OID 0)
-- Dependencies: 229

--

ALTER SEQUENCE project_application_link_id_seq OWNED BY project_application_link.id;


--
-- TOC entry 230 (class 1259 OID 17604)

--

CREATE SEQUENCE project_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3032 (class 0 OID 0)
-- Dependencies: 230

--

ALTER SEQUENCE project_id_seq OWNED BY project.id;


--
-- TOC entry 284 (class 1259 OID 20548)

--

CREATE TABLE project_object_type_link (
    id bigint NOT NULL,
    project_id bigint NOT NULL,
    object_type_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 283 (class 1259 OID 20546)

--

CREATE SEQUENCE project_object_type_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3033 (class 0 OID 0)
-- Dependencies: 283

--

ALTER SEQUENCE project_object_type_link_id_seq OWNED BY project_object_type_link.id;



--
-- TOC entry 231 (class 1259 OID 17606)

--

CREATE TABLE project_test_case_link (
    id bigint NOT NULL,
    project_id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 232 (class 1259 OID 17610)

--

CREATE SEQUENCE project_test_case_link_id_seq
    START WITH 11
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3034 (class 0 OID 0)
-- Dependencies: 232

--

ALTER SEQUENCE project_test_case_link_id_seq OWNED BY project_test_case_link.id;



--
-- TOC entry 290 (class 1259 OID 23450)

--

CREATE TABLE properties (
    id bigint NOT NULL,
    key text,
    value text,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 289 (class 1259 OID 23448)

--

CREATE SEQUENCE properties_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3035 (class 0 OID 0)
-- Dependencies: 289

--

ALTER SEQUENCE properties_id_seq OWNED BY properties.id;


--
-- TOC entry 233 (class 1259 OID 17612)

--

CREATE TABLE run (
    id bigint NOT NULL,
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    log text,
    status_id bigint DEFAULT 1 NOT NULL,
    finished boolean DEFAULT false NOT NULL,
    test_case json,
    parameter json
);




--
-- TOC entry 234 (class 1259 OID 17627)

--

CREATE SEQUENCE run_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3036 (class 0 OID 0)
-- Dependencies: 234

--

ALTER SEQUENCE run_id_seq OWNED BY run.id;


--
-- TOC entry 235 (class 1259 OID 17629)

--

CREATE TABLE run_instruction_result_link (
    id bigint NOT NULL,
    run_id bigint NOT NULL,
    instruction_result_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 236 (class 1259 OID 17633)

--

CREATE SEQUENCE run_instruction_result_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3037 (class 0 OID 0)
-- Dependencies: 236

--

ALTER SEQUENCE run_instruction_result_link_id_seq OWNED BY run_instruction_result_link.id;


--
-- TOC entry 237 (class 1259 OID 17635)

--

CREATE SEQUENCE run_module_property_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3038 (class 0 OID 0)
-- Dependencies: 237

--

ALTER SEQUENCE run_module_property_id_seq OWNED BY module_property.id;


--
-- TOC entry 238 (class 1259 OID 17637)

--

CREATE TABLE run_set (
    id bigint NOT NULL,
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    comment text,
    active boolean DEFAULT true NOT NULL,
    log text,
    owner_id bigint,
    group_id bigint
);




--
-- TOC entry 288 (class 1259 OID 22527)

--

CREATE TABLE run_set_ems_job_link (
    id bigint NOT NULL,
    run_set_id bigint NOT NULL,
    job_id bigint,
    job_uuid uuid
);




--
-- TOC entry 287 (class 1259 OID 22525)

--

CREATE SEQUENCE run_set_ems_job_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3039 (class 0 OID 0)
-- Dependencies: 287

--

ALTER SEQUENCE run_set_ems_job_link_id_seq OWNED BY run_set_ems_job_link.id;


--
-- TOC entry 239 (class 1259 OID 17644)

--

CREATE SEQUENCE run_set_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3040 (class 0 OID 0)
-- Dependencies: 239

--

ALTER SEQUENCE run_set_id_seq OWNED BY run_set.id;


--
-- TOC entry 240 (class 1259 OID 17646)

--

CREATE TABLE run_set_test_case_link (
    id bigint NOT NULL,
    run_set_id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 241 (class 1259 OID 17650)

--

CREATE SEQUENCE run_set_test_case_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3041 (class 0 OID 0)
-- Dependencies: 241

--

ALTER SEQUENCE run_set_test_case_link_id_seq OWNED BY run_set_test_case_link.id;


--
-- TOC entry 242 (class 1259 OID 17652)

--

CREATE TABLE section (
    id bigint NOT NULL,
    name text,
    comment text,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL,
    log text,
    owner_id bigint
);




--
-- TOC entry 243 (class 1259 OID 17661)

--

CREATE TABLE section_element_link (
    id bigint NOT NULL,
    section_id bigint NOT NULL,
    element_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 244 (class 1259 OID 17665)

--

CREATE SEQUENCE section_element_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3042 (class 0 OID 0)
-- Dependencies: 244

--

ALTER SEQUENCE section_element_link_id_seq OWNED BY section_element_link.id;


--
-- TOC entry 245 (class 1259 OID 17667)

--

CREATE SEQUENCE section_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3043 (class 0 OID 0)
-- Dependencies: 245

--

ALTER SEQUENCE section_id_seq OWNED BY section.id;


--
-- TOC entry 246 (class 1259 OID 17669)

--

CREATE TABLE section_line (
    id bigint NOT NULL,
    html_position_x1 bigint NOT NULL,
    html_position_x2 bigint NOT NULL,
    html_position_y1 bigint NOT NULL,
    html_position_y2 bigint NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 247 (class 1259 OID 17675)

--

CREATE SEQUENCE section_line_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3044 (class 0 OID 0)
-- Dependencies: 247

--

ALTER SEQUENCE section_line_id_seq OWNED BY section_line.id;


--
-- TOC entry 248 (class 1259 OID 17677)

--

CREATE TABLE section_section_line_link (
    id bigint NOT NULL,
    section_id bigint NOT NULL,
    section_line_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 249 (class 1259 OID 17681)

--

CREATE SEQUENCE section_section_line_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3045 (class 0 OID 0)
-- Dependencies: 249

--

ALTER SEQUENCE section_section_line_link_id_seq OWNED BY section_section_line_link.id;


--
-- TOC entry 250 (class 1259 OID 17683)

--

CREATE TABLE status (
    id bigint NOT NULL,
    name text NOT NULL
);




--
-- TOC entry 251 (class 1259 OID 17689)

--

CREATE SEQUENCE status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3046 (class 0 OID 0)
-- Dependencies: 251

--

ALTER SEQUENCE status_id_seq OWNED BY status.id;


--
-- TOC entry 252 (class 1259 OID 17691)

--

CREATE TABLE step_log (
    message text NOT NULL,
    id bigint NOT NULL,
    level_id bigint DEFAULT 1 NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL
);




--
-- TOC entry 253 (class 1259 OID 17701)

--

CREATE SEQUENCE step_log_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3047 (class 0 OID 0)
-- Dependencies: 253

--

ALTER SEQUENCE step_log_id_seq OWNED BY step_log.id;


--
-- TOC entry 254 (class 1259 OID 17703)

--

CREATE TABLE step_option (
    id bigint NOT NULL,
    name text NOT NULL,
    value text,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    preset boolean DEFAULT false
);




--
-- TOC entry 255 (class 1259 OID 17712)

--

CREATE SEQUENCE step_option_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3048 (class 0 OID 0)
-- Dependencies: 255

--

ALTER SEQUENCE step_option_id_seq OWNED BY step_option.id;


--
-- TOC entry 256 (class 1259 OID 17714)

--

CREATE TABLE storage (
    id bigint NOT NULL,
    name text NOT NULL,
    parameter json,
    updated_at timestamp with time zone DEFAULT now(),
    created_at timestamp with time zone DEFAULT now(),
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 257 (class 1259 OID 17723)

--

CREATE SEQUENCE storage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3049 (class 0 OID 0)
-- Dependencies: 257

--

ALTER SEQUENCE storage_id_seq OWNED BY storage.id;


--
-- TOC entry 258 (class 1259 OID 17725)

--

CREATE TABLE test_case (
    id bigint NOT NULL,
    name text NOT NULL,
    created_at timestamp with time zone DEFAULT now(),
    updated_at timestamp with time zone DEFAULT now(),
    flag boolean DEFAULT false,
    result_status bigint,
    comment text,
    log text,
    status_id bigint,
    active boolean DEFAULT true NOT NULL,
    owner_id bigint,
    group_id bigint
);




--
-- TOC entry 286 (class 1259 OID 22514)

--

CREATE TABLE test_case_ems_task_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    task_id bigint,
    task_uuid uuid
);




--
-- TOC entry 285 (class 1259 OID 22512)

--

CREATE SEQUENCE test_case_ems_task_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3050 (class 0 OID 0)
-- Dependencies: 285

--

ALTER SEQUENCE test_case_ems_task_link_id_seq OWNED BY test_case_ems_task_link.id;


--
-- TOC entry 259 (class 1259 OID 17735)

--

CREATE TABLE test_case_engine_link (
    id bigint NOT NULL,
    engine_id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 260 (class 1259 OID 17739)

--

CREATE SEQUENCE test_case_engine_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3051 (class 0 OID 0)
-- Dependencies: 260

--

ALTER SEQUENCE test_case_engine_link_id_seq OWNED BY test_case_engine_link.id;


--
-- TOC entry 261 (class 1259 OID 17741)

--

CREATE TABLE test_case_environment_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    environment_id bigint NOT NULL,
    active boolean NOT NULL
);




--
-- TOC entry 262 (class 1259 OID 17744)

--

CREATE SEQUENCE test_case_environment_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3052 (class 0 OID 0)
-- Dependencies: 262

--

ALTER SEQUENCE test_case_environment_link_id_seq OWNED BY test_case_environment_link.id;


--
-- TOC entry 263 (class 1259 OID 17746)

--

CREATE TABLE test_case_folder (
    id bigint NOT NULL,
    name text NOT NULL,
    comment text,
    updated_at timestamp with time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    created_at timestamp with time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    active boolean DEFAULT true NOT NULL,
    log text,
    type_id bigint,
    owner_id bigint
);




--
-- TOC entry 264 (class 1259 OID 17755)

--

CREATE SEQUENCE test_case_folder_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3053 (class 0 OID 0)
-- Dependencies: 264

--

ALTER SEQUENCE test_case_folder_id_seq OWNED BY test_case_folder.id;


--
-- TOC entry 265 (class 1259 OID 17757)

--

CREATE TABLE test_case_folder_test_case_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL,
    test_case_folder_id bigint
);




--
-- TOC entry 266 (class 1259 OID 17761)

--

CREATE SEQUENCE test_case_folder_test_case_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3054 (class 0 OID 0)
-- Dependencies: 266

--

ALTER SEQUENCE test_case_folder_test_case_link_id_seq OWNED BY test_case_folder_test_case_link.id;


--
-- TOC entry 267 (class 1259 OID 17763)

--

CREATE SEQUENCE test_case_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3055 (class 0 OID 0)
-- Dependencies: 267

--

ALTER SEQUENCE test_case_id_seq OWNED BY test_case.id;


--
-- TOC entry 268 (class 1259 OID 17765)

--

CREATE TABLE test_case_instruction_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    instruction_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 269 (class 1259 OID 17769)

--

CREATE SEQUENCE test_case_instruction_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3056 (class 0 OID 0)
-- Dependencies: 269

--

ALTER SEQUENCE test_case_instruction_link_id_seq OWNED BY test_case_instruction_link.id;


--
-- TOC entry 270 (class 1259 OID 17771)

--

CREATE TABLE test_case_option (
    name text,
    value text,
    id bigint NOT NULL,
    updated_at timestamp with time zone DEFAULT now() NOT NULL,
    created_at timestamp with time zone DEFAULT now() NOT NULL,
    comment text
);




--
-- TOC entry 271 (class 1259 OID 17780)

--

CREATE SEQUENCE test_case_option_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3057 (class 0 OID 0)
-- Dependencies: 271

--

ALTER SEQUENCE test_case_option_id_seq OWNED BY test_case_option.id;


--
-- TOC entry 272 (class 1259 OID 17782)

--

CREATE TABLE test_case_run_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    run_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 273 (class 1259 OID 17786)

--

CREATE SEQUENCE test_case_run_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3058 (class 0 OID 0)
-- Dependencies: 273

--

ALTER SEQUENCE test_case_run_link_id_seq OWNED BY test_case_run_link.id;


--
-- TOC entry 274 (class 1259 OID 17788)

--

CREATE TABLE test_case_storage_link (
    id bigint NOT NULL,
    test_case_id bigint NOT NULL,
    storage_id bigint NOT NULL,
    active boolean DEFAULT true NOT NULL
);




--
-- TOC entry 275 (class 1259 OID 17792)

--

CREATE SEQUENCE test_case_storage_link_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3059 (class 0 OID 0)
-- Dependencies: 275

--

ALTER SEQUENCE test_case_storage_link_id_seq OWNED BY test_case_storage_link.id;


--
-- TOC entry 295 (class 1259 OID 24460)

--

CREATE TABLE "user" (
    id bigint NOT NULL,
    name text,
    uuid bigint,
    element_type_id bigint
);




--
-- TOC entry 294 (class 1259 OID 24458)

--

CREATE SEQUENCE user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;




--
-- TOC entry 3060 (class 0 OID 0)
-- Dependencies: 294

--

ALTER SEQUENCE user_id_seq OWNED BY "user".id;


--
-- TOC entry 2418 (class 2604 OID 17794)

--

ALTER TABLE ONLY application ALTER COLUMN id SET DEFAULT nextval('application_id_seq'::regclass);


--
-- TOC entry 2422 (class 2604 OID 17795)

--

ALTER TABLE ONLY application_section_link ALTER COLUMN id SET DEFAULT nextval('application_section_link_id_seq'::regclass);


--
-- TOC entry 2541 (class 2604 OID 20523)

--

ALTER TABLE ONLY atm_object_type ALTER COLUMN id SET DEFAULT nextval('atm_object_type_id_seq'::regclass);


--
-- TOC entry 2424 (class 2604 OID 17796)

--

ALTER TABLE ONLY element ALTER COLUMN id SET DEFAULT nextval('element_id_seq'::regclass);


--
-- TOC entry 2427 (class 2604 OID 17797)

--

ALTER TABLE ONLY element_action ALTER COLUMN id SET DEFAULT nextval('element_action_id_seq'::regclass);


--
-- TOC entry 2429 (class 2604 OID 17798)

--

ALTER TABLE ONLY element_element_type_link ALTER COLUMN id SET DEFAULT nextval('element_element_type_link_id_seq'::regclass);


--
-- TOC entry 2538 (class 2604 OID 18557)

--

ALTER TABLE ONLY element_locator_type ALTER COLUMN id SET DEFAULT nextval('element_locator_type_id_seq'::regclass);


--
-- TOC entry 2430 (class 2604 OID 17799)

--

ALTER TABLE ONLY element_type ALTER COLUMN id SET DEFAULT nextval('element_type_id_seq'::regclass);


--
-- TOC entry 2431 (class 2604 OID 18444)

--

ALTER TABLE ONLY element_type_element_action_link ALTER COLUMN id SET DEFAULT nextval('element_type_element_action_link_id_seq'::regclass);


--
-- TOC entry 2539 (class 2604 OID 18570)

--

ALTER TABLE ONLY element_type_element_locator_type_link ALTER COLUMN id SET DEFAULT nextval('element_type_element_locator_type_link_id_seq'::regclass);


--
-- TOC entry 2432 (class 2604 OID 17800)

--

ALTER TABLE ONLY engine ALTER COLUMN id SET DEFAULT nextval('engine_id_seq'::regclass);


--
-- TOC entry 2436 (class 2604 OID 17801)

--

ALTER TABLE ONLY engine_module_property_link ALTER COLUMN id SET DEFAULT nextval('engine_module_property_link_id_seq'::regclass);


--
-- TOC entry 2437 (class 2604 OID 17802)

--

ALTER TABLE ONLY environment ALTER COLUMN id SET DEFAULT nextval('environment_id_seq'::regclass);


--
-- TOC entry 2438 (class 2604 OID 17803)

--

ALTER TABLE ONLY error_code ALTER COLUMN id SET DEFAULT nextval('error_code_id_seq'::regclass);


--
-- TOC entry 2445 (class 2604 OID 17805)

--

ALTER TABLE ONLY instruction ALTER COLUMN id SET DEFAULT nextval('instruction_id_seq'::regclass);


--
-- TOC entry 2451 (class 2604 OID 17806)

--

ALTER TABLE ONLY instruction_result ALTER COLUMN id SET DEFAULT nextval('instruction_result_id_seq'::regclass);


--
-- TOC entry 2447 (class 2604 OID 17807)

--

ALTER TABLE ONLY instruction_result_file_link ALTER COLUMN id SET DEFAULT nextval('instruction_result_file_link_id_seq'::regclass);


--
-- TOC entry 2454 (class 2604 OID 17808)

--

ALTER TABLE ONLY instruction_result_step_log_link ALTER COLUMN id SET DEFAULT nextval('instruction_result_step_log_link_id_seq'::regclass);


--
-- TOC entry 2456 (class 2604 OID 17809)

--

ALTER TABLE ONLY instruction_step_option_link ALTER COLUMN id SET DEFAULT nextval('instruction_step_option_link_id_seq'::regclass);


--
-- TOC entry 2458 (class 2604 OID 17810)

--

ALTER TABLE ONLY instruction_test_case_option_link ALTER COLUMN id SET DEFAULT nextval('instruction_test_case_option_link_id_seq'::regclass);


--
-- TOC entry 2459 (class 2604 OID 17811)

--

ALTER TABLE ONLY log_level ALTER COLUMN id SET DEFAULT nextval('log_level_id_seq'::regclass);


--
-- TOC entry 2463 (class 2604 OID 17812)

--

ALTER TABLE ONLY module ALTER COLUMN id SET DEFAULT nextval('module_id_seq'::regclass);


--
-- TOC entry 2465 (class 2604 OID 17813)

--

ALTER TABLE ONLY module_module_property_link ALTER COLUMN id SET DEFAULT nextval('module_module_property_link_id_seq'::regclass);


--
-- TOC entry 2468 (class 2604 OID 17814)

--

ALTER TABLE ONLY module_property ALTER COLUMN id SET DEFAULT nextval('run_module_property_id_seq'::regclass);


--
-- TOC entry 2472 (class 2604 OID 17815)

--

ALTER TABLE ONLY project ALTER COLUMN id SET DEFAULT nextval('project_id_seq'::regclass);


--
-- TOC entry 2474 (class 2604 OID 17816)

--

ALTER TABLE ONLY project_application_link ALTER COLUMN id SET DEFAULT nextval('project_application_link_id_seq'::regclass);


--
-- TOC entry 2542 (class 2604 OID 20551)

--

ALTER TABLE ONLY project_object_type_link ALTER COLUMN id SET DEFAULT nextval('project_object_type_link_id_seq'::regclass);


--
-- TOC entry 2476 (class 2604 OID 17817)

--

ALTER TABLE ONLY project_test_case_link ALTER COLUMN id SET DEFAULT nextval('project_test_case_link_id_seq'::regclass);


--
-- TOC entry 2546 (class 2604 OID 23453)

--

ALTER TABLE ONLY properties ALTER COLUMN id SET DEFAULT nextval('properties_id_seq'::regclass);


--
-- TOC entry 2480 (class 2604 OID 17818)

--

ALTER TABLE ONLY run ALTER COLUMN id SET DEFAULT nextval('run_id_seq'::regclass);


--
-- TOC entry 2483 (class 2604 OID 17820)

--

ALTER TABLE ONLY run_instruction_result_link ALTER COLUMN id SET DEFAULT nextval('run_instruction_result_link_id_seq'::regclass);


--
-- TOC entry 2487 (class 2604 OID 17821)

--

ALTER TABLE ONLY run_set ALTER COLUMN id SET DEFAULT nextval('run_set_id_seq'::regclass);


--
-- TOC entry 2545 (class 2604 OID 22530)

--

ALTER TABLE ONLY run_set_ems_job_link ALTER COLUMN id SET DEFAULT nextval('run_set_ems_job_link_id_seq'::regclass);


--
-- TOC entry 2489 (class 2604 OID 17822)

--

ALTER TABLE ONLY run_set_test_case_link ALTER COLUMN id SET DEFAULT nextval('run_set_test_case_link_id_seq'::regclass);


--
-- TOC entry 2493 (class 2604 OID 17823)

--

ALTER TABLE ONLY section ALTER COLUMN id SET DEFAULT nextval('section_id_seq'::regclass);


--
-- TOC entry 2495 (class 2604 OID 17824)

--

ALTER TABLE ONLY section_element_link ALTER COLUMN id SET DEFAULT nextval('section_element_link_id_seq'::regclass);


--
-- TOC entry 2499 (class 2604 OID 17825)

--

ALTER TABLE ONLY section_line ALTER COLUMN id SET DEFAULT nextval('section_line_id_seq'::regclass);


--
-- TOC entry 2501 (class 2604 OID 17826)

--

ALTER TABLE ONLY section_section_line_link ALTER COLUMN id SET DEFAULT nextval('section_section_line_link_id_seq'::regclass);


--
-- TOC entry 2502 (class 2604 OID 17827)

--

ALTER TABLE ONLY status ALTER COLUMN id SET DEFAULT nextval('status_id_seq'::regclass);


--
-- TOC entry 2505 (class 2604 OID 17828)

--

ALTER TABLE ONLY step_log ALTER COLUMN id SET DEFAULT nextval('step_log_id_seq'::regclass);


--
-- TOC entry 2509 (class 2604 OID 17829)

--

ALTER TABLE ONLY step_option ALTER COLUMN id SET DEFAULT nextval('step_option_id_seq'::regclass);


--
-- TOC entry 2514 (class 2604 OID 17830)

--

ALTER TABLE ONLY storage ALTER COLUMN id SET DEFAULT nextval('storage_id_seq'::regclass);


--
-- TOC entry 2519 (class 2604 OID 17831)

--

ALTER TABLE ONLY test_case ALTER COLUMN id SET DEFAULT nextval('test_case_id_seq'::regclass);


--
-- TOC entry 2544 (class 2604 OID 22517)

--

ALTER TABLE ONLY test_case_ems_task_link ALTER COLUMN id SET DEFAULT nextval('test_case_ems_task_link_id_seq'::regclass);


--
-- TOC entry 2521 (class 2604 OID 17832)

--

ALTER TABLE ONLY test_case_engine_link ALTER COLUMN id SET DEFAULT nextval('test_case_engine_link_id_seq'::regclass);


--
-- TOC entry 2522 (class 2604 OID 17833)

--

ALTER TABLE ONLY test_case_environment_link ALTER COLUMN id SET DEFAULT nextval('test_case_environment_link_id_seq'::regclass);


--
-- TOC entry 2524 (class 2604 OID 17834)

--

ALTER TABLE ONLY test_case_folder ALTER COLUMN id SET DEFAULT nextval('test_case_folder_id_seq'::regclass);


--
-- TOC entry 2528 (class 2604 OID 17835)

--

ALTER TABLE ONLY test_case_folder_test_case_link ALTER COLUMN id SET DEFAULT nextval('test_case_folder_test_case_link_id_seq'::regclass);


--
-- TOC entry 2530 (class 2604 OID 17836)

--

ALTER TABLE ONLY test_case_instruction_link ALTER COLUMN id SET DEFAULT nextval('test_case_instruction_link_id_seq'::regclass);


--
-- TOC entry 2533 (class 2604 OID 17837)

--

ALTER TABLE ONLY test_case_option ALTER COLUMN id SET DEFAULT nextval('test_case_option_id_seq'::regclass);


--
-- TOC entry 2535 (class 2604 OID 17838)

--

ALTER TABLE ONLY test_case_run_link ALTER COLUMN id SET DEFAULT nextval('test_case_run_link_id_seq'::regclass);


--
-- TOC entry 2537 (class 2604 OID 17839)

--

ALTER TABLE ONLY test_case_storage_link ALTER COLUMN id SET DEFAULT nextval('test_case_storage_link_id_seq'::regclass);


--
-- TOC entry 2551 (class 2604 OID 24463)

--

ALTER TABLE ONLY "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq'::regclass);


--
-- TOC entry 2553 (class 2606 OID 17883)

--

ALTER TABLE ONLY application
    ADD CONSTRAINT application_pkey PRIMARY KEY (id);


--
-- TOC entry 2555 (class 2606 OID 17885)

--

ALTER TABLE ONLY application_section_link
    ADD CONSTRAINT application_section_ukey UNIQUE (application_id, section_id);


--
-- TOC entry 2557 (class 2606 OID 17887)

--

ALTER TABLE ONLY application_section_link
    ADD CONSTRAINT application_to_section_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2561 (class 2606 OID 17889)

--

ALTER TABLE ONLY element_action
    ADD CONSTRAINT element_action_name_ukey UNIQUE (name);


--
-- TOC entry 2563 (class 2606 OID 17891)

--

ALTER TABLE ONLY element_action
    ADD CONSTRAINT element_action_pkey PRIMARY KEY (id);


--
-- TOC entry 2573 (class 2606 OID 18450)

--

ALTER TABLE ONLY element_type_element_action_link
    ADD CONSTRAINT element_element_action_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2565 (class 2606 OID 17897)

--

ALTER TABLE ONLY element_element_type_link
    ADD CONSTRAINT element_element_type_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2567 (class 2606 OID 17893)

--

ALTER TABLE ONLY element_element_type_link
    ADD CONSTRAINT element_element_type_ukey UNIQUE (element_id, element_type_id);


--
-- TOC entry 2723 (class 2606 OID 18564)

--

ALTER TABLE ONLY element_locator_type
    ADD CONSTRAINT element_locator_type_name_ukey UNIQUE (name);


--
-- TOC entry 2725 (class 2606 OID 18562)

--

ALTER TABLE ONLY element_locator_type
    ADD CONSTRAINT element_locator_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2559 (class 2606 OID 17895)

--

ALTER TABLE ONLY element
    ADD CONSTRAINT element_pkey PRIMARY KEY (id);


--
-- TOC entry 2575 (class 2606 OID 17901)

--

ALTER TABLE ONLY element_type_element_action_link
    ADD CONSTRAINT element_type_element_action_ukey UNIQUE (element_type_id, element_action_id);


--
-- TOC entry 2727 (class 2606 OID 18573)

--

ALTER TABLE ONLY element_type_element_locator_type_link
    ADD CONSTRAINT element_type_element_locator_type_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2729 (class 2606 OID 18575)

--

ALTER TABLE ONLY element_type_element_locator_type_link
    ADD CONSTRAINT element_type_element_locator_type_ukey UNIQUE (element_type_id, element_locator_type_id);


--
-- TOC entry 2569 (class 2606 OID 17903)

--

ALTER TABLE ONLY element_type
    ADD CONSTRAINT element_type_name_ukey UNIQUE (name);


--
-- TOC entry 2571 (class 2606 OID 17905)

--

ALTER TABLE ONLY element_type
    ADD CONSTRAINT element_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2581 (class 2606 OID 17907)

--

ALTER TABLE ONLY engine_module_property_link
    ADD CONSTRAINT engine_module_property_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2583 (class 2606 OID 17909)

--

ALTER TABLE ONLY engine_module_property_link
    ADD CONSTRAINT engine_module_property_ukey UNIQUE (engine_id, module_property_id);


--
-- TOC entry 2577 (class 2606 OID 17911)

--

ALTER TABLE ONLY engine
    ADD CONSTRAINT engine_name_ukey UNIQUE (name);


--
-- TOC entry 2579 (class 2606 OID 17913)

--

ALTER TABLE ONLY engine
    ADD CONSTRAINT engine_pkey PRIMARY KEY (id);


--
-- TOC entry 2629 (class 2606 OID 17915)

--

ALTER TABLE ONLY module_property
    ADD CONSTRAINT engine_property_pkey PRIMARY KEY (id);


--
-- TOC entry 2585 (class 2606 OID 17917)

--

ALTER TABLE ONLY environment
    ADD CONSTRAINT env_name_value_ukey UNIQUE (name, value);


--
-- TOC entry 2587 (class 2606 OID 17919)

--

ALTER TABLE ONLY environment
    ADD CONSTRAINT enviroment_pkey PRIMARY KEY (id);


--
-- TOC entry 2589 (class 2606 OID 17921)

--

ALTER TABLE ONLY error_code
    ADD CONSTRAINT error_code_pkey PRIMARY KEY (id);


--
-- TOC entry 2591 (class 2606 OID 17923)

--

ALTER TABLE ONLY error_code
    ADD CONSTRAINT error_code_uk_name UNIQUE (name);


--
-- TOC entry 2593 (class 2606 OID 20393)

--

ALTER TABLE ONLY file
    ADD CONSTRAINT file_pkey PRIMARY KEY (uuid);


--
-- TOC entry 2701 (class 2606 OID 17927)

--

ALTER TABLE ONLY test_case_folder_test_case_link
    ADD CONSTRAINT folder_to_test_case_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2744 (class 2606 OID 24437)

--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_name_key UNIQUE (name);


--
-- TOC entry 2746 (class 2606 OID 24439)

--

ALTER TABLE ONLY "group"
    ADD CONSTRAINT group_pkey PRIMARY KEY (id);


--
-- TOC entry 2595 (class 2606 OID 17929)

--

ALTER TABLE ONLY instruction
    ADD CONSTRAINT instruction_pkey PRIMARY KEY (id);


--
-- TOC entry 2605 (class 2606 OID 17933)

--

ALTER TABLE ONLY instruction_result_step_log_link
    ADD CONSTRAINT instruction_result_step_log_ukey UNIQUE (instruction_result_id, step_log_id);


--
-- TOC entry 2611 (class 2606 OID 17935)

--

ALTER TABLE ONLY instruction_step_option_link
    ADD CONSTRAINT instruction_step_option UNIQUE (instruction_id, step_option_id);


--
-- TOC entry 2617 (class 2606 OID 17937)

--

ALTER TABLE ONLY instruction_test_case_option_link
    ADD CONSTRAINT instruction_test_case_option_ukey UNIQUE (instruction_id, test_case_option_id);


--
-- TOC entry 2598 (class 2606 OID 17939)

--

ALTER TABLE ONLY instruction_result_file_link
    ADD CONSTRAINT instruction_to_file_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2619 (class 2606 OID 17941)

--

ALTER TABLE ONLY instruction_test_case_option_link
    ADD CONSTRAINT instruction_to_logic_option_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2613 (class 2606 OID 17943)

--

ALTER TABLE ONLY instruction_step_option_link
    ADD CONSTRAINT instruction_to_step_option_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2678 (class 2606 OID 17945)

--

ALTER TABLE ONLY step_log
    ADD CONSTRAINT log_entry_pkey PRIMARY KEY (id);


--
-- TOC entry 2621 (class 2606 OID 17947)

--

ALTER TABLE ONLY log_level
    ADD CONSTRAINT log_level_pkey PRIMARY KEY (id);


--
-- TOC entry 2711 (class 2606 OID 17949)

--

ALTER TABLE ONLY test_case_option
    ADD CONSTRAINT logic_option_pkey PRIMARY KEY (id);


--
-- TOC entry 2731 (class 2606 OID 20528)

--

ALTER TABLE ONLY atm_object_type
    ADD CONSTRAINT management_object_type_pkey PRIMARY KEY (id);


--
-- TOC entry 2625 (class 2606 OID 17951)

--

ALTER TABLE ONLY module_module_property_link
    ADD CONSTRAINT module_module_property_ukey UNIQUE (module_id, module_property_id);


--
-- TOC entry 2627 (class 2606 OID 17953)

--

ALTER TABLE ONLY module_module_property_link
    ADD CONSTRAINT module_to_module_property_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2631 (class 2606 OID 17957)

--

ALTER TABLE ONLY module_property
    ADD CONSTRAINT name_value_ukey UNIQUE (name, value);


--
-- TOC entry 2623 (class 2606 OID 17959)

--

ALTER TABLE ONLY module
    ADD CONSTRAINT pkey PRIMARY KEY (id);


--
-- TOC entry 2637 (class 2606 OID 17961)

--

ALTER TABLE ONLY project_application_link
    ADD CONSTRAINT project_application_ukey UNIQUE (project_id, application_id);


--
-- TOC entry 2633 (class 2606 OID 17963)

--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_name_ukey UNIQUE (name);


--
-- TOC entry 2733 (class 2606 OID 20554)

--

ALTER TABLE ONLY project_object_type_link
    ADD CONSTRAINT project_object_type_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2635 (class 2606 OID 17965)

--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_pkey PRIMARY KEY (id);


--
-- TOC entry 2641 (class 2606 OID 17967)

--

ALTER TABLE ONLY project_test_case_link
    ADD CONSTRAINT project_test_case_ukey UNIQUE (project_id, test_case_id);


--
-- TOC entry 2639 (class 2606 OID 17969)

--

ALTER TABLE ONLY project_application_link
    ADD CONSTRAINT project_to_application_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2643 (class 2606 OID 17971)

--

ALTER TABLE ONLY project_test_case_link
    ADD CONSTRAINT project_to_test_case_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2739 (class 2606 OID 23458)

--

ALTER TABLE ONLY properties
    ADD CONSTRAINT properties_pkey PRIMARY KEY (id);


--
-- TOC entry 2601 (class 2606 OID 17973)

--

ALTER TABLE ONLY instruction_result
    ADD CONSTRAINT result_pkey PRIMARY KEY (id);


--
-- TOC entry 2607 (class 2606 OID 17975)

--

ALTER TABLE ONLY instruction_result_step_log_link
    ADD CONSTRAINT result_to_log_entry_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2650 (class 2606 OID 17977)

--

ALTER TABLE ONLY run_instruction_result_link
    ADD CONSTRAINT run_instruction_result_ukey UNIQUE (run_id, instruction_result_id);


--
-- TOC entry 2646 (class 2606 OID 17979)

--

ALTER TABLE ONLY run
    ADD CONSTRAINT run_pkey PRIMARY KEY (id);


--
-- TOC entry 2652 (class 2606 OID 17981)

--

ALTER TABLE ONLY run_instruction_result_link
    ADD CONSTRAINT run_result_mapping_pkey PRIMARY KEY (id);


--
-- TOC entry 2689 (class 2606 OID 17983)

--

ALTER TABLE ONLY test_case_engine_link
    ADD CONSTRAINT run_set_engine_ukey UNIQUE (engine_id, test_case_id);


--
-- TOC entry 2737 (class 2606 OID 22532)

--

ALTER TABLE ONLY run_set_ems_job_link
    ADD CONSTRAINT run_set_job_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2655 (class 2606 OID 17985)

--

ALTER TABLE ONLY run_set
    ADD CONSTRAINT run_set_name_ukey UNIQUE (name);


--
-- TOC entry 2657 (class 2606 OID 17987)

--

ALTER TABLE ONLY run_set
    ADD CONSTRAINT run_set_pkey PRIMARY KEY (id);


--
-- TOC entry 2719 (class 2606 OID 17989)

--

ALTER TABLE ONLY test_case_storage_link
    ADD CONSTRAINT run_set_storage_link_ukey UNIQUE (test_case_id, storage_id);


--
-- TOC entry 2659 (class 2606 OID 17991)

--

ALTER TABLE ONLY run_set_test_case_link
    ADD CONSTRAINT run_set_test_case_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2661 (class 2606 OID 17993)

--

ALTER TABLE ONLY run_set_test_case_link
    ADD CONSTRAINT run_set_test_case_ukey UNIQUE (run_set_id, test_case_id);

--
-- TOC entry 2665 (class 2606 OID 17997)

--

ALTER TABLE ONLY section_element_link
    ADD CONSTRAINT section_element_ukey UNIQUE (section_id, element_id);


--
-- TOC entry 2669 (class 2606 OID 17999)

--

ALTER TABLE ONLY section_line
    ADD CONSTRAINT section_line_pkey PRIMARY KEY (id);


--
-- TOC entry 2663 (class 2606 OID 18001)

--

ALTER TABLE ONLY section
    ADD CONSTRAINT section_pkey PRIMARY KEY (id);


--
-- TOC entry 2671 (class 2606 OID 18003)

--

ALTER TABLE ONLY section_section_line_link
    ADD CONSTRAINT section_section_line_ukey UNIQUE (section_id, section_line_id);


--
-- TOC entry 2667 (class 2606 OID 18005)

--

ALTER TABLE ONLY section_element_link
    ADD CONSTRAINT section_to_element_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2673 (class 2606 OID 18007)

--

ALTER TABLE ONLY section_section_line_link
    ADD CONSTRAINT section_to_section_line_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2675 (class 2606 OID 18009)

--

ALTER TABLE ONLY status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 2680 (class 2606 OID 18011)

--

ALTER TABLE ONLY step_option
    ADD CONSTRAINT step_option_pkey PRIMARY KEY (id);


--
-- TOC entry 2682 (class 2606 OID 18013)

--

ALTER TABLE ONLY storage
    ADD CONSTRAINT storage_pkey PRIMARY KEY (id);


--
-- TOC entry 2691 (class 2606 OID 18015)

--

ALTER TABLE ONLY test_case_engine_link
    ADD CONSTRAINT test_case_engine_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2693 (class 2606 OID 18017)

--

ALTER TABLE ONLY test_case_environment_link
    ADD CONSTRAINT test_case_environment_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2697 (class 2606 OID 18019)

--

ALTER TABLE ONLY test_case_folder
    ADD CONSTRAINT test_case_folder_name_ukey UNIQUE (name);


--
-- TOC entry 2699 (class 2606 OID 18021)

--

ALTER TABLE ONLY test_case_folder
    ADD CONSTRAINT test_case_folder_pkey PRIMARY KEY (id);


--
-- TOC entry 2695 (class 2606 OID 18023)

--

ALTER TABLE ONLY test_case_environment_link
    ADD CONSTRAINT test_case_id_enviroment_id_ukey UNIQUE (test_case_id, environment_id);


--
-- TOC entry 2707 (class 2606 OID 18025)

--

ALTER TABLE ONLY test_case_instruction_link
    ADD CONSTRAINT test_case_instruction_ukey UNIQUE (test_case_id, instruction_id);


--
-- TOC entry 2685 (class 2606 OID 18027)

--

ALTER TABLE ONLY test_case
    ADD CONSTRAINT test_case_name_ukey UNIQUE (name);


--
-- TOC entry 2687 (class 2606 OID 18029)

--

ALTER TABLE ONLY test_case
    ADD CONSTRAINT test_case_pkey PRIMARY KEY (id);


--
-- TOC entry 2715 (class 2606 OID 18031)

--

ALTER TABLE ONLY test_case_run_link
    ADD CONSTRAINT test_case_run_ukey UNIQUE (test_case_id, run_id);


--
-- TOC entry 2721 (class 2606 OID 18033)

--

ALTER TABLE ONLY test_case_storage_link
    ADD CONSTRAINT test_case_storage_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2735 (class 2606 OID 22519)

--

ALTER TABLE ONLY test_case_ems_task_link
    ADD CONSTRAINT test_case_task_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2703 (class 2606 OID 18035)

--

ALTER TABLE ONLY test_case_folder_test_case_link
    ADD CONSTRAINT test_case_test_case_folder_ukey UNIQUE (test_case_id, test_case_folder_id);


--
-- TOC entry 2709 (class 2606 OID 18037)

--

ALTER TABLE ONLY test_case_instruction_link
    ADD CONSTRAINT test_case_to_instruction_mapping_pkey PRIMARY KEY (id);


--
-- TOC entry 2717 (class 2606 OID 18039)

--

ALTER TABLE ONLY test_case_run_link
    ADD CONSTRAINT test_case_to_run_link_pkey PRIMARY KEY (id);


--
-- TOC entry 2748 (class 2606 OID 24468)

--

ALTER TABLE ONLY "user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- TOC entry 2644 (class 1259 OID 18040)

--

CREATE INDEX fki_execution_fkey_status ON run USING btree (status_id);


--
-- TOC entry 2647 (class 1259 OID 18045)

--

CREATE INDEX fki_execution_to_result_link_result_fkey ON run_instruction_result_link USING btree (instruction_result_id);


--
-- TOC entry 2648 (class 1259 OID 18046)

--

CREATE INDEX fki_execution_to_result_mapping_execution_fkey ON run_instruction_result_link USING btree (run_id);


--
-- TOC entry 2602 (class 1259 OID 18047)

--

CREATE INDEX fki_instruction_result_to_log_entry_fkey_instruction_result ON instruction_result_step_log_link USING btree (instruction_result_id);


--
-- TOC entry 2603 (class 1259 OID 18048)

--

CREATE INDEX fki_instruction_result_to_log_entry_fkey_log_entry ON instruction_result_step_log_link USING btree (step_log_id);


--
-- TOC entry 2596 (class 1259 OID 18050)

--

CREATE INDEX fki_instruction_to_file_link_fkey_instruction ON instruction_result_file_link USING btree (instruction_result_id);


--
-- TOC entry 2614 (class 1259 OID 18051)

--

CREATE INDEX fki_instruction_to_logic_option_link_fkey_instruction ON instruction_test_case_option_link USING btree (instruction_id);


--
-- TOC entry 2615 (class 1259 OID 18052)

--

CREATE INDEX fki_instruction_to_logic_option_link_fkey_option_link ON instruction_test_case_option_link USING btree (test_case_option_id);


--
-- TOC entry 2608 (class 1259 OID 18053)

--

CREATE INDEX fki_instruction_to_step_option_link_fkey_instrcution ON instruction_step_option_link USING btree (instruction_id);


--
-- TOC entry 2609 (class 1259 OID 18054)

--

CREATE INDEX fki_instruction_to_step_option_link_fkey_step_option ON instruction_step_option_link USING btree (step_option_id);


--
-- TOC entry 2676 (class 1259 OID 18055)

--

CREATE INDEX fki_log_entry ON step_log USING btree (level_id);


--
-- TOC entry 2599 (class 1259 OID 18056)

--

CREATE INDEX fki_result_fkey_status ON instruction_result USING btree (status_id);


--
-- TOC entry 2653 (class 1259 OID 24457)

--

CREATE INDEX fki_run_set_fkey ON run_set USING btree (group_id);


--
-- TOC entry 2683 (class 1259 OID 24451)

--

CREATE INDEX fki_test_case_group_id_fkey ON test_case USING btree (group_id);


--
-- TOC entry 2704 (class 1259 OID 18057)

--

CREATE INDEX fki_test_case_instruction_fkey2 ON test_case_instruction_link USING btree (instruction_id);


--
-- TOC entry 2712 (class 1259 OID 18058)

--

CREATE INDEX fki_test_case_to_execution_link_fkey_execution ON test_case_run_link USING btree (run_id);


--
-- TOC entry 2713 (class 1259 OID 18059)

--

CREATE INDEX fki_test_case_to_execution_link_fkey_test_case ON test_case_run_link USING btree (test_case_id);


--
-- TOC entry 2705 (class 1259 OID 18060)

--

CREATE INDEX fki_test_case_to_instrcution_fkey1 ON test_case_instruction_link USING btree (test_case_id);

--
-- TOC entry 2828 (class 2620 OID 18519)

--

CREATE TRIGGER finished_status_update BEFORE UPDATE ON instruction_result FOR EACH ROW EXECUTE PROCEDURE finished_update_status();


--
-- TOC entry 2846 (class 2620 OID 18522)

--

CREATE TRIGGER finished_status_update BEFORE UPDATE ON run FOR EACH ROW EXECUTE PROCEDURE finished_update_status();


--
-- TOC entry 2811 (class 2620 OID 18061)

--

CREATE TRIGGER insert_application_created_at_updated_at_active BEFORE INSERT ON application FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2813 (class 2620 OID 18062)

--

CREATE TRIGGER insert_application_section_link_active BEFORE INSERT ON application_section_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2814 (class 2620 OID 18064)

--

CREATE TRIGGER insert_element_created_at_updated_at_active BEFORE INSERT ON element FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2816 (class 2620 OID 18065)

--

CREATE TRIGGER insert_element_element_type_link_active BEFORE INSERT ON element_element_type_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2817 (class 2620 OID 18066)

--

CREATE TRIGGER insert_element_type_element_action_link_active BEFORE INSERT ON element_type_element_action_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2818 (class 2620 OID 18067)

--

CREATE TRIGGER insert_engine_created_at_updated_at_active BEFORE INSERT ON engine FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2820 (class 2620 OID 18068)

--

CREATE TRIGGER insert_engine_module_property_link_active BEFORE INSERT ON engine_module_property_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2821 (class 2620 OID 18069)

--

CREATE TRIGGER insert_file_created_at_updated_at_active BEFORE INSERT ON file FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2823 (class 2620 OID 18070)

--

CREATE TRIGGER insert_instruction_created_at_updated_at_active BEFORE INSERT ON instruction FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2826 (class 2620 OID 18071)

--

CREATE TRIGGER insert_instruction_result_created_at_updated_at_active BEFORE INSERT ON instruction_result FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2825 (class 2620 OID 18072)

--

CREATE TRIGGER insert_instruction_result_file_link_active BEFORE INSERT ON instruction_result_file_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2830 (class 2620 OID 18073)

--

CREATE TRIGGER insert_instruction_result_step_log_link_active BEFORE INSERT ON instruction_result_step_log_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2832 (class 2620 OID 18074)

--

CREATE TRIGGER insert_instruction_step_option_link_active BEFORE INSERT ON instruction_step_option_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2833 (class 2620 OID 18075)

--

CREATE TRIGGER insert_instruction_test_case_option_link_active BEFORE INSERT ON instruction_test_case_option_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2834 (class 2620 OID 18076)

--

CREATE TRIGGER insert_log_level_created_at_updated_at_active BEFORE INSERT ON log_level FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2835 (class 2620 OID 18077)

--

CREATE TRIGGER insert_module_created_at_updated_at_active BEFORE INSERT ON module FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2837 (class 2620 OID 18078)

--

CREATE TRIGGER insert_module_module_property_link_active BEFORE INSERT ON module_module_property_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2838 (class 2620 OID 18079)

--

CREATE TRIGGER insert_module_property_created_at_updated_at_active BEFORE INSERT ON module_property FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2842 (class 2620 OID 18080)

--

CREATE TRIGGER insert_project_application_link_active BEFORE INSERT ON project_application_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2840 (class 2620 OID 18081)

--

CREATE TRIGGER insert_project_created_at_updated_at_active BEFORE INSERT ON project FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2843 (class 2620 OID 18082)

--

CREATE TRIGGER insert_project_test_case_link_active BEFORE INSERT ON project_test_case_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2844 (class 2620 OID 18083)

--

CREATE TRIGGER insert_run_created_at_updated_at BEFORE INSERT ON run FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_column();


--
-- TOC entry 2847 (class 2620 OID 18085)

--

CREATE TRIGGER insert_run_instruction_result_link_active BEFORE INSERT ON run_instruction_result_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2848 (class 2620 OID 18086)

--

CREATE TRIGGER insert_run_set_created_at_updated_at_active BEFORE INSERT ON run_set FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2850 (class 2620 OID 18087)

--

CREATE TRIGGER insert_run_set_test_case_link_active BEFORE INSERT ON run_set_test_case_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2851 (class 2620 OID 18088)

--

CREATE TRIGGER insert_section_created_at_updated_at_active BEFORE INSERT ON section FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2853 (class 2620 OID 18089)

--

CREATE TRIGGER insert_section_element_link_active BEFORE INSERT ON section_element_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2854 (class 2620 OID 18090)

--

CREATE TRIGGER insert_section_line_created_at_updated_at_active BEFORE INSERT ON section_line FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2856 (class 2620 OID 18091)

--

CREATE TRIGGER insert_section_section_line_link_active BEFORE INSERT ON section_section_line_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2857 (class 2620 OID 18092)

--

CREATE TRIGGER insert_step_log_created_at_updated_at_active BEFORE INSERT ON step_log FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2831 (class 2620 OID 18503)

--

CREATE TRIGGER insert_step_log_update_instruction_result_status AFTER INSERT ON instruction_result_step_log_link FOR EACH ROW EXECUTE PROCEDURE step_log_update_instruction_result_status();


--
-- TOC entry 2859 (class 2620 OID 18093)

--

CREATE TRIGGER insert_step_option_created_at_updated_at_active BEFORE INSERT ON step_option FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2861 (class 2620 OID 18094)

--

CREATE TRIGGER insert_storage_created_at_updated_at_active BEFORE INSERT ON storage FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2863 (class 2620 OID 18095)

--

CREATE TRIGGER insert_test_case_created_at_updated_at_active BEFORE INSERT ON test_case FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2865 (class 2620 OID 18096)

--

CREATE TRIGGER insert_test_case_engine_link_active BEFORE INSERT ON test_case_engine_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2866 (class 2620 OID 18097)

--

CREATE TRIGGER insert_test_case_folder_created_at_updated_at_active BEFORE INSERT ON test_case_folder FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2868 (class 2620 OID 18098)

--

CREATE TRIGGER insert_test_case_folder_test_case_link_active BEFORE INSERT ON test_case_folder_test_case_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2869 (class 2620 OID 18099)

--

CREATE TRIGGER insert_test_case_instruction_link_active BEFORE INSERT ON test_case_instruction_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2870 (class 2620 OID 18100)

--

CREATE TRIGGER insert_test_case_option_created_at_updated_at_active BEFORE INSERT ON test_case_option FOR EACH ROW EXECUTE PROCEDURE insert_updated_at_created_at_active_column();


--
-- TOC entry 2872 (class 2620 OID 18101)

--

CREATE TRIGGER insert_test_case_run_link_active BEFORE INSERT ON test_case_run_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2873 (class 2620 OID 18102)

--

CREATE TRIGGER insert_test_case_storage_link_active BEFORE INSERT ON test_case_storage_link FOR EACH ROW EXECUTE PROCEDURE insert_active_column();


--
-- TOC entry 2812 (class 2620 OID 18103)

--

CREATE TRIGGER update_application_created_at_updated_at BEFORE UPDATE ON application FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2815 (class 2620 OID 18104)

--

CREATE TRIGGER update_element_created_at_updated_at BEFORE UPDATE ON element FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2819 (class 2620 OID 18105)

--

CREATE TRIGGER update_engine_created_at_updated_at BEFORE UPDATE ON engine FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2822 (class 2620 OID 18106)

--

CREATE TRIGGER update_file_created_at_updated_at BEFORE UPDATE ON file FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2824 (class 2620 OID 18107)

--

CREATE TRIGGER update_instruction_created_at_updated_at BEFORE UPDATE ON instruction FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2827 (class 2620 OID 18108)

--

CREATE TRIGGER update_instruction_result_created_at_updated_at BEFORE UPDATE ON instruction_result FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2836 (class 2620 OID 18109)

--

CREATE TRIGGER update_module_created_at_updated_at BEFORE UPDATE ON module FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2839 (class 2620 OID 18110)

--

CREATE TRIGGER update_module_property_created_at_updated_at BEFORE UPDATE ON module_property FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2841 (class 2620 OID 18111)

--

CREATE TRIGGER update_project_created_at_updated_at BEFORE UPDATE ON project FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2845 (class 2620 OID 18112)

--

CREATE TRIGGER update_run_created_at_updated_at BEFORE UPDATE ON run FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2849 (class 2620 OID 18113)

--

CREATE TRIGGER update_run_set_created_at_updated_at BEFORE UPDATE ON run_set FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2829 (class 2620 OID 18534)

--

CREATE TRIGGER update_run_status AFTER UPDATE ON instruction_result FOR EACH ROW EXECUTE PROCEDURE instruction_result_update_run_status();


--
-- TOC entry 2852 (class 2620 OID 18114)

--

CREATE TRIGGER update_section_created_at_updated_at BEFORE UPDATE ON section FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2855 (class 2620 OID 18115)

--

CREATE TRIGGER update_section_line_created_at_updated_at BEFORE UPDATE ON section_line FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2858 (class 2620 OID 18116)

--

CREATE TRIGGER update_step_log_created_at_updated_at BEFORE UPDATE ON step_log FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2860 (class 2620 OID 18117)

--

CREATE TRIGGER update_step_option_created_at_updated_at BEFORE UPDATE ON step_option FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2862 (class 2620 OID 18118)

--

CREATE TRIGGER update_storage_created_at_updated_at BEFORE UPDATE ON storage FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2864 (class 2620 OID 18119)

--

CREATE TRIGGER update_test_case_created_at_updated_at BEFORE UPDATE ON test_case FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2867 (class 2620 OID 18120)

--

CREATE TRIGGER update_test_case_folder_created_at_updated_at BEFORE UPDATE ON test_case_folder FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2871 (class 2620 OID 18121)

--

CREATE TRIGGER update_test_case_option_created_at_updated_at BEFORE UPDATE ON test_case_option FOR EACH ROW EXECUTE PROCEDURE update_created_at_updated_at_column();


--
-- TOC entry 2764 (class 2606 OID 18127)

--

ALTER TABLE ONLY instruction_result_file_link
    ADD CONSTRAINT "		instruction_result_to_file_link_fkey_instruction_result" FOREIGN KEY (instruction_result_id) REFERENCES instruction_result(id);


--
-- TOC entry 2767 (class 2606 OID 18132)

--

ALTER TABLE ONLY instruction_result_step_log_link
    ADD CONSTRAINT "	instruction_result_to_step_log_fkey_instruction_result" FOREIGN KEY (step_log_id) REFERENCES step_log(id);


--
-- TOC entry 2768 (class 2606 OID 18137)

--

ALTER TABLE ONLY instruction_result_step_log_link
    ADD CONSTRAINT "	instruction_result_to_step_log_fkey_step_log" FOREIGN KEY (instruction_result_id) REFERENCES instruction_result(id);


--
-- TOC entry 2788 (class 2606 OID 18142)

--

ALTER TABLE ONLY section_section_line_link
    ADD CONSTRAINT "	section_to_section_line_fkey_section_line_id" FOREIGN KEY (section_line_id) REFERENCES section_line(id);


--
-- TOC entry 2749 (class 2606 OID 18152)

--

ALTER TABLE ONLY application_section_link
    ADD CONSTRAINT application_to_sction_link_application_fkey FOREIGN KEY (application_id) REFERENCES application(id);


--
-- TOC entry 2750 (class 2606 OID 18157)

--

ALTER TABLE ONLY application_section_link
    ADD CONSTRAINT application_to_sction_link_section_fkey FOREIGN KEY (section_id) REFERENCES section(id);


--
-- TOC entry 2752 (class 2606 OID 20425)

--

ALTER TABLE ONLY element
    ADD CONSTRAINT element_locator_type_id FOREIGN KEY (element_locator_type_id) REFERENCES element_locator_type(id);


--
-- TOC entry 2753 (class 2606 OID 18162)

--

ALTER TABLE ONLY element_element_type_link
    ADD CONSTRAINT element_to_element_type_link_element FOREIGN KEY (element_id) REFERENCES element(id);


--
-- TOC entry 2754 (class 2606 OID 18167)

--

ALTER TABLE ONLY element_element_type_link
    ADD CONSTRAINT element_to_element_type_link_element_type FOREIGN KEY (element_type_id) REFERENCES element_type(id);


--
-- TOC entry 2806 (class 2606 OID 18581)

--

ALTER TABLE ONLY element_type_element_locator_type_link
    ADD CONSTRAINT element_tyep_element_locator_type_fkey_element_locator_type FOREIGN KEY (element_locator_type_id) REFERENCES element_locator_type(id);


--
-- TOC entry 2805 (class 2606 OID 18576)

--

ALTER TABLE ONLY element_type_element_locator_type_link
    ADD CONSTRAINT element_tyep_element_locator_type_fkey_element_type FOREIGN KEY (element_type_id) REFERENCES element_type(id);


--
-- TOC entry 2755 (class 2606 OID 18172)

--

ALTER TABLE ONLY element_type_element_action_link
    ADD CONSTRAINT element_type_element_action_link_element_action FOREIGN KEY (element_action_id) REFERENCES element_action(id);


--
-- TOC entry 2756 (class 2606 OID 18177)

--

ALTER TABLE ONLY element_type_element_action_link
    ADD CONSTRAINT element_type_element_action_link_element_type FOREIGN KEY (element_type_id) REFERENCES element_type(id);


--
-- TOC entry 2751 (class 2606 OID 18730)

--

ALTER TABLE ONLY element
    ADD CONSTRAINT element_type_id_fkey FOREIGN KEY (element_type_id) REFERENCES element_type(id);


--
-- TOC entry 2757 (class 2606 OID 18182)

--

ALTER TABLE ONLY engine_module_property_link
    ADD CONSTRAINT engine_fkey FOREIGN KEY (engine_id) REFERENCES engine(id);


--
-- TOC entry 2793 (class 2606 OID 18187)

--

ALTER TABLE ONLY test_case_engine_link
    ADD CONSTRAINT engine_fkey FOREIGN KEY (engine_id) REFERENCES engine(id);


--
-- TOC entry 2796 (class 2606 OID 18396)

--

ALTER TABLE ONLY test_case_environment_link
    ADD CONSTRAINT environment_fkey FOREIGN KEY (environment_id) REFERENCES environment(id);


--
-- TOC entry 2759 (class 2606 OID 20539)

--

ALTER TABLE ONLY file
    ADD CONSTRAINT file_fk_type FOREIGN KEY (type_id) REFERENCES atm_object_type(id);


--
-- TOC entry 2763 (class 2606 OID 20504)

--

ALTER TABLE ONLY instruction
    ADD CONSTRAINT instruction_fkey_application FOREIGN KEY (application_id) REFERENCES application(id);


--
-- TOC entry 2760 (class 2606 OID 18192)

--

ALTER TABLE ONLY instruction
    ADD CONSTRAINT instruction_fkey_element FOREIGN KEY (element_id) REFERENCES element(id);


--
-- TOC entry 2761 (class 2606 OID 18197)

--

ALTER TABLE ONLY instruction
    ADD CONSTRAINT instruction_fkey_project FOREIGN KEY (project_id) REFERENCES project(id);


--
-- TOC entry 2762 (class 2606 OID 18202)

--

ALTER TABLE ONLY instruction
    ADD CONSTRAINT instruction_fkey_section FOREIGN KEY (section_id) REFERENCES section(id);


--
-- TOC entry 2765 (class 2606 OID 20399)

--

ALTER TABLE ONLY instruction_result_file_link
    ADD CONSTRAINT instruction_result_to_file_link_fkey_file FOREIGN KEY (file_uuid) REFERENCES file(uuid);


--
-- TOC entry 2769 (class 2606 OID 18207)

--

ALTER TABLE ONLY instruction_step_option_link
    ADD CONSTRAINT instruction_to_step_option_link_fkey_instruction FOREIGN KEY (instruction_id) REFERENCES instruction(id);


--
-- TOC entry 2770 (class 2606 OID 18212)

--

ALTER TABLE ONLY instruction_step_option_link
    ADD CONSTRAINT instruction_to_step_option_link_fkey_step_option FOREIGN KEY (step_option_id) REFERENCES step_option(id);


--
-- TOC entry 2771 (class 2606 OID 18217)

--

ALTER TABLE ONLY instruction_test_case_option_link
    ADD CONSTRAINT instruction_to_test_case_option_link_fkey_instruction FOREIGN KEY (instruction_id) REFERENCES instruction(id);


--
-- TOC entry 2772 (class 2606 OID 18222)

--

ALTER TABLE ONLY instruction_test_case_option_link
    ADD CONSTRAINT instruction_to_test_case_option_link_fkey_test_case_option FOREIGN KEY (test_case_option_id) REFERENCES test_case_option(id);


--
-- TOC entry 2790 (class 2606 OID 18227)

--

ALTER TABLE ONLY step_log
    ADD CONSTRAINT log_level_fkey FOREIGN KEY (level_id) REFERENCES log_level(id) NOT VALID;


--
-- TOC entry 2758 (class 2606 OID 18232)

--

ALTER TABLE ONLY engine_module_property_link
    ADD CONSTRAINT module_property_fkey FOREIGN KEY (module_property_id) REFERENCES module_property(id);


--
-- TOC entry 2773 (class 2606 OID 18237)

--

ALTER TABLE ONLY module_module_property_link
    ADD CONSTRAINT module_property_id FOREIGN KEY (module_property_id) REFERENCES module_property(id);


--
-- TOC entry 2774 (class 2606 OID 18242)

--

ALTER TABLE ONLY module_module_property_link
    ADD CONSTRAINT moudle_id_fkey FOREIGN KEY (module_id) REFERENCES module(id);


--
-- TOC entry 2775 (class 2606 OID 20534)

--

ALTER TABLE ONLY project
    ADD CONSTRAINT project_fk_type FOREIGN KEY (type_id) REFERENCES atm_object_type(id);


--
-- TOC entry 2808 (class 2606 OID 20560)

--

ALTER TABLE ONLY project_object_type_link
    ADD CONSTRAINT project_object_type_fk_object_type FOREIGN KEY (object_type_id) REFERENCES atm_object_type(id);


--
-- TOC entry 2807 (class 2606 OID 20555)

--

ALTER TABLE ONLY project_object_type_link
    ADD CONSTRAINT project_object_type_fk_project FOREIGN KEY (project_id) REFERENCES project(id);


--
-- TOC entry 2776 (class 2606 OID 18247)

--

ALTER TABLE ONLY project_application_link
    ADD CONSTRAINT project_to_application_link_fkey_application FOREIGN KEY (application_id) REFERENCES application(id);


--
-- TOC entry 2777 (class 2606 OID 18252)

--

ALTER TABLE ONLY project_application_link
    ADD CONSTRAINT project_to_application_link_fkey_project FOREIGN KEY (project_id) REFERENCES project(id);


--
-- TOC entry 2778 (class 2606 OID 18257)

--

ALTER TABLE ONLY project_test_case_link
    ADD CONSTRAINT project_to_test_case_link_project_id_fkey FOREIGN KEY (project_id) REFERENCES project(id);


--
-- TOC entry 2779 (class 2606 OID 18262)

--

ALTER TABLE ONLY project_test_case_link
    ADD CONSTRAINT project_to_test_case_link_test_case_id_fkey FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2766 (class 2606 OID 18267)

--

ALTER TABLE ONLY instruction_result
    ADD CONSTRAINT result_fkey_status FOREIGN KEY (status_id) REFERENCES status(id) NOT VALID;


--
-- TOC entry 2780 (class 2606 OID 18272)

--

ALTER TABLE ONLY run
    ADD CONSTRAINT run_fkey_status FOREIGN KEY (status_id) REFERENCES status(id);


--
-- TOC entry 2784 (class 2606 OID 18277)

--

ALTER TABLE ONLY run_set_test_case_link
    ADD CONSTRAINT run_set_fkey FOREIGN KEY (run_set_id) REFERENCES run_set(id);


--
-- TOC entry 2783 (class 2606 OID 24452)

--

ALTER TABLE ONLY run_set
    ADD CONSTRAINT run_set_fkey FOREIGN KEY (group_id) REFERENCES "group"(id);


--
-- TOC entry 2810 (class 2606 OID 22533)

--

ALTER TABLE ONLY run_set_ems_job_link
    ADD CONSTRAINT run_set_job_fkey_run_set FOREIGN KEY (run_set_id) REFERENCES run_set(id);


--
-- TOC entry 2781 (class 2606 OID 18302)

--

ALTER TABLE ONLY run_instruction_result_link
    ADD CONSTRAINT run_to_instrcution_result_link_fkey_instruction_result FOREIGN KEY (instruction_result_id) REFERENCES instruction_result(id);


--
-- TOC entry 2782 (class 2606 OID 18307)

--

ALTER TABLE ONLY run_instruction_result_link
    ADD CONSTRAINT run_to_result_link_fkey_run FOREIGN KEY (run_id) REFERENCES run(id) NOT VALID;


--
-- TOC entry 2786 (class 2606 OID 18312)

--

ALTER TABLE ONLY section_element_link
    ADD CONSTRAINT section_to_element_link_fkey_element FOREIGN KEY (element_id) REFERENCES element(id);


--
-- TOC entry 2787 (class 2606 OID 18317)

--

ALTER TABLE ONLY section_element_link
    ADD CONSTRAINT section_to_element_link_fkey_section FOREIGN KEY (section_id) REFERENCES section(id);


--
-- TOC entry 2789 (class 2606 OID 18322)

--

ALTER TABLE ONLY section_section_line_link
    ADD CONSTRAINT section_to_section_line_fkey_section_id FOREIGN KEY (section_id) REFERENCES section(id);


--
-- TOC entry 2803 (class 2606 OID 18327)

--

ALTER TABLE ONLY test_case_storage_link
    ADD CONSTRAINT storage_id FOREIGN KEY (storage_id) REFERENCES storage(id);


--
-- TOC entry 2785 (class 2606 OID 18332)

--

ALTER TABLE ONLY run_set_test_case_link
    ADD CONSTRAINT test_case_fkey FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2794 (class 2606 OID 18337)

--

ALTER TABLE ONLY test_case_engine_link
    ADD CONSTRAINT test_case_fkey FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2795 (class 2606 OID 18391)

--

ALTER TABLE ONLY test_case_environment_link
    ADD CONSTRAINT test_case_fkey FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2797 (class 2606 OID 18342)

--

ALTER TABLE ONLY test_case_folder_test_case_link
    ADD CONSTRAINT test_case_folder_to_test_case_link_fkey_test_case FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2798 (class 2606 OID 18347)

--

ALTER TABLE ONLY test_case_folder_test_case_link
    ADD CONSTRAINT test_case_folder_to_test_case_link_fkey_test_case_folder FOREIGN KEY (test_case_folder_id) REFERENCES test_case_folder(id);


--
-- TOC entry 2792 (class 2606 OID 24446)

--

ALTER TABLE ONLY test_case
    ADD CONSTRAINT test_case_group_id_fkey FOREIGN KEY (group_id) REFERENCES "group"(id);


--
-- TOC entry 2804 (class 2606 OID 18352)

--

ALTER TABLE ONLY test_case_storage_link
    ADD CONSTRAINT test_case_id FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2791 (class 2606 OID 18357)

--

ALTER TABLE ONLY test_case
    ADD CONSTRAINT test_case_status_id_fkey FOREIGN KEY (status_id) REFERENCES status(id);


--
-- TOC entry 2809 (class 2606 OID 22520)

--

ALTER TABLE ONLY test_case_ems_task_link
    ADD CONSTRAINT test_case_task_fkey_test_case FOREIGN KEY (test_case_id) REFERENCES test_case(id);


--
-- TOC entry 2799 (class 2606 OID 18362)

--

ALTER TABLE ONLY test_case_instruction_link
    ADD CONSTRAINT test_case_to_instrcution_link_fkey_test_case FOREIGN KEY (test_case_id) REFERENCES test_case(id) NOT VALID;


--
-- TOC entry 2800 (class 2606 OID 18367)

--

ALTER TABLE ONLY test_case_instruction_link
    ADD CONSTRAINT test_case_to_instruction_link_fkey_instruction FOREIGN KEY (instruction_id) REFERENCES instruction(id) NOT VALID;


--
-- TOC entry 2801 (class 2606 OID 18372)

--

ALTER TABLE ONLY test_case_run_link
    ADD CONSTRAINT test_case_to_run_link_fkey_run FOREIGN KEY (run_id) REFERENCES run(id) NOT VALID;


--
-- TOC entry 2802 (class 2606 OID 18377)

--

ALTER TABLE ONLY test_case_run_link
    ADD CONSTRAINT test_case_to_run_link_fkey_test_case FOREIGN KEY (test_case_id) REFERENCES test_case(id) NOT VALID;
