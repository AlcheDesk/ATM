UPDATE property SET key = 'meowlomo.atm.report.runSetResult.template.freemarker' WHERE key = 'meowlomo.atm.report.runSetResult.template';

UPDATE property SET value = 'run_set_result_default_freemarker' WHERE key = 'meowlomo.atm.report.runSetResult.template.freemarker';

INSERT INTO property(id, key, value, is_active, is_predefined)
	VALUES (25, 'meowlomo.atm.report.runSetResult.template.thymeleaf', 'run_set_result_default_thymeleaf', true, true);