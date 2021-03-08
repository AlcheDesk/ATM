DROP TRIGGER "application_insert_created_at_updated_at" ON "application";
CREATE TRIGGER "application_insert_created_at_updated_at"
  BEFORE INSERT ON application
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "dev_execution_log_insert_created_at_updated_at" ON "dev_execution_log";
CREATE TRIGGER "dev_execution_log_insert_created_at_updated_at"
  BEFORE INSERT ON dev_execution_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "dev_file_insert_created_at_updated_at" ON "dev_file";
CREATE TRIGGER "dev_file_insert_created_at_updated_at"
  BEFORE INSERT ON dev_file
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "dev_instruction_result_insert_created_at_updated_at" ON "dev_instruction_result";
CREATE TRIGGER "dev_instruction_result_insert_created_at_updated_at"
  BEFORE INSERT ON dev_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "dev_step_log_insert_created_at_updated_at" ON "dev_step_log";
CREATE TRIGGER "dev_step_log_insert_created_at_updated_at"
  BEFORE INSERT ON dev_step_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "driver_insert_created_at_updated_at" ON "driver";
CREATE TRIGGER "driver_insert_created_at_updated_at"
  BEFORE INSERT ON driver
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "element_insert_created_at_updated_at" ON "element";
CREATE TRIGGER "element_insert_created_at_updated_at"
  BEFORE INSERT ON element
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "instruction_insert_created_at_updated_at" ON "instruction";
CREATE TRIGGER "instruction_insert_created_at_updated_at"
  BEFORE INSERT ON instruction
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "instruction_option_insert_created_at_updated_at" ON "instruction_option";
CREATE TRIGGER "instruction_option_insert_created_at_updated_at"
  BEFORE INSERT ON instruction_option
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "prod_execution_log_insert_created_at_updated_at" ON "prod_execution_log";
CREATE TRIGGER "prod_execution_log_insert_created_at_updated_at"
  BEFORE INSERT ON prod_execution_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "prod_file_insert_created_at_updated_at" ON "prod_file";
CREATE TRIGGER "prod_file_insert_created_at_updated_at"
  BEFORE INSERT ON prod_file
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "prod_instruction_result_insert_created_at_updated_at" ON "prod_instruction_result";
CREATE TRIGGER "prod_instruction_result_insert_created_at_updated_at"
  BEFORE INSERT ON prod_instruction_result
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "prod_step_log_insert_created_at_updated_at" ON "prod_step_log";
CREATE TRIGGER "prod_step_log_insert_created_at_updated_at"
  BEFORE INSERT ON prod_step_log
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "project_insert_created_at_updated_at" ON "project";
CREATE TRIGGER "project_insert_created_at_updated_at"
  BEFORE INSERT ON project
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "run_insert_created_at_updated_at" ON "run";
CREATE TRIGGER "run_insert_created_at_updated_at"
  BEFORE INSERT ON run
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "run_set_insert_created_at_updated_at" ON "run_set";
CREATE TRIGGER "run_set_insert_created_at_updated_at"
  BEFORE INSERT ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "run_set_job_link_insert_created_at_updated_at" ON "run_set_job_link";
CREATE TRIGGER "run_set_job_link_insert_created_at_updated_at"
  BEFORE INSERT ON run_set_job_link
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "section_insert_created_at_updated_at" ON "section";
CREATE TRIGGER "section_insert_created_at_updated_at"
  BEFORE INSERT ON section
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "test_case_insert_created_at_updated_at" ON "test_case";
CREATE TRIGGER "test_case_insert_created_at_updated_at"
  BEFORE INSERT ON test_case
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "test_case_folder_insert_created_at_updated_at" ON "test_case_folder";
CREATE TRIGGER "test_case_folder_insert_created_at_updated_at"
  BEFORE INSERT ON test_case_folder
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "test_case_option_insert_created_at_updated_at" ON "test_case_option";
CREATE TRIGGER "test_case_option_insert_created_at_updated_at"
  BEFORE INSERT ON test_case_option
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

DROP TRIGGER "test_case_task_link_insert_created_at_updated_at" ON "test_case_task_link";
CREATE TRIGGER "test_case_task_link_insert_created_at_updated_at"
  BEFORE INSERT ON test_case_task_link
  FOR EACH ROW
EXECUTE PROCEDURE insert_updated_at_created_at_column();

