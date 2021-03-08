UPDATE "instruction_option_map" SET instruction_action_ids = i.ids
FROM
(
SELECT instruction_option_id as insoid, 
case when array_agg(distinct(instruction_action_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(instruction_action_id)) end as ids
from instruction_action_instruction_option_link group by instruction_option_id
) i
WHERE instruction_option_id = i.insoid;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "instruction_action_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET instruction_action_ids = 
	(SELECT case when array_agg(distinct(link.instruction_action_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.instruction_action_id)) end
	from instruction_action_instruction_option_link link where link.instruction_option_id = NEW.instruction_option_id
	group by link.instruction_option_id)
	WHERE instruction_option_id = NEW.instruction_option_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;
----------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION "element_type_instruction_option_link_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	UPDATE instruction_option_map SET element_type_ids = 
	(SELECT case when array_agg(distinct(link.element_type_id)) = '{null}'::bigint[] then '{}'::bigint[] else array_agg(distinct(link.element_type_id)) end
	from element_type_instruction_option_link link where link.instruction_option_id = NEW.instruction_option_id
	group by link.instruction_option_id)
	WHERE instruction_option_id = NEW.instruction_option_id;
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;