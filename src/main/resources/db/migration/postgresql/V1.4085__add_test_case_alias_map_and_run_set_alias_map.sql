CREATE TABLE run_set_alias_name_map
    (
        id bigserial NOT NULL,
        run_set_id bigint NOT NULL,
        alias_names text[] NOT NULL DEFAULT '{}',
        PRIMARY KEY (id),
        CONSTRAINT run_set_alias_name_map_fk_run_set FOREIGN KEY (run_set_id)
        REFERENCES "run_set" ("id"),
        CONSTRAINT run_set_alias_name_map_ix_run_set UNIQUE (run_set_id)
    );
-----------------------------------------------------------------------------
INSERT INTO multi_tenancy_ignorance (id, table_name) VALUES (54, 'run_set_alias_name_map');
-----------------------------------------------------------------------------
insert into run_set_alias_name_map (run_set_id, alias_names)
select rs.id as run_set_id, 
case
when array_agg(distinct(al.name)) = '{null}'::text[] then '{}'::text[]
else array_agg(distinct(al.name))
END
as alias_names from run_set rs
left join run_set_alias_link link
  on rs.id = link.run_set_id
left join alias al
  on link.alias_id = al.id
group by rs.id;
-----------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "run_set_insert_run_set_alias_name_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN			  
	insert into run_set_alias_name_map (run_set_id, alias_names) 
	values (NEW.id, 
	        '{}'::text[]
	       );
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "run_set_after_insert_run_set_alias_name_map"
  AFTER INSERT ON run_set
  FOR EACH ROW
EXECUTE PROCEDURE run_set_insert_run_set_alias_name_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsal_update_run_set_alias_name_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE run_set_alias_name_map
	SET 
	  alias_names = types.names
	FROM
	  (SELECT 
	  	case
		when array_agg(distinct(name)) = '{null}'::text[] then '{}'::text[]
		else array_agg(distinct(name))
		END
	  AS names FROM alias al
	  left join run_set_alias_link link
      on al.id = link.alias_id
	  WHERE al.is_deleted IS FALSE AND link.run_set_id = NEW.run_set_id) types
	WHERE 
	  run_set_id = NEW.run_set_id;
	RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "tcitm_after_update_run_set_alias_name_map"
  AFTER UPDATE OR INSERT ON run_set_alias_link
  FOR EACH ROW
EXECUTE PROCEDURE rsal_update_run_set_alias_name_map();
------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "rsal_delete_update_run_set_alias_name_map" ()  RETURNS trigger
  VOLATILE
AS $$
BEGIN
	UPDATE run_set_alias_name_map
	SET 
	  alias_names = types.names
	FROM
	  (SELECT 
		case
		when array_agg(distinct(name)) = '{null}'::text[] then '{}'::text[]
		else array_agg(distinct(name))
		END
	  AS names FROM alias al
	  left join run_set_alias_link link
      on al.id = link.alias_id
	  WHERE al.is_deleted IS FALSE AND link.run_set_id = OLD.run_set_id) types
	WHERE 
	  run_set_id = OLD.run_set_id;
	RETURN NULL;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER "rsal_delete_update_run_set_alias_name_map"
  AFTER DELETE ON run_set_alias_link
  FOR EACH ROW
EXECUTE PROCEDURE rsal_delete_update_run_set_alias_name_map();
------------------------------------------------------------------------------