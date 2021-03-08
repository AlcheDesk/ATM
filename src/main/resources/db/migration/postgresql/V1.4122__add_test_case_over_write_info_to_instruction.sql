ALTER TABLE "instruction" ADD COLUMN ref_test_case_overwrite_name text;
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_tcow UNIQUE ("id", "ref_test_case_overwrite_id");
--------------------------------------------------------------------------------------
ALTER TABLE "instruction_target_map" ADD COLUMN ref_test_case_overwrite_id bigint;
ALTER TABLE "instruction_target_map" ADD COLUMN ref_test_case_overwrite_name text;
ALTER TABLE "instruction_target_map" ADD CONSTRAINT instruction_target_map_fk_tcow_ins FOREIGN KEY (ref_test_case_overwrite_id, instruction_id) 
REFERENCES "instruction" ("ref_test_case_overwrite_id", "id") DEFERRABLE INITIALLY DEFERRED;
--------------------------------------------------------------------------------------
UPDATE "instruction_target_map" itm
SET ref_test_case_overwrite_id = i.ref_test_case_overwrite_id,
ref_test_case_overwrite_name = i.ref_test_case_overwrite_name
FROM (select ins.id as ins_id, 
         tcow.id as ref_test_case_overwrite_id,
	 tcow.name as ref_test_case_overwrite_name
 from instruction ins 
 full outer join test_case_overwrite tcow on tcow.id = ins.ref_test_case_overwrite_id
 where ins.id is not null and ins.ref_test_case_overwrite_id is not null) i
WHERE 
    i.ins_id = instruction_id;
--------------------------------------------------------------------------------------
UPDATE "instruction"
SET ref_test_case_overwrite_name = i.name
FROM (SELECT instruction_id, ref_test_case_overwrite_name as name
    FROM instruction_target_map) i
WHERE 
    i.instruction_id = id;
--------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_overwrite_update_itm" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set ref_test_case_overwrite_name = NEW.name where ref_test_case_overwrite_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_overwrite_update_update_itm"
  AFTER UPDATE ON test_case_overwrite
  FOR EACH ROW
EXECUTE PROCEDURE test_case_overwrite_update_itm();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_target_map_update_instruction" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.application_name <> OLD.application_name OR 
	NEW.section_name <> OLD.section_name OR 
	NEW.element_name <> OLD.element_name OR 
	NEW.ref_test_case_name <> OLD.ref_test_case_name OR
	NEW.test_case_share_folder_name <> OLD.test_case_share_folder_name THEN
		update instruction set target = 
		concat_ws ('.', NEW.application_name, NEW.section_name, NEW.element_name, NEW.ref_test_case_name, NEW.test_case_share_folder_name) 
		where id = NEW.instruction_id;
	END IF;
	
	IF NEW.ref_test_case_overwrite_name <> OLD.ref_test_case_overwrite_name THEN
		update instruction set ref_test_case_overwrite_name = NEW.ref_test_case_overwrite_name 
	    where id = NEW.instruction_id;
	END IF;
	
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_insert_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into instruction_target_map
	(	 
	 application_id, 
	 application_name, 
	 section_id, 
	 section_name, 
	 element_id, 
	 element_name, 
	 test_case_share_folder_id, 
	 test_case_share_folder_name, 
	 ref_test_case_id, 
	 ref_test_case_name,
	 ref_test_case_overwrite_id,
	 ref_test_case_overwrite_name,
	 instruction_id
	 ) 
	values 
	(
		 NEW.application_id, 
		 (select name from application where id = NEW.application_id), 
		 NEW.section_id, 
		 (select name from section where id = NEW.section_id),  
		 NEW.element_id, 
		 (select name from element where id = NEW.element_id),  
		 NEW.test_case_share_folder_id, 
		 (select name from test_case_share_folder where id = NEW.test_case_share_folder_id), 
		 NEW.ref_test_case_id, 
		 (select name from test_case where id = NEW.ref_test_case_id), 
		 NEW.ref_test_case_overwrite_id, 
		 (select name from test_case_overwrite where id = NEW.ref_test_case_overwrite_id), 
		 NEW.id
	 );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;
------------------------------------------------------------------------------