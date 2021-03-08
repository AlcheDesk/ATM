ALTER TABLE "run_set" ADD COLUMN parent_id bigint;
ALTER TABLE "run_set" ADD CONSTRAINT run_set_fk_parent FOREIGN KEY (parent_id) REFERENCES "run_set" ("id");