CREATE TRIGGER "driver_pack_insert_created_at_updated_at"
  BEFORE INSERT ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();


CREATE TRIGGER "driver_pack_update_created_at_updated_at"
  BEFORE UPDATE ON driver_pack
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();