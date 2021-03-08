CREATE TRIGGER alias_insert_create_at_update_at BEFORE INSERT ON "alias" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER alias_update_created_at_updated_at BEFORE UPDATE ON "alias" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();