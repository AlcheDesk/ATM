INSERT INTO element_action (name) VALUES ('execute') ON CONFLICT DO NOTHING;
INSERT INTO element_type_element_action_link (element_type_id,element_action_id)
VALUES ((select id from element_type where name = 'sql'),(select id from element_action where name = 'execute')) ON CONFLICT DO NOTHING;


INSERT INTO properties (key,value,preloaded) values ('emsBaseUrl','http://localhost:8090',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.group','DEFAULT',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.interactive','true',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.status','NEW',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.timeout','100',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.maxRetry','1',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.retryNumber','1',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.cpuCore','2',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.ram','4',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.operatingSystem','*',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.priority','1',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.job.type','ATM',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.job.group','DEFAULT',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.job.priority','1',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.job.status','NEW',true) ON CONFLICT DO NOTHING;
INSERT INTO properties (key,value,preloaded) values ('meowlomo.ems.task.type','JSON',true) ON CONFLICT DO NOTHING;
