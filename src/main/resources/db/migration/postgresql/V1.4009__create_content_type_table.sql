CREATE TABLE content_type
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT content_type_ix_name UNIQUE (name)
    );
    
INSERT INTO content_type (id, name) VALUES (1,'Image');
INSERT INTO content_type (id, name) VALUES (2,'Video');
INSERT INTO content_type (id, name) VALUES (3,'Text');
INSERT INTO content_type (id, name) VALUES (4,'Audio');
INSERT INTO content_type (id, name) VALUES (5,'Android_APK');
INSERT INTO content_type (id, name) VALUES (6,'iOS_IPA');

ALTER TABLE "user_content" ADD CONSTRAINT user_content_fk_type FOREIGN KEY ("content_type") REFERENCES "content_type" ("id");

ALTER TABLE "user_content" RENAME COLUMN "content_type" TO content_type_id;

INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (26,'content_type');