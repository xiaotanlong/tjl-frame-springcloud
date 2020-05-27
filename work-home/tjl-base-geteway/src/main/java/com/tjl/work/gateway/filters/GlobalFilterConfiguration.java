package com.tjl.work.gateway.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 功能描述
 * @author tjl
 * @Type GlobalFilterConfiguration
 * @date 2020/5/20 15:30
 * @Version 1.0
 */
@Configuration
public class GlobalFilterConfiguration {

    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public GlobalFilter getIpAddressGlobalFilter(){
        System.out.println("================IpAddressGlobalFilter init =============");
        return new IpAddressGlobalFilter();
    }

    @Bean
    public GlobalFilter getTokenGlobalFilter(){
        System.out.println("================TokenGlobalFilter init =============");
        return new TokenGlobalFilter(redisTemplate);
    }
}
