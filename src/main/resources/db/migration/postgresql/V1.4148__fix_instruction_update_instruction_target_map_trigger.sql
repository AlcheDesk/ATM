CREATE OR REPLACE FUNCTION "instruction_update_instruction_target_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	IF NEW.application_id <> OLD.application_id OR
	  NEW.section_id <> OLD.section_id OR
	  NEW.element_id <> OLD.element_id OR
	  NEW.test_case_share_folder_id <> OLD.test_case_share_folder_id OR
	  NEW.ref_test_case_overwrite_id <> OLD.ref_test_case_overwrite_id OR
	  NEW.ref_test_case_id <> OLD.ref_test_case_id THEN
	  
	 update "instruction_target_map" set
		 application_id = NEW.application_id,
		 application_name =  (select name from application where id = NEW.application_id), 
		 section_id = NEW.section_id, 
		 section_name = (select name from section where id = NEW.section_id),  
		 element_id = NEW.element_id, 
		 element_name =  (select name from element where id = NEW.element_id),  
		 test_case_share_folder_id =  NEW.test_case_share_folder_id, 
		 test_case_share_folder_name =  (select name from test_case_share_folder where id = NEW.test_case_share_folder_id), 
		 ref_test_case_id = NEW.ref_test_case_id, 
		 ref_test_case_name = (select name from test_case where id = NEW.ref_test_case_id), 
		 ref_test_case_overwrite_id = NEW.ref_test_case_overwrite_id, 
		 ref_test_case_overwrite_name = (select name from test_case_overwrite where id = NEW.ref_test_case_overwrite_id)
    where instruction_id = NEW.id;
	END IF;
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;