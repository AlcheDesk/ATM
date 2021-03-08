package com.meowlomo.atm.report.freemaker;

import freemarker.template.Template;

public interface FreeMakerDBTemplateLoader {
    Template loadTempalte(String tempalteIndetifier);
}
