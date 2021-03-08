DELETE FROM "driver" WHERE id IS NOT NULL;

INSERT INTO "driver" (id,name,comment,active,default_flag,driver_type_id) VALUES (1,'Firefox','Default firefox settings',true,true,1) ON CONFLICT DO NOTHING;
ALTER TABLE "driver" ADD CONSTRAINT driver_ix_type_and_default UNIQUE ("default_flag","driver_type_id");