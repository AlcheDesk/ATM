CREATE OR REPLACE FUNCTION "execution_logic_run_update_run_set_result" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.status_id = 2 THEN
		UPDATE run_set_result SET status_id = 2, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
		||'Updated status from NEW to WIP by run '||NEW.id WHERE status_id = 1 AND id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 3 THEN
        UPDATE run_set_result SET status_id = 2, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status from NEW to WIP by run '||NEW.id WHERE status_id = 1 AND id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 5 THEN
        UPDATE run_set_result SET status_id = 5, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to ERROR by run '||NEW.id WHERE id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 4 THEN
        UPDATE run_set_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a FAIL signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 8 THEN
        UPDATE run_set_result SET status_id = 4, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to FAIL by TIMEOUT by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a TIMEOUT signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
    ELSIF NEW.status_id = 9 THEN
        UPDATE run_set_result SET status_id = 9, log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Updated status to TERMINATED by TERMINATED by run '||NEW.id WHERE status_id <> 5 AND id = NEW.run_set_result_id;
        UPDATE run_set_result SET log = to_char (now()::timestamp at time zone 'UTC', 'YYYY-MM-DD"T"HH24:MI:SS"Z"')
        ||'Keep status as ERROR but got a TERMINATED signal by run '||NEW.id WHERE status_id = 5 AND id = NEW.run_set_result_id;
	END IF;
    RETURN NEW;   
END;
$body$ LANGUAGE plpgsql;