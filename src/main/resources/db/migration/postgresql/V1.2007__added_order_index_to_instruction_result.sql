--added logical order index to instrcution and instruction result
ALTER TABLE "instruction" ADD COLUMN logical_order_index VARCHAR(20);
ALTER TABLE "prod_instruction_result" ADD COLUMN logical_order_index VARCHAR(20);
ALTER TABLE "dev_instruction_result" ADD COLUMN logical_order_index VARCHAR(20);