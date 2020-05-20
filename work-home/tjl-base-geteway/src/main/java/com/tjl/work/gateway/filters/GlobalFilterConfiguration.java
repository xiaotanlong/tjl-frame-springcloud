package com.tjl.work.gateway.filters;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能描述
 * @author tjl
 * @Type GlobalFilterConfiguration
 * @date 2020/5/20 15:30
 * @Version 1.0
 */
@Configuration
public class GlobalFilterConfiguration {
    @Bean
    public GlobalFilter getIpAddressGlobalFilter(){
        System.out.println("================IpAddressGlobalFilter init =============");
        return new IpAddressGlobalFilter();
    }

    @Bean
    public GlobalFilter getTokenGlobalFilter(){
        System.out.println("================TokenGlobalFilter init =============");
        return new TokenGlobalFilter();
    }
}
