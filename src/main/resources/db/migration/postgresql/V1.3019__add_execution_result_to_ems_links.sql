CREATE TABLE run_set_result_job_link
    (
        id bigserial NOT NULL,
        run_set_result_id bigint NOT NULL,
        job_uuid uuid NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT run_set_result_job_link_fk_run_set FOREIGN KEY (run_set_result_id) REFERENCES "run_set_result" ("id"),
        CONSTRAINT run_set_result_job_link_ix_run_set UNIQUE (run_set_result_id),
        CONSTRAINT run_set_result_job_link_ix_job UNIQUE (job_uuid)
    );
    
CREATE TRIGGER run_set_result_job_link_insert_create_at_update_at BEFORE INSERT ON "run_set_result_job_link" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER run_set_result_job_link_update_created_at_updated_at BEFORE UPDATE ON "run_set_result_job_link" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();

CREATE TABLE run_task_link
    (
        id bigserial NOT NULL,
        run_id bigint NOT NULL,
        task_uuid uuid NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT run_task_link_fk_run_set FOREIGN KEY (run_id) REFERENCES "run" ("id"),
        CONSTRAINT run_task_link_ix_run UNIQUE (run_id),
        CONSTRAINT run_task_link_ix_task UNIQUE (task_uuid)
    );
    
CREATE TRIGGER run_task_link_insert_create_at_update_at BEFORE INSERT ON "run_task_link" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER run_task_link_update_created_at_updated_at BEFORE UPDATE ON "run_task_link" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();