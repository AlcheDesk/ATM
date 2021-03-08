package com.meowlomo.atm.report.freemaker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public interface FreeMarkerUtilService {
    // Template loadTempalte(String tempalteIndetifier);

    Template getTemplate(String name)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException;

    void print(String name, Map<String, Object> root);

    String fprint(String name, Map<String, Object> root, String outFile);

    String fprintWithoutFile(Map<String, Object> root, String templateName);

}
