CREATE OR REPLACE FUNCTION "public"."tcitm_insert_insert_test_case_driver_type_map" ()  RETURNS trigger
  VOLATILE
AS $body$
BEGIN			  
	insert into test_case_driver_type_map (test_case_id, driver_types) 
	values (NEW.test_case_id, 
	        (
	          select
	          CASE 
	            WHEN array_agg(distinct(link.driver_type_id)) is NULL THEN '{}'::bigint[] 
	            ELSE array_agg(distinct(link.driver_type_id))
	          END
	         from driver_type_instruction_type_link link where link.instruction_type_id = ANY (NEW.instruction_types))
	       );
	RETURN NEW;
END;
$body$ LANGUAGE plpgsql;

insert into test_case_instruction_type_map (test_case_id, instruction_types)
select test_case_id, 
CASE when array_agg(distinct(instruction_type_id)) is null THEN '{}'::bigint[] 
ELSE array_agg(distinct(instruction_type_id)) 
END as ins_types 
from instruction
where test_case_id in (
select tc.id from test_case tc FULL OUTER join test_case_instruction_type_map tcit on tc.id = tcit.test_case_id
where tcit.test_case_id is null
)
group by test_case_id;

insert into test_case_instruction_type_map (test_case_id)
select tc.id from test_case tc FULL OUTER join instruction ins on tc.id = ins.test_case_id
where ins.test_case_id is null and tc.id not in (select test_case_id from test_case_instruction_type_map);