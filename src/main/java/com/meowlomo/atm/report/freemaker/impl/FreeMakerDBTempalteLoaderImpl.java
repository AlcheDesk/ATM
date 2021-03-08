package com.meowlomo.atm.report.freemaker.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.core.model.TemplateExample;
import com.meowlomo.atm.core.service.base.TemplateService;
import com.meowlomo.atm.report.freemaker.FreeMakerDBTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class FreeMakerDBTempalteLoaderImpl implements FreeMakerDBTemplateLoader {

    @Autowired
    private TemplateService templateService;

    @Value("${meowlomo.atm.freemaker.encoding:UTF-8}")
    private String FREEMAKER_TEMPLATE_ENCODING;

    @Override
    public Template loadTempalte(String tempalteIndetifier) {
        // we need to find the template from the database first
        TemplateExample templateExample = new TemplateExample();
        templateExample.createCriteria().andNameEqualTo(tempalteIndetifier);
        List<com.meowlomo.atm.core.model.Template> templates = templateService.selectByExample(templateExample);
        if (templates.isEmpty()) {
            return null;
        }
        else {
            // get the first one from the tempalte
            com.meowlomo.atm.core.model.Template template = templates.get(0);
            // convert the template to freemaker template
            try {
                Configuration cfg = new Configuration(Configuration.getVersion());
                cfg.setDefaultEncoding(FREEMAKER_TEMPLATE_ENCODING);
                return new Template(tempalteIndetifier, template.getContent(), cfg);
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
        }
    }

}
