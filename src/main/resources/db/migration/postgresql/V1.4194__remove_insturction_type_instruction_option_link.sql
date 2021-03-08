DROP TRIGGER "instruction_type_instruction_option_link_change_others" ON "instruction_type_instruction_option_link";
DROP FUNCTION "instruction_type_instruction_option_link_change_others" ( );
DROP TABLE "instruction_type_instruction_option_link";
ALTER TABLE "instruction_option_map" DROP COLUMN "instruction_type_ids";
CREATE OR REPLACE FUNCTION "public"."instruction_option_insert_change_others" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN
	------------------------------------
	-- instruction_option_map
	INSERT INTO instruction_option_map (instruction_option_id, instruction_action_ids, element_type_ids) 
	VALUES (NEW.id,
	        '{}'::bigint[],
	        '{}'::bigint[]
	       );
	------------------------------------  
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;