package com.meowlomo.atm.config.multitenancy;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.google.common.collect.Lists;

@Configuration
@MapperScan("com.meowlomo.atm.core.mapper")
public class MybatisPlusConfig {

    public static List<String> IGNORE_TENANT_TABLES = Lists.newArrayList("multi_tenancy_ignorance");

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        return paginationInterceptor;
    }
}
