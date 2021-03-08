UPDATE element_locator_type SET name='Name_tmp' WHERE name='Name';
UPDATE element_locator_type SET name='CSS_temp' WHERE name='CSS';
UPDATE element_locator_type SET name='Tag_temp' WHERE name='Tag';
UPDATE element_locator_type SET name='XPath_temp' WHERE name='XPath';
UPDATE element_locator_type SET name='ID_temp' WHERE name='ID';
UPDATE element_locator_type SET name='Linktext_temp' WHERE name='Linktext';
UPDATE element_locator_type SET name='JDBC_temp' WHERE name='JDBC';

UPDATE element_locator_type SET name='Name' WHERE id=1;
UPDATE element_locator_type SET name='CSS' WHERE id=2;
UPDATE element_locator_type SET name='Tag' WHERE id=3;
UPDATE element_locator_type SET name='XPath' WHERE id=4;
UPDATE element_locator_type SET name='ID' WHERE id=5;
UPDATE element_locator_type SET name='Linktext' WHERE id=6;
UPDATE element_locator_type SET name='JDBC' WHERE id=7;

DELETE FROM element_locator_type WHERE name='Name_tmp';
DELETE FROM element_locator_type WHERE name='CSS_temp';
DELETE FROM element_locator_type WHERE name='Tag_temp';
DELETE FROM element_locator_type WHERE name='XPath_temp';
DELETE FROM element_locator_type WHERE name='ID_temp';
DELETE FROM element_locator_type WHERE name='Linktext_temp';
DELETE FROM element_locator_type WHERE name='JDBC_temp';

---------------------------------------------------------
UPDATE instruction_type SET name='Functional_temp' WHERE name='Functional';
UPDATE instruction_type SET name='Performance_temp' WHERE name='Performance';
UPDATE instruction_type SET name='API_temp' WHERE name='API';
UPDATE instruction_type SET name='Manual_temp' WHERE name='Manual';
UPDATE instruction_type SET name='APP_temp' WHERE name='APP';
UPDATE instruction_type SET name='Reference_temp' WHERE name='Reference';
UPDATE instruction_type SET name='Comment_temp' WHERE name='Comment';

UPDATE instruction_type SET name='Functional' WHERE id=1;
UPDATE instruction_type SET name='Performance' WHERE id=2;
UPDATE instruction_type SET name='API' WHERE id=3;
UPDATE instruction_type SET name='Manual' WHERE id=4;
UPDATE instruction_type SET name='APP' WHERE id=5;
UPDATE instruction_type SET name='Reference' WHERE id=6;
UPDATE instruction_type SET name='Comment' WHERE id=7;

DELETE FROM instruction_type WHERE name='Functional_temp';
DELETE FROM instruction_type WHERE name='Performance_temp';
DELETE FROM instruction_type WHERE name='API_temp';
DELETE FROM instruction_type WHERE name='Manual_temp';
DELETE FROM instruction_type WHERE name='APP_temp';
DELETE FROM instruction_type WHERE name='Reference_temp';
DELETE FROM instruction_type WHERE name='Comment_temp';

-----------------------------------------------------------
UPDATE element_type SET name='Button_temp' WHERE name='Button';
UPDATE element_type SET name='Link_temp' WHERE name='Link';
UPDATE element_type SET name='Dropdown_temp' WHERE name='Dropdown';
UPDATE element_type SET name='Radio_temp' WHERE name='Radio';
UPDATE element_type SET name='Checkbox_temp' WHERE name='Checkbox';
UPDATE element_type SET name='Table_temp' WHERE name='Table';
UPDATE element_type SET name='FileDown_temp' WHERE name='FileDown';
UPDATE element_type SET name='FileUp_temp' WHERE name='FileUp';
UPDATE element_type SET name='Frame_temp' WHERE name='Frame';
UPDATE element_type SET name='Browser_temp' WHERE name='Browser';
UPDATE element_type SET name='Textbox_temp' WHERE name='Textbox';
UPDATE element_type SET name='SQL_temp' WHERE name='SQL';

UPDATE element_type SET name='Button' WHERE id=1;
UPDATE element_type SET name='Link' WHERE id=2;
UPDATE element_type SET name='Dropdown' WHERE id=3;
UPDATE element_type SET name='Radio' WHERE id=4;
UPDATE element_type SET name='Checkbox' WHERE id=5;
UPDATE element_type SET name='Table' WHERE id=6;
UPDATE element_type SET name='FileDown' WHERE id=7;
UPDATE element_type SET name='FileUp' WHERE id=8;
UPDATE element_type SET name='Frame' WHERE id=9;
UPDATE element_type SET name='Browser' WHERE id=10;
UPDATE element_type SET name='Textbox' WHERE id=11;
UPDATE element_type SET name='SQL' WHERE id=12;

