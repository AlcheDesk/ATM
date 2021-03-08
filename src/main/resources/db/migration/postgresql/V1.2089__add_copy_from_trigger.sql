-- create trigger function
CREATE OR REPLACE FUNCTION "update_copy_from_id_column" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
    NEW.copy_from_id = OLD.copy_from_id;
    RETURN NEW;   
END;
$$ LANGUAGE plpgsql;

-- add trigger to table
-- api_schema
CREATE TRIGGER api_schema_update_copy_from_id
    BEFORE UPDATE 
    ON api_schema
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- application    
CREATE TRIGGER application_update_copy_from_id
    BEFORE UPDATE 
    ON application
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- driver    
CREATE TRIGGER driver_update_copy_from_id
    BEFORE UPDATE 
    ON driver
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- drvier_pack    
CREATE TRIGGER driver_pack_update_copy_from_id
    BEFORE UPDATE 
    ON driver_pack
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- element    
CREATE TRIGGER element_update_copy_from_id
    BEFORE UPDATE 
    ON element
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- instruction    
CREATE TRIGGER instruction_update_copy_from_id
    BEFORE UPDATE 
    ON instruction
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- project   
CREATE TRIGGER project_update_copy_from_id
    BEFORE UPDATE 
    ON project
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- run_set   
CREATE TRIGGER run_set_update_copy_from_id
    BEFORE UPDATE 
    ON run_set
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- section   
CREATE TRIGGER section_update_copy_from_id
    BEFORE UPDATE 
    ON section
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- test_case   
CREATE TRIGGER test_case_update_copy_from_id
    BEFORE UPDATE 
    ON test_case
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();
-- test_case_folder   
CREATE TRIGGER test_case_folder_update_copy_from_id
    BEFORE UPDATE 
    ON test_case_folder
    FOR EACH ROW
    EXECUTE PROCEDURE update_copy_from_id_column();