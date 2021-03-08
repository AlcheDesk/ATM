--instruction target triggers
ALTER TABLE "application" disable TRIGGER "application_udpate_trigger_instruction_target_update";
ALTER TABLE "instruction" disable TRIGGER "instruction_insert_target";
ALTER TABLE "instruction" disable TRIGGER "instruction_update_target";
ALTER TABLE "section" disable TRIGGER "section_udpate_trigger_instruction_target_update";

-- result triggers
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_finished_status_update";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_insert_trigger_run_status";
ALTER TABLE "dev_instruction_result" disable TRIGGER "dev_instruction_result_update_trigger_run_status";
ALTER TABLE "dev_step_log" disable TRIGGER "dev_step_log_trigger_dev_instruction_result_status_update";

ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_finished_status_update";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_insert_trigger_run_status";
ALTER TABLE "prod_instruction_result" disable TRIGGER "prod_instruction_result_update_trigger_run_status";
ALTER TABLE "prod_step_log" disable TRIGGER "prod_step_log_trigger_prod_instruction_result_status_update";

ALTER TABLE "run" disable TRIGGER "run_update_finished";


ALTER TABLE "instruction" DROP COLUMN "target";
ALTER TABLE "prod_instruction_result" DROP COLUMN "target";
ALTER TABLE "dev_instruction_result" DROP COLUMN "target";