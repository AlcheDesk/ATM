CREATE TABLE
    driver_pack
    (
        id bigserial NOT NULL,
        name text NOT NULL,
        created_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        updated_at TIMESTAMP WITH TIME zone DEFAULT now() NOT NULL,
        execution_number bigint DEFAULT 0 NOT NULL,
        COMMENT text,
        PRIMARY KEY (id),
        CONSTRAINT driver_pack_ix_name UNIQUE (name)
    );
    
CREATE TABLE 
    driver_pack_driver_link 
    (
        id bigserial NOT NULL,
        driver_pack_id bigint NOT NULL,
        driver_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT driver_pack_driver_link_ix_driver_pack_driver UNIQUE (driver_pack_id, driver_id),
        CONSTRAINT driver_pack_driver_link_fk_driver_pack FOREIGN KEY (driver_pack_id) REFERENCES driver_pack(id),
        CONSTRAINT driver_pack_driver_link_fk_driver FOREIGN KEY (driver_id) REFERENCES driver(id)
    );