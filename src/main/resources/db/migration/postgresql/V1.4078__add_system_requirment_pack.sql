CREATE TABLE system_requirement_pack 
	(id BIGSERIAL NOT NULL, 
	name TEXT NOT NULL, 
	created_at TIMESTAMP(6) WITH TIME ZONE DEFAULT now() NOT NULL, 
	updated_at TIMESTAMP(6) WITH TIME ZONE DEFAULT now() NOT NULL, 
	execution_count BIGINT DEFAULT 0 NOT NULL, 
	comment TEXT, 
	is_deleted BOOLEAN DEFAULT false NOT NULL, 
	log TEXT, 
	is_default BOOLEAN DEFAULT false NOT NULL, 
	copy_from_id BIGINT, tenant_id BIGINT DEFAULT 1 NOT NULL, 
	PRIMARY KEY (id), CONSTRAINT system_requirement_pack_fk_copy_from FOREIGN KEY (copy_from_id) REFERENCES "system_requirement_pack" ("id")
	);

CREATE TRIGGER "system_requirement_pack_update_copy_from_id"
  BEFORE UPDATE ON system_requirement_pack
  FOR EACH ROW
EXECUTE PROCEDURE update_copy_from_id_column();

CREATE TRIGGER "system_requirement_pack_update_created_at_updated_at"
  BEFORE UPDATE ON system_requirement_pack
  FOR EACH ROW
EXECUTE PROCEDURE update_created_at_updated_at_column();

CREATE TRIGGER "system_requirement_pack_update_log"
  BEFORE UPDATE ON system_requirement_pack
  FOR EACH ROW
EXECUTE PROCEDURE update_log_column();
---------------------------------------------------------------------
CREATE TABLE system_requirement_pack_system_requirement_link 
	(id BIGSERIAL NOT NULL, 
	system_requirement_pack_id BIGINT NOT NULL, 
	system_requirement_id BIGINT NOT NULL, 
	PRIMARY KEY (id), 
	CONSTRAINT srpsrl_fk_system_requirement FOREIGN KEY (system_requirement_id) REFERENCES "system_requirement" ("id"), 
	CONSTRAINT srpsrl_fk_system_requirement_pack FOREIGN KEY (system_requirement_pack_id) REFERENCES "system_requirement_pack" ("id"), 
	UNIQUE (system_requirement_pack_id, system_requirement_id)
	);
----------------------------------------------------------------------
	CREATE TABLE
    system_requirement_type
    (
        id BIGSERIAL NOT NULL,
        name TEXT NOT NULL,
        is_multiselectable BOOLEAN DEFAULT false NOT NULL,
        is_active BOOLEAN DEFAULT false NOT NULL,
        is_predefined BOOLEAN DEFAULT false NOT NULL,
        PRIMARY KEY (id),
        UNIQUE (name)
    );
-----------------------------------------------------------------------    
ALTER TABLE "system_requirement" DROP COLUMN "is_reserved";
ALTER TABLE "system_requirement" DROP COLUMN "is_value_required";
ALTER TABLE "system_requirement" ADD COLUMN system_requirement_type_id bigint;
ALTER TABLE "system_requirement" ALTER COLUMN system_requirement_type_id SET NOT NULL;
ALTER TABLE "system_requirement" ADD COLUMN value bigint;
ALTER TABLE "system_requirement" ALTER COLUMN value SET NOT NULL;
ALTER TABLE "system_requirement" ALTER COLUMN value SET DEFAULT 0;
ALTER TABLE "system_requirement" ADD CONSTRAINT system_requirement_fk_system_requirement_type FOREIGN KEY (system_requirement_type_id) REFERENCES "system_requirement_type" ("id");
