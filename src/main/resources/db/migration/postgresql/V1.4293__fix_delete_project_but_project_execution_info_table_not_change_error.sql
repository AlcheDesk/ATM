--add column from project_execution_info
ALTER TABLE project_execution_info
    ADD COLUMN project_is_deleted boolean NOT NULL DEFAULT False;

--update project_execution_info project_is_deleted column
UPDATE project_execution_info SET project_is_deleted = true
	   WHERE project_id in (SELECT id FROM project where is_deleted is true);

--replace function project_update_change_others
CREATE OR REPLACE FUNCTION project_update_change_others()
    RETURNS trigger
    LANGUAGE 'plpgsql'
    VOLATILE
    COST 100
AS $BODY$BEGIN
	IF NEW.name IS NOT NULL AND NEW.name IS DISTINCT FROM OLD.name THEN
		UPDATE test_case SET project_name = NEW.name WHERE project_id = NEW.id;
		UPDATE project_execution_info SET project_name = NEW.name WHERE project_id = NEW.id;
	ELSIF TG_OP = 'UPDATE' AND NEW.name IS NULL THEN
	    UPDATE test_case SET project_name = NULL WHERE project_id = NEW.id;
	    UPDATE project_execution_info SET project_name = NULL WHERE project_id = NEW.id;
	ELSIF TG_OP = 'UPDATE' AND NEW.is_deleted IS true THEN
	    DELETE FROM project_execution_info
	    WHERE project_id = OLD.id;
	END IF;
	
	IF NEW.is_deleted IS TRUE AND OLD.is_deleted IS FALSE THEN
		UPDATE project_execution_info SET project_is_deleted = NEW.is_deleted WHERE project_id = NEW.id;
	END IF;
	
	IF NEW.is_deleted IS FALSE AND OLD.is_deleted IS TRUE THEN
	    UPDATE project_execution_info SET project_is_deleted = NEW.is_deleted WHERE project_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$BODY$;