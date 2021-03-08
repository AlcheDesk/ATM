package com.meowlomo.atm.config;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Configuration
public class JerseyConfig extends ResourceConfig {

    private final Logger logger = LoggerFactory.getLogger(JerseyConfig.class);

    @Value("${spring.jersey.application-path:/api}")
    private String apiPath;

    @Value("${meowlomo.atm.swaggerr.enable:false}")
    private boolean enableSwagger;
    
    @Inject
    private ApplicationContext appCtx;

    public JerseyConfig() {
        logger.info("Loading Jersey configuration.");
        // Register endpoints, providers, ...
    }

    @PostConstruct
    public void init() {
        // Register components
        logger.info("registering jersey resoures");
        this.registerEndpoints();
        if (enableSwagger) {
            logger.info("configuring Swagger");
            this.configureSwagger();
        }

    }

    private void registerEndpoints() {
        this.scanResources();
        if (enableSwagger) {
            this.register(WadlResource.class);
        }       
        this.register(JacksonFeature.class);
        this.register(LoggingFeature.class);
        this.register(RequestContextFilter.class);
    }

    private void configureSwagger() {
        // Available at localhost:port/api/swagger.json
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("ATM");
        config.setTitle("meowlomo Automated Test Management (ATM) API");
        config.setVersion("v1.4");
        config.setContact("meowlomo");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.meowlomo.atm.core.resource");
        config.setPrettyPrint(true);
        config.setScan(true);
    }

    private void scanResources() {
//        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        logger.info("Rest Endpoint classes found:");
        Map<String, Object> pathBeans = appCtx.getBeansWithAnnotation(Path.class);
        for (Object o : pathBeans.values()) {
            logger.info(" -> " + o.getClass().getName());
            register(o);
        }

        logger.info("Rest Provider classes found:");
        Map<String, Object> providerBeans = appCtx.getBeansWithAnnotation(Provider.class);
        for (Object o : providerBeans.values()) {
            logger.info(" -> " + o.getClass().getName());
            register(o);
        }
//        property("contextConfig", context);
    }
}