ALTER TABLE "instruction_overwrite" ALTER COLUMN "overwrite_fields" DROP NOT NULL;
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_ck_overwrite_fields_and_instruction_type_id 
CHECK (
(overwrite_fields IS NOT NULL AND instruction_type_id <> 4 AND instruction_type_id <> 6 AND instruction_type_id <> 7)
OR 
(overwrite_fields IS NULL AND (instruction_type_id = 4 OR instruction_type_id = 6 OR instruction_type_id = 7))
);