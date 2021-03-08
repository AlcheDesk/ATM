ALTER TABLE "instruction_overwrite" DROP CONSTRAINT "instruction_overwrite_ck_overwrite_fields_and_instruction_type_";
ALTER TABLE "instruction_type" ADD CONSTRAINT instruction_type_ix_overwrietable UNIQUE ("id", "overridable_fields");
ALTER TABLE "instruction_overwrite" ADD CONSTRAINT
instruction_overwrite_fk_instruction_type_overwriwrite_fields FOREIGN KEY ("overwrite_fields",
"instruction_type_id") REFERENCES "instruction_type" ("overridable_fields", "id");