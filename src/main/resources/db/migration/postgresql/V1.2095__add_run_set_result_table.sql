-- create table
CREATE TABLE run_set_result
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        start_at TIMESTAMP WITH TIME zone,
        end_at TIMESTAMP WITH TIME zone,
        status_id bigint DEFAULT 1 NOT NULL,
        log text NOT NULL,
        group_id bigint NOT NULL,
        run_set_type_id bigint NOT NULL,
        priority INTEGER DEFAULT 10 NOT NULL,
        run_set_id bigint NOT NULL,
        CONSTRAINT run_set_result_fk_group FOREIGN KEY (group_id) REFERENCES "group"("id"),
        CONSTRAINT run_set_result_fk_run_set FOREIGN KEY (run_set_id) REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_result_fk_run_set_type FOREIGN KEY (run_set_type_id) REFERENCES "run_set_type" ("id"),
        CONSTRAINT run_set_result_fk_status FOREIGN KEY (status_id) REFERENCES "status" ("id"),
        PRIMARY KEY (id)
    );

-- add trigger 
CREATE TRIGGER run_set_result_insert_create_at_update_at BEFORE UPDATE ON "run_set_result" FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER run_set_result_update_created_at_updated_at BEFORE UPDATE ON "run_set_result" FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();

-- add link to run
ALTER TABLE "run" ADD COLUMN run_set_result_id bigint;
ALTER TABLE "run" ADD CONSTRAINT run_fk_run_set_result FOREIGN KEY (run_set_result_id) REFERENCES "run_set_result" ("id");