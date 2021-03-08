CREATE TABLE api_schema
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        type text NOT NULL,
        json_schema json,
        xml_schema xml,
        PRIMARY KEY (id),
        CONSTRAINT api_schema_ix_name UNIQUE (name),
        CONSTRAINT api_schema_ck_type CHECK (type IN ('JSON','XML'))
    );
    
ALTER TABLE "instruction" ADD COLUMN xml_schema xml;

ALTER TABLE "instruction" DROP COLUMN "request_headers";
ALTER TABLE "instruction" DROP COLUMN "query_parameters";
ALTER TABLE "instruction" ADD COLUMN request_headers json;
ALTER TABLE "instruction" ADD COLUMN query_parameters json;