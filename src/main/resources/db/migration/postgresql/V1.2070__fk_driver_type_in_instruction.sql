ALTER TABLE "instruction_type" ADD CONSTRAINT instruction_type_fk_driver_type FOREIGN KEY ("driver_type_id") REFERENCES "driver_type" ("id");