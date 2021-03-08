CREATE TABLE multi_tenancy_ignorance
    (
        id bigserial NOT NULL,
        table_name text NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT multi_tenancy_ignorance_ix_table_name UNIQUE (table_name)
    );