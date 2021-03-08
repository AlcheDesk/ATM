-- create table color
CREATE TABLE color(id bigserial NOT NULL,name text NOT NULL,PRIMARY KEY (id))
WITH (OIDS = FALSE);

-- ALTER TABLE color OWNER to testadmin;

INSERT INTO color (name) VALUES ('蓝') ON CONFLICT DO NOTHING;
INSERT INTO color (name) VALUES ('绿') ON CONFLICT DO NOTHING;
INSERT INTO color (name) VALUES ('橙') ON CONFLICT DO NOTHING;
INSERT INTO color (name) VALUES ('红') ON CONFLICT DO NOTHING;
INSERT INTO color (name) VALUES ('灰') ON CONFLICT DO NOTHING;

-- add column color_id in table element and instruction
ALTER TABLE public.element ADD COLUMN color_id bigint;

ALTER TABLE element ADD CONSTRAINT element_fkey_color FOREIGN KEY (color_id)
    REFERENCES color (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE instruction ADD CONSTRAINT instruction_fkey_color FOREIGN KEY (color_id)
    REFERENCES color (id) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;

-- insert instruction_type
INSERT INTO instruction_type (name) VALUES ('注释') ON CONFLICT DO NOTHING;

-- drop instruction action not null
ALTER TABLE instruction ALTER COLUMN action DROP NOT NULL;