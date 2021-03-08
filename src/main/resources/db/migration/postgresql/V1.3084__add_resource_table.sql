CREATE TABLE resource
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        path text NOT NULL,
        description text,
        uuid uuid DEFAULT uuid_generate_v4() NOT NULL,
        md5 CHARACTER(32) NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT resource_ixmd5 UNIQUE (md5),
        CONSTRAINT resource_ix_uuid UNIQUE (uuid)
    );
    
ALTER TABLE "driver" ADD COLUMN resource_md5 CHARACTER(32);
ALTER TABLE "test_case" ADD COLUMN resource_md5 CHARACTER(32);
ALTER TABLE "instruction" ADD COLUMN resource_md5 CHARACTER(32);