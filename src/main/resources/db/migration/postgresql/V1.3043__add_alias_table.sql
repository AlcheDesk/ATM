CREATE TABLE alias
	(
	    id bigserial,
	    name text NOT NULL,
	    comment text,
	    updated_at timestamp with time zone NOT NULL,
	    created_at timestamp with time zone NOT NULL,
	    is_deleted BOOLEAN DEFAULT false NOT NULL,
	    PRIMARY KEY (id)
	);

CREATE TABLE run_set_alias_link
    (
        id bigserial,
        run_set_id bigint,
        alias_id bigint,
        PRIMARY KEY (id),
        CONSTRAINT run_set_alias_link_fk_run_set FOREIGN KEY (run_set_id) REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_alias_link_fk_alias FOREIGN KEY (alias_id) REFERENCES "alias" ("id"),
        CONSTRAINT run_set_alias_link_ix_run_set_alias UNIQUE (run_set_id, alias_id)
    );