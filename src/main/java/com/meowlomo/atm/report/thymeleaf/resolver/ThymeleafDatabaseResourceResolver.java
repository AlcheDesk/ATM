package com.meowlomo.atm.report.thymeleaf.resolver;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.StringTemplateResolver;
import org.thymeleaf.templateresource.ITemplateResource;

import com.google.common.collect.Sets;
import com.meowlomo.atm.core.model.Template;
import com.meowlomo.atm.core.model.TemplateExample;
import com.meowlomo.atm.core.service.base.TemplateService;

@Component
public class ThymeleafDatabaseResourceResolver extends StringTemplateResolver {
    
    private final Logger logger = LoggerFactory.getLogger(ThymeleafDatabaseResourceResolver.class);

	@Autowired
	TemplateService templateService;

	private final static String PREFIX = "db:";

	public ThymeleafDatabaseResourceResolver() {
		setResolvablePatterns(Sets.newHashSet(PREFIX + "*"));
	}

	@Override
	protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, Map<String, Object> templateResolutionAttributes) {
		TemplateExample templateExample = new TemplateExample();
		templateExample.createCriteria().andNameEqualTo(template.replaceFirst(PREFIX, ""));
		List<Template> thymeleafTemplates = templateService.selectByExample(templateExample);
		if (thymeleafTemplates.isEmpty()) {
		    logger.error("Trying to get a nonexisting template by name "+ template);
			return null;
		} else {
		    Template templateObj = thymeleafTemplates.get(0);
			String thymeleafTemplate = templateObj.getContent();
			setTemplateMode(templateObj.getMode());
			return super.computeTemplateResource(configuration, ownerTemplate, thymeleafTemplate ,templateResolutionAttributes);
		}
	}
}
