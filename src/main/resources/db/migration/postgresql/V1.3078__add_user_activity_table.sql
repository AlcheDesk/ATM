CREATE OR REPLACE FUNCTION "insert_created_at_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.created_at = now();
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION "update_created_at_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.created_at = OLD.created_at;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

CREATE TABLE user_activity_log
    (
        id bigserial NOT NULL,
        user_uuid uuid NOT NULL,
        activity_uuid uuid NOT NULL,
        target_model VARCHAR(255) NOT NULL,
        action_name VARCHAR(255) NOT NULL,
        input text,
        log text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        PRIMARY KEY (id)
    );
    
CREATE TRIGGER user_activity_log_insert_created_at BEFORE INSERT ON "user_activity_log" 
FOR EACH ROW EXECUTE PROCEDURE "insert_created_at_column"();
CREATE TRIGGER user_activity_log_update_created_at BEFORE UPDATE ON "user_activity_log" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_column"();




