DELETE FROM "system_requirement_pack_system_requirement_link" WHERE id IS NOT NULL;
ALTER TABLE "system_requirement_pack_system_requirement_link" ADD COLUMN system_requirement_type_id bigint;
ALTER TABLE "system_requirement_pack_system_requirement_link" ALTER COLUMN system_requirement_type_id SET NOT NULL;
ALTER TABLE "system_requirement" ADD CONSTRAINT system_requirement_ix_id_type UNIQUE ("id", "system_requirement_type_id");
ALTER TABLE "system_requirement_pack_system_requirement_link" ADD CONSTRAINT 
srpsrl_fk_system_requirement_ant_type FOREIGN KEY ("system_requirement_id", system_requirement_type_id) REFERENCES 
"system_requirement" ("id", "system_requirement_type_id");
ALTER TABLE "system_requirement_pack_system_requirement_link" ADD CONSTRAINT srpsrl_ix_srp_srt UNIQUE ("system_requirement_pack_id", system_requirement_type_id);
ALTER TABLE "system_requirement_pack_system_requirement_link" RENAME CONSTRAINT "system_requirement_pack_syste_system_requirement_pack_id_sy_key" TO srpsrl_ix_srp_sr;