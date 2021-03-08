CREATE TABLE tenant
    (
        id bigserial NOT NULL,
        username text NOT NULL,
        uuid uuid NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT tenant_ix_uuid UNIQUE (uuid)
    );
    
SELECT setval('tenant_id_seq', 9999999, true);