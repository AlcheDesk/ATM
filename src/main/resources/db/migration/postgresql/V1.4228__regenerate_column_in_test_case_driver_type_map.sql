UPDATE test_case_driver_type_map 
SET driver_types = i.dtm
FROM
(
select tsitmap.test_case_id as tci, array_agg(distinct(link.driver_type_id)) as dtm from driver_type_instruction_type_link link inner join
test_case_instruction_type_map tsitmap on link.instruction_type_id = ANY (tsitmap.instruction_types)
group by test_case_id
) i
WHERE i.tci = test_case_id;
