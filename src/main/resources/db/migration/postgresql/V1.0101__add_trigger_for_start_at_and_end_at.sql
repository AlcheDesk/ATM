CREATE OR REPLACE FUNCTION finished_update_status() RETURNS trigger
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
    	NEW.end_at =now();
    ELSIF OLD.finished = false and NEW.finished = true THEN
        NEW.end_at =now();  
    END IF;
    RETURN NEW;   
END;
$$;

