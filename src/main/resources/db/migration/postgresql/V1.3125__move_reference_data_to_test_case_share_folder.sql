-- insert the data to the test case share folder
insert into test_case_share_folder(name, description)
(select * from 
(select name, comment from project where is_deleted is false and id in 
(
  select project_id from test_case where is_deleted is false and id in 
  (
    select test_case_id from instruction where ref_test_case_id is not null
  )
)
group by name, comment) as a);

-- add the link data to the test case share folder and test case link table
insert into test_case_share_folder_test_case_link (test_case_share_folder_id, test_case_id)
select tcsf.id as tcsf_id, tc.id as tc_id from test_case_share_folder tcsf
inner join project pro on tcsf.name = pro.name
inner join test_case tc on pro.id = tc.project_id
where pro.is_deleted is false and tc.is_deleted is false and tc.id in (select ref_test_case_id from instruction where is_deleted is false );