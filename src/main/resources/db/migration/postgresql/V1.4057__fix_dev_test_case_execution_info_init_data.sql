update 
  test_case_execution_info tcei
set
latest_dev_run_status_id = lr.status_id,
latest_dev_run_updated_at =lr.updated_at,
latest_dev_run_instruction_executed_count = (select count(id) from dev_instruction_result where run_id = lr.id),
latest_dev_run_instruction_pass_count = (select count(id) from dev_instruction_result where run_id = lr.id and status_id = 3),
latest_dev_run_source_ip = lr.trigger_source,
latest_dev_run_created_at = lr.created_at,
latest_dev_run_id = lr.id,
latest_dev_run_executable_instruction_number = (select count(id) from instruction where test_case_id = lr.test_case_id and instruction_type_id not in (4,7))
from 
 (
 select r.* from run r 
 inner join test_case_execution_info tc
 on tc.test_case_id = r.test_case_id
 where r.run_type_id = 2 order by r.id
 ) as lr
 where lr.test_case_id = tcei.test_case_id