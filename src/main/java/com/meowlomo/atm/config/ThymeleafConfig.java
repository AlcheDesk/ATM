package com.meowlomo.atm.config;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.google.common.collect.Sets;
import com.meowlomo.atm.core.service.base.TemplateService;
import com.meowlomo.atm.report.thymeleaf.resolver.ThymeleafDatabaseResourceResolver;

@Configuration
@Import(ThymeleafAutoConfiguration.class)
public class ThymeleafConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private SpringTemplateEngine templateEngine;
    @Autowired
    private ThymeleafDatabaseResourceResolver dbResolver;
    @Autowired
    TemplateService templateService;

    @Bean
    public SpringTemplateEngine customTemplateEngine() {    	
    	dbResolver.setResolvablePatterns(Sets.newHashSet("db:" + "*"));
        dbResolver.setOrder(1);
        dbResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine.addTemplateResolver(dbResolver);
        Set<ITemplateResolver> resolvers = this.templateEngine.getTemplateResolvers();
        for (ITemplateResolver resolver : resolvers) {
        	logger.info("Template engine has resolver : " + resolver.getName() + " with order " + resolver.getOrder());
        }
        return this.templateEngine;
    }
    
//    @Bean
//    public ITemplateResolver DBResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setPrefix("static/client");
//        // For Spring Boot
//        // resolver.setPrefix("classpath:/templates/");
//        resolver.setSuffix(".html");
//        resolver.setTemplateMode("HTML5");
//        resolver.setCacheable(true);
//        resolver.
//        return resolver;
//    } 
	
}
