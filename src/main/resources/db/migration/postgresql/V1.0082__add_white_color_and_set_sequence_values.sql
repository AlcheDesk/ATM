DELETE FROM color WHERE name = 'WHITE';
INSERT INTO color (id,name) VALUES (6,'WHITE') ON CONFLICT DO NOTHING;

ALTER SEQUENCE instrcution_option_id_seq RENAME TO instruction_option_id_seq;

ALTER SEQUENCE color_id_seq START WITH 1000;
ALTER SEQUENCE color_id_seq RESTART; 

ALTER SEQUENCE driver_id_seq START WITH 1000;
ALTER SEQUENCE driver_id_seq RESTART;  

ALTER SEQUENCE element_type_id_seq START WITH 1000;
ALTER SEQUENCE element_type_id_seq RESTART;  

ALTER SEQUENCE element_locator_type_id_seq START WITH 1000;
ALTER SEQUENCE element_locator_type_id_seq RESTART; 

ALTER SEQUENCE element_action_id_seq START WITH 1000;
ALTER SEQUENCE element_action_id_seq RESTART; 

ALTER SEQUENCE file_type_id_seq START WITH 1000;
ALTER SEQUENCE file_type_id_seq RESTART; 

ALTER SEQUENCE group_id_seq START WITH 1000;
ALTER SEQUENCE group_id_seq RESTART; 

ALTER SEQUENCE instruction_option_id_seq START WITH 1000;
ALTER SEQUENCE instruction_option_id_seq RESTART; 

ALTER SEQUENCE instruction_type_id_seq START WITH 1000;
ALTER SEQUENCE instruction_type_id_seq RESTART;  

ALTER SEQUENCE project_type_id_seq START WITH 1000;
ALTER SEQUENCE project_type_id_seq RESTART; 

ALTER SEQUENCE run_set_type_id_seq START WITH 1000;
ALTER SEQUENCE run_set_type_id_seq RESTART;  

ALTER SEQUENCE system_requirement_id_seq START WITH 1000;
ALTER SEQUENCE system_requirement_id_seq RESTART;  

ALTER SEQUENCE test_case_folder_type_id_seq START WITH 1000;
ALTER SEQUENCE test_case_folder_type_id_seq RESTART;

ALTER SEQUENCE test_case_option_id_seq START WITH 1000;
ALTER SEQUENCE test_case_option_id_seq RESTART;  