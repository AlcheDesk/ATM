CREATE OR REPLACE FUNCTION "finish_run_set_result"(bigint)
  RETURNS void AS
$func$
BEGIN
   IF (SELECT count(id) FROM run WHERE run_set_result_id = $1 AND is_finished IS FALSE) = 0 THEN
      UPDATE run_set_result SET is_finished = TRUE, end_at = now()::timestamp at time zone 'UTC', log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'finished due to all linked run has been finished.' WHERE id = $1;
   END IF;
END
$func$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "execution_logic_run_update_run_set_result" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 2 THEN
		UPDATE run_set_result SET status_id = 2, start_at = now()::timestamp at time zone 'UTC', log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'Updated status from NEW to WIP by run '||NEW.id WHERE status_id = 1 AND id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 3 THEN
        UPDATE run_set_result SET status_id = 3, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status from WIP to PASS by run '||NEW.id WHERE status_id = 2 AND id = NEW.run_set_result_id;
        PERFORM finish_run_set_result(NEW.run_set_result_id);
    ELSIF NEW.status_id = 5 THEN
        UPDATE run_set_result SET status_id = 5, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to ERROR by run '||NEW.id WHERE id = NEW.run_set_result_id;
        PERFORM finish_run_set_result(NEW.run_set_result_id);
    ELSIF NEW.status_id = 4 THEN
        UPDATE run_set_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a FAIL signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
        PERFORM finish_run_set_result(NEW.run_set_result_id);
    ELSIF NEW.status_id = 8 THEN
        UPDATE run_set_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by TIMEOUT by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a TIMEOUT signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
        PERFORM finish_run_set_result(NEW.run_set_result_id);
    ELSIF NEW.status_id = 9 THEN
        UPDATE run_set_result SET status_id = 9, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to TERMINATED by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a TERMINATED signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
        PERFORM finish_run_set_result(NEW.run_set_result_id);
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "execution_logic_prod_instruction_result_update_run" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id <> 2 AND NEW.status_id <> 5 THEN
		UPDATE run SET status_id = 2, start_at = now()::timestamp at time zone 'UTC', log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'Updated status from NEW to WIP by instruction result '||NEW.id WHERE status_id = 1 AND id = NEW.run_id;
    ELSIF NEW.status_id = 5 THEN
        UPDATE run SET status_id = 5, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to ERROR by instruction result '||NEW.id WHERE id = NEW.run_id;
    ELSIF NEW.status_id = 4 THEN
        UPDATE run SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by instruction result '||NEW.id WHERE status_id <> 5 AND id = NEW.run_id;
        UPDATE run SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a FAIL signal by instruction result '||NEW.id WHERE status_id = 5 AND id = NEW.run_id;
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "execution_logic_prod_step_log_update_prod_instruction_result" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.step_log_type_id = 1 OR NEW.step_log_type_id = 2 THEN
		UPDATE prod_instruction_result SET status_id = 2, start_at = now()::timestamp at time zone 'UTC', log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'Updated status from NEW to WIP by step log '||NEW.id WHERE status_id = 1 AND id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id = 8 THEN
        UPDATE prod_instruction_result SET status_id = 5, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to ERROR by step log '||NEW.id WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id = 4 OR NEW.step_log_type_id = 6 OR NEW.step_log_type_id = 7 THEN
        UPDATE prod_instruction_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by step log '||NEW.id WHERE status_id <> 5 AND id = NEW.instruction_result_id;
        UPDATE prod_instruction_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a FAIL signal by step log '||NEW.id WHERE status_id = 5 AND id = NEW.instruction_result_id;
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "execution_logic_dev_step_log_update_dev_instruction_result" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.step_log_type_id = 1 OR NEW.step_log_type_id = 2 THEN
		UPDATE dev_instruction_result SET status_id = 2, start_at = now()::timestamp at time zone 'UTC', log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'Updated status from NEW to WIP by step log '||NEW.id WHERE status_id = 1 AND id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id = 8 THEN
        UPDATE dev_instruction_result SET status_id = 5, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to ERROR by step log '||NEW.id WHERE id = NEW.instruction_result_id;
    ELSIF NEW.step_log_type_id = 4 OR NEW.step_log_type_id = 6 OR NEW.step_log_type_id = 7 THEN
        UPDATE dev_instruction_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by step log '||NEW.id WHERE status_id <> 5 AND id = NEW.instruction_result_id;
        UPDATE dev_instruction_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a FAIL signal by step log '||NEW.id WHERE status_id = 5 AND id = NEW.instruction_result_id;
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;