CREATE OR REPLACE FUNCTION "driver_delete_delete_driver_pack_driver_link" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	delete from driver_pack_driver_link where driver_id = OLD.id;
	RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "driver_before_delete_driver_pack_driver_link"
  BEFORE DELETE ON driver
  FOR EACH ROW
EXECUTE PROCEDURE driver_delete_delete_driver_pack_driver_link();

DELETE FROM "driver" WHERE is_deleted is true;
ALTER TABLE "driver" DROP COLUMN "is_deleted";