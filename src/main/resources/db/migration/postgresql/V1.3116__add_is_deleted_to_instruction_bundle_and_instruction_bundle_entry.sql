ALTER TABLE instruction_bundle
    ADD COLUMN is_deleted boolean NOT NULL DEFAULT False;

ALTER TABLE instruction_bundle_entry
    ADD COLUMN is_deleted boolean NOT NULL DEFAULT False;