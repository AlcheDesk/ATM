ALTER TABLE "run" ADD COLUMN system_requirement_pack_id bigint;
ALTER TABLE "run" ADD COLUMN system_requirements json;
ALTER TABLE "run" ADD COLUMN system_requirement_pack json;
ALTER TABLE "run" ADD CONSTRAINT run_fk_system_requirement_pack FOREIGN KEY (system_requirement_pack_id) REFERENCES "system_requirement_pack" ("id");