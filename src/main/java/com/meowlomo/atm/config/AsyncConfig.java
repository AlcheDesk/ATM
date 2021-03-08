package com.meowlomo.atm.config;

import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer{
    
    @Value("${meowlomo.atm.scheduler.pool-size:20}")
    private int ASYNC_POOL_SIZE;
    
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();

        threadPoolTaskScheduler.setPoolSize(ASYNC_POOL_SIZE);
        threadPoolTaskScheduler.setThreadNamePrefix("Async-T");
        threadPoolTaskScheduler.initialize();
        
        return threadPoolTaskScheduler;
    }
    
}
