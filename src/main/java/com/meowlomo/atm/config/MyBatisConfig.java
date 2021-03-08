package com.meowlomo.atm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan("com.meowlomo.atm.core.mapper")
public class MyBatisConfig {

    private final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);

    public MyBatisConfig() {
        logger.info("mybatis configuration class has been constructed.");
    }

}
