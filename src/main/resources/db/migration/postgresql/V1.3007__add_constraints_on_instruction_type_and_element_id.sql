ALTER TABLE "instruction_overwrite" ALTER COLUMN "element_id" DROP NOT NULL;
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT instruction_overwrite_ck_element_id_and_instruction_type_id 
CHECK (
(element_id IS NOT NULL AND instruction_type_id <> 4 AND instruction_type_id <> 6 AND instruction_type_id <> 7)
OR 
(element_id IS NULL AND (instruction_type_id = 4 OR instruction_type_id = 6 OR instruction_type_id = 7))
);


ALTER TABLE "instruction" ADD CONSTRAINT instruction_ck_element_id_and_instruction_type_id 
CHECK (
(element_id IS NOT NULL AND instruction_type_id <> 4 AND instruction_type_id <> 6 AND instruction_type_id <> 7)
OR 
(element_id IS NULL AND (instruction_type_id = 4 OR instruction_type_id = 6 OR instruction_type_id = 7))
);
