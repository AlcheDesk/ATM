-- reset the data
TRUNCATE test_case_share_folder, test_case_share_folder_test_case_link;
SELECT setval('test_case_share_folder_id_seq', 999, true); 
SELECT setval('test_case_share_folder_test_case_link_id_seq', 999, true); 

--redo the data
-- insert the data to the test case share folder
insert into test_case_share_folder(name, description, is_deleted)
(select * from 
(select name, comment, is_deleted from project where id in 
(
  select project_id from test_case where id in 
  (
    select ref_test_case_id from instruction where ref_test_case_id is not null
  )
)
group by name, comment, is_deleted) as a);

-- add the link data to the test case share folder and test case link table
insert into test_case_share_folder_test_case_link (test_case_share_folder_id, test_case_id)
select tcsf.id as tcsf_id, tc.id as tc_id from test_case_share_folder tcsf
inner join project pro on tcsf.name = pro.name
inner join test_case tc on pro.id = tc.project_id
where tc.id in (select ref_test_case_id from instruction where ref_test_case_id is not null);