DELETE FROM element_type WHERE name='Button_temp';
DELETE FROM element_type WHERE name='Link_temp';
DELETE FROM element_type WHERE name='Dropdown_temp';
DELETE FROM element_type WHERE name='Radio_temp';
DELETE FROM element_type WHERE name='Checkbox_temp';
DELETE FROM element_type WHERE name='Table_temp';
DELETE FROM element_type WHERE name='FileDown_temp';
DELETE FROM element_type WHERE name='FileUp_temp';
DELETE FROM element_type WHERE name='Frame_temp';
DELETE FROM element_type WHERE name='Browser_temp';
DELETE FROM element_type WHERE name='Textbox_temp';
DELETE FROM element_type WHERE name='SQL_temp';
-----------------------------------------------------------
UPDATE element_action SET name='Click_temp' WHERE name='Click';
UPDATE element_action SET name='Enter_temp' WHERE name='Enter';
UPDATE element_action SET name='Modify_temp' WHERE name='Modify';
UPDATE element_action SET name='Clear_temp' WHERE name='Clear';
UPDATE element_action SET name='EnterTextReadOnly_temp' WHERE name='EnterTextReadOnly';
UPDATE element_action SET name='Select_temp' WHERE name='Select';
UPDATE element_action SET name='Check_temp' WHERE name='Check';
UPDATE element_action SET name='Download_temp' WHERE name='Download';
UPDATE element_action SET name='Upload_temp' WHERE name='Upload';
UPDATE element_action SET name='Wait_temp' WHERE name='Wait';
UPDATE element_action SET name='Navigate_temp' WHERE name='Navigate';
UPDATE element_action SET name='Exist_temp' WHERE name='Exist';
UPDATE element_action SET name='Verify_temp' WHERE name='Verify';
UPDATE element_action SET name='SwitchToFrame_temp' WHERE name='SwitchToFrame';
UPDATE element_action SET name='Sleep_temp' WHERE name='Sleep';
UPDATE element_action SET name='Back_temp' WHERE name='Back';
UPDATE element_action SET name='Count_temp' WHERE name='Count';
UPDATE element_action SET name='isDisabled_temp' WHERE name='isDisabled';
UPDATE element_action SET name='Execute_temp' WHERE name='Execute';

UPDATE element_action SET name='Click' WHERE id=1;
UPDATE element_action SET name='Enter' WHERE id=2;
UPDATE element_action SET name='Modify' WHERE id=3;
UPDATE element_action SET name='Clear' WHERE id=4;
UPDATE element_action SET name='EnterTextReadOnly' WHERE id=5;
UPDATE element_action SET name='Select' WHERE id=6;
UPDATE element_action SET name='Check' WHERE id=7;
UPDATE element_action SET name='Download' WHERE id=8;
UPDATE element_action SET name='Upload' WHERE id=9;
UPDATE element_action SET name='Wait' WHERE id=10;
UPDATE element_action SET name='Navigate' WHERE id=11;
UPDATE element_action SET name='Exist' WHERE id=12;
UPDATE element_action SET name='Verify' WHERE id=13;
UPDATE element_action SET name='SwitchToFrame' WHERE id=14;
UPDATE element_action SET name='Sleep' WHERE id=15;
UPDATE element_action SET name='Back' WHERE id=16;
UPDATE element_action SET name='Count' WHERE id=17;
UPDATE element_action SET name='isDisabled' WHERE id=18;
UPDATE element_action SET name='Execute' WHERE id=19;

DELETE FROM element_action WHERE name='Click_temp';
DELETE FROM element_action WHERE name='Enter_temp';
DELETE FROM element_action WHERE name='Modify_temp';
DELETE FROM element_action WHERE name='Clear_temp';
DELETE FROM element_action WHERE name='EnterTextReadOnly_temp';
DELETE FROM element_action WHERE name='Select_temp';
DELETE FROM element_action WHERE name='Check_temp';
DELETE FROM element_action WHERE name='Download_temp';
DELETE FROM element_action WHERE name='Upload_temp';
DELETE FROM element_action WHERE name='Wait_temp';
DELETE FROM element_action WHERE name='Navigate_temp';
DELETE FROM element_action WHERE name='Exist_temp';
DELETE FROM element_action WHERE name='Verify_temp';
DELETE FROM element_action WHERE name='SwitchToFrame_temp';
DELETE FROM element_action WHERE name='Sleep_temp';
DELETE FROM element_action WHERE name='Back_temp';
DELETE FROM element_action WHERE name='Count_temp';
DELETE FROM element_action WHERE name='isDisabled_temp';
DELETE FROM element_action WHERE name='Execute_temp';
-------------------------------------------------------------
UPDATE project_type SET name='Individual_temp' WHERE name='Individual';
UPDATE project_type SET name='Group_temp' WHERE name='Individual';

