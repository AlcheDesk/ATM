CREATE TABLE template
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        type text DEFAULT 'EMAIL',
        content text NOT NULL,
        updated_at timestamp with time zone DEFAULT now() NOT NULL,
	    created_at timestamp with time zone DEFAULT now() NOT NULL,
	    log text,
	    PRIMARY KEY (id),
	    CONSTRAINT template_ix_name UNIQUE (name)
    );
    
CREATE TRIGGER template_insert_create_at_update_at BEFORE INSERT ON "template" 
FOR EACH ROW EXECUTE PROCEDURE "insert_updated_at_created_at_column"();
CREATE TRIGGER template_update_created_at_updated_at BEFORE UPDATE ON "template" 
FOR EACH ROW EXECUTE PROCEDURE "update_created_at_updated_at_column"();


CREATE TABLE parameter_script
    (
        id bigserial NOT NULL,
        type text DEFAULT 'JavaScript' NOT NULL,
        symbol text NOT NULL,
        script text NOT NULL,
        PRIMARY KEY (id)
    );
    
ALTER TABLE "email" ALTER COLUMN updated_at SET DEFAULT now();
ALTER TABLE "email" ALTER COLUMN created_at SET DEFAULT now();
