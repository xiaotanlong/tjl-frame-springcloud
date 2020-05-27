package com.tjl.work.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 功能描述 用户模块路由的过滤器
 * @author tjl
 * @Type GatewayFilterForUserService
 * @date 2020/5/20 16:28
 * @Version 1.0
 */
public class GatewayFilterForUserService implements GatewayFilter {
    private Logger logger = LoggerFactory.getLogger(GatewayFilterForUserService.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        List<String> tokens =  exchange.getRequest().getHeaders().get("x-auth-token");
        logger.info("======= 作用于 用户服务的 过滤器=====token is {}...", tokens);
        return chain.filter(exchange);
    }
}
