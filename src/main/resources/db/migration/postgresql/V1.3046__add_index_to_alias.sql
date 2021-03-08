ALTER TABLE alias DROP CONSTRAINT alias_ix_name;

CREATE UNIQUE INDEX alias_unique_index_name_is_deleted
    ON alias USING btree
    (name COLLATE pg_catalog."default" ASC NULLS LAST)
    TABLESPACE pg_default    WHERE is_deleted IS FALSE
;