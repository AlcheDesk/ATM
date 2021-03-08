ALTER TABLE "application" ADD CONSTRAINT application_ix_id_name UNIQUE ("id", "name");
ALTER TABLE "section" ADD CONSTRAINT section_ix_id_name UNIQUE ("id", "name");
ALTER TABLE "element" ADD CONSTRAINT element_ix_id_name UNIQUE ("id", "name");
ALTER TABLE "test_case_share_folder" ADD CONSTRAINT test_case_share_folder_ix_id_name UNIQUE ("id", "name");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_ref_tc UNIQUE ("id", "ref_test_case_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_tcsf UNIQUE ("id", "test_case_share_folder_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_app UNIQUE ("id", "application_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_sec UNIQUE ("id", "section_id");
ALTER TABLE "instruction" ADD CONSTRAINT instruction_ix_ins_ele UNIQUE ("id", "element_id");
-----------------------------------------------------------------------------
CREATE TABLE instruction_target_map
    (
        id bigserial NOT NULL,
        application_id bigint,
        application_name text,
        section_id bigint,
        section_name text,
        element_id bigint,
        element_name text,
        test_case_share_folder_id bigint,
        test_case_share_folder_name text,
        ref_test_case_id bigint,
        ref_test_case_name text,
        instruction_id bigint NOT NULL,
        CONSTRAINT instruction_target_map_fk_application FOREIGN KEY (application_id, application_name) REFERENCES "application" ("id", "name") DEFERRABLE INITIALLY DEFERRED,
        CONSTRAINT instruction_target_map_fk_section FOREIGN KEY (section_name, section_id) REFERENCES "section" ("name", "id") DEFERRABLE INITIALLY DEFERRED,
        CONSTRAINT instruction_target_map_fk_element FOREIGN KEY (element_id, element_name) REFERENCES "element" ("id", "name") DEFERRABLE INITIALLY DEFERRED,
        CONSTRAINT instruction_target_map_fk_instruction FOREIGN KEY (instruction_id) REFERENCES "instruction" ("id") DEFERRABLE INITIALLY DEFERRED,
        CONSTRAINT instruction_target_map_fk_ref_test_case FOREIGN KEY (ref_test_case_id, instruction_id) REFERENCES "instruction" ("ref_test_case_id", "id") DEFERRABLE INITIALLY DEFERRED,
        CONSTRAINT instruction_target_map_fk_tcsf_tc FOREIGN KEY (test_case_share_folder_id, instruction_id) REFERENCES "instruction" ("test_case_share_folder_id", "id") DEFERRABLE INITIALLY DEFERRED
    );
-----------------------------------------------------------------------------------------------
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
	 instruction_id
 )   
 select app.id as application_id, 
	 app.name as application_name, 
	 sec.id as section_id, 
	 sec.name as section_name, 
	 ele.id as element_id, 
	 ele.name as element_name, 
	 tcsf.id as test_case_share_folder_id, 
	 tcsf.name as test_case_share_folder_name, 
	 tc.id as ref_test_case_id, 
	 tc.name as ref_test_case_name, 
	 ins.id as instruction_id
 from instruction ins 
 full outer join test_case_share_folder tcsf on tcsf.id = ins.test_case_share_folder_id
 full outer join application app on app.id = ins.application_id
 full outer join section sec on sec.id = ins.section_id
 full outer join element ele on ele.id = ins.element_id
 full outer join test_case tc on tc.id = ins.ref_test_case_id
 where ins.id is not null;
 -----------------------------------------------------------------------------
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
		 NEW.id
	 );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "instruction_after_insert_insert_itm"
  AFTER INSERT ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE instruction_insert_instruction_target_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "application_update_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set application_name = NEW.name where application_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "application_update_update_itm"
  BEFORE UPDATE ON application
  FOR EACH ROW
EXECUTE PROCEDURE application_update_instruction_target_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "section_update_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set section_name = NEW.name where section_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "section_update_update_itm"
  AFTER UPDATE ON section
  FOR EACH ROW
EXECUTE PROCEDURE section_update_instruction_target_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "element_update_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set element_name = NEW.name where element_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "element_update_update_itm"
  AFTER UPDATE ON element
  FOR EACH ROW
EXECUTE PROCEDURE element_update_instruction_target_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_update_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set ref_test_case_name = NEW.name where ref_test_case_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_update_update_itm"
  AFTER UPDATE ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE test_case_update_instruction_target_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "test_case_share_folder_update_itm" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	IF NEW.name <> OLD.name THEN
		update instruction_target_map set test_case_share_folder_name = NEW.name where test_case_share_folder_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "test_case_share_folder_update_update_itm"
  AFTER UPDATE ON test_case_share_folder
  FOR EACH ROW
EXECUTE PROCEDURE test_case_share_folder_update_itm();
------------------------------------------------------------------------------