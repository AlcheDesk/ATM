CREATE TABLE instruction_option_map
    (
        instruction_option_id bigint,
        instruction_type_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL,
        instruction_action_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL,
        element_type_ids bigint[] DEFAULT ARRAY[]::BIGINT[] NOT NULL,
        PRIMARY KEY (instruction_option_id),
        CONSTRAINT instruction_option_map_fk_instruction_option FOREIGN KEY (instruction_option_id)
        REFERENCES "instruction_option" ("id"),
        CONSTRAINT instruction_option_map_ix_instruction_option UNIQUE (instruction_option_id)
    );
----------------------------------------------------------------------------------------------------------------------------------
INSERT INTO instruction_option_map (instruction_option_id, instruction_type_ids, instruction_action_ids, element_type_ids)
SELECT insop.id, 
case when array_agg(distinct(itiol.instruction_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(itiol.instruction_type_id)) end, 
case when array_agg(distinct(iaiol.instruction_option_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(iaiol.instruction_option_id)) end, 
case when array_agg(distinct(etiol.element_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(etiol.element_type_id)) end 
from instruction_option insop
full outer join instruction_type_instruction_option_link itiol on itiol.instruction_option_id = insop.id
full outer join instruction_action_instruction_option_link iaiol on iaiol.instruction_option_id = insop.id
full outer join element_type_instruction_option_link etiol on etiol.instruction_option_id = insop.id
group by insop.id;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_option_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- instruction_option_map
	INSERT INTO instruction_option_map (instruction_option_id, instruction_type_ids, instruction_action_ids, element_type_ids) 
	VALUES (NEW.id, 
	        '{}'::bigint[],
	        '{}'::bigint[],
	        '{}'::bigint[]
	       );
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "instruction_option_after_insert_change_others"
  AFTER INSERT ON instruction_option
  FOR EACH ROW
EXECUTE PROCEDURE instruction_option_insert_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_type_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET instruction_type_ids = 
	(SELECT case when array_agg(distinct(link.instruction_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.instruction_type_id)) end
	from instruction_option insop
	full outer join instruction_type_instruction_option_link link on link.instruction_option_id = insop.id
	group by insop.id)
	WHERE id = NEW.instruction_option_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "instruction_type_instruction_option_link_change_others"
  AFTER INSERT OR UPDATE ON instruction_type_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE instruction_type_instruction_option_link_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "element_type_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET element_type_ids = 
	(SELECT case when array_agg(distinct(link.element_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.element_type_id)) end
	from instruction_option insop
	full outer join element_type_instruction_option_link link on link.instruction_option_id = insop.id
	group by insop.id)
	WHERE id = NEW.instruction_option_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "element_type_instruction_option_link_change_others"
  AFTER INSERT OR UPDATE ON element_type_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE element_type_instruction_option_link_change_others();
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_action_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET instruction_action_ids = 
	(SELECT case when array_agg(distinct(link.instruction_action_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.instruction_action_id)) end
	from instruction_option insop
	full outer join instruction_action_instruction_option_link link on link.instruction_option_id = insop.id
	group by insop.id)
	WHERE id = NEW.instruction_option_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
CREATE TRIGGER "instruction_action_instruction_option_link_change_others"
  AFTER INSERT OR UPDATE ON instruction_action_instruction_option_link
  FOR EACH ROW
EXECUTE PROCEDURE instruction_action_instruction_option_link_change_others();
----------------------------------------------------------------------------------------------------------------------------------
