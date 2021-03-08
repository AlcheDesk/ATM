package com.meowlomo.atm.listener;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.PoolException;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import com.meowlomo.atm.cache.RedisService;

@Component
public class ApplicationPreperationListener implements ApplicationListener<ApplicationReadyEvent>{
    
    static final Logger logger = LoggerFactory.getLogger(DataMappingInitListener.class);

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private Environment environment;
    
    @Autowired
    private ApplicationContext appContext;
    
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //fluash all key for he application when starting the applciation
        try {
            redisConnectionFactory.getConnection().flushAll();
            RedisService.REDIS_CONECTION_OK.set(true);
        }catch (PoolException exception) {
            //check the active profile
            String[] profiles = this.environment.getActiveProfiles();
            //check if contains prod
            boolean containsProd = Arrays.stream(profiles).anyMatch("prod"::equalsIgnoreCase);
            if (containsProd) {
                logger.error("Can't connection to Redis server", exception);
                logger.error("Applciation is shutting down due to Redis connection error");
                SpringApplication.exit(appContext, new ExitCodeGenerator() {
                    public int getExitCode() {
                        // no errors
                        return 0;
                    }
                });
            }
            else {
                RedisService.REDIS_CONECTION_OK.set(false);
                logger.warn("Can't connection to Redis server", exception);
                logger.warn("Active prifiles {}, Redis caching will be disabled.", Arrays.toString(profiles));
            }
        }
        
    }

}