UPDATE project_type SET name='Individual' WHERE id=1;
UPDATE project_type SET name='Group' WHERE id=2;

DELETE FROM project_type WHERE name='Individual_temp';
DELETE FROM project_type WHERE name='Group_temp';
-------------------------------------------------------------
UPDATE run_set_type SET name='ATM_temp' WHERE name='ATM';
UPDATE run_set_type SET name='External_temp' WHERE name='External';

UPDATE run_set_type SET name='ATM' WHERE id=1;
UPDATE run_set_type SET name='External' WHERE id=2;

DELETE FROM run_set_type WHERE name='ATM_temp';
DELETE FROM run_set_type WHERE name='External_temp';
--------------------------------------------------------------
UPDATE file_type SET name='Screenshot_temp' WHERE name='Screenshot';
UPDATE file_type SET name='Log_temp' WHERE name='Log';

UPDATE file_type SET name='Screenshot' WHERE id=1;
UPDATE file_type SET name='Log' WHERE id=2;

DELETE FROM file_type WHERE name='Screenshot_temp';
DELETE FROM file_type WHERE name='Log_temp';
---------------------------------------------------------------
UPDATE color SET name='BLUE_temp' WHERE name='BLUE';
UPDATE color SET name='GREEN_temp' WHERE name='GREEN';
UPDATE color SET name='ORANGE_temp' WHERE name='ORANGE';
UPDATE color SET name='RED_temp' WHERE name='RED';
UPDATE color SET name='GREY_temp' WHERE name='GREY';

UPDATE color SET name='BLUE' WHERE id=1;
UPDATE color SET name='GREEN' WHERE id=2;
UPDATE color SET name='ORANGE' WHERE id=3;
UPDATE color SET name='RED' WHERE id=4;
UPDATE color SET name='GREY' WHERE id=5;

DELETE FROM color WHERE name='BLUE_temp';
DELETE FROM color WHERE name='GREEN_temp';
DELETE FROM color WHERE name='ORANGE_temp';
DELETE FROM color WHERE name='RED_temp';
DELETE FROM color WHERE name='GREY_temp';
---------------------------------------------------------------
UPDATE "group" SET name='Default_temp' WHERE name='Default';
UPDATE "group" SET name='DEV_temp' WHERE name='DEV';
UPDATE "group" SET name='Test_temp' WHERE name='Test';
UPDATE "group" SET name='CI_temp' WHERE name='CI';

UPDATE "group" SET name='Default' WHERE id=1;
UPDATE "group" SET name='DEV' WHERE id=2;
UPDATE "group" SET name='Test' WHERE id=3;
UPDATE "group" SET name='CI' WHERE id=4;

DELETE FROM "group" WHERE name='Default_temp';
DELETE FROM "group" WHERE name='DEV_temp';
DELETE FROM "group" WHERE name='Test_temp';
DELETE FROM "group" WHERE name='CI_temp';
--------------------------------------------------------------
UPDATE log_level SET name='N/A_temp' WHERE name='N/A';
UPDATE log_level SET name='PASS_temp' WHERE name='PASS';
UPDATE log_level SET name='FAIL_temp' WHERE name='FAIL';
UPDATE log_level SET name='WIP_temp' WHERE name='WIP';
UPDATE log_level SET name='ERROR_temp' WHERE name='ERROR';
UPDATE log_level SET name='INFO_temp' WHERE name='INFO';
UPDATE log_level SET name='WARNING_temp' WHERE name='WARNING';
UPDATE log_level SET name='DEBUG_temp' WHERE name='DEBUG';
UPDATE log_level SET name='TRACE_temp' WHERE name='TRACE';

UPDATE log_level SET name='N/A' WHERE id=1;
UPDATE log_level SET name='PASS' WHERE id=2;
UPDATE log_level SET name='FAIL' WHERE id=3;
UPDATE log_level SET name='WIP' WHERE id=4;
UPDATE log_level SET name='ERROR' WHERE id=5;
UPDATE log_level SET name='INFO' WHERE id=6;
UPDATE log_level SET name='WARNING' WHERE id=7;
UPDATE log_level SET name='DEBUG' WHERE id=8;
UPDATE log_level SET name='TRACE' WHERE id=9;

DELETE FROM log_level WHERE name='N/A_temp';
DELETE FROM log_level WHERE name='PASS_temp';
DELETE FROM log_level WHERE name='FAIL_temp';
DELETE FROM log_level WHERE name='WIP_temp';
DELETE FROM log_level WHERE name='ERROR_temp';
DELETE FROM log_level WHERE name='INFO_temp';
DELETE FROM log_level WHERE name='WARNING_temp';
DELETE FROM log_level WHERE name='DEBUG_temp';
DELETE FROM log_level WHERE name='TRACE_temp';
