package com.meowlomo.atm.notification.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.meowlomo.atm.notification.service.TemplateMessageContentBuilder;

@Service
public class TemplateContentBuilder implements TemplateMessageContentBuilder {

	@SuppressWarnings("unused")
	private final Logger logger = LoggerFactory.getLogger(TemplateContentBuilder.class);

	@Autowired
	private SpringTemplateEngine templateEngine;

//	@Autowired
//	private TemplateService templateService;

	@Override
	public String buildMessage(String templateName, Map<String, Object> templateDate) {
		Context context = new Context(null, templateDate);
		return templateEngine.process("db:"+templateName, context);
	}

}
