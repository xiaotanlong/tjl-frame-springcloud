package com.tjl.work.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 功能描述 全局过滤器的包装类，将全局路由包装成统一的网关过滤器
 * @author tjl
 * @Type GatewayFilterAdapter
 * @date 2020/5/20 17:05
 * @Version 1.0
 */
public class GatewayFilterAdapter implements GatewayFilter {

    /**
     * 全局过滤器
     */
    private final GlobalFilter delegate;

    public GatewayFilterAdapter(GlobalFilter delegate) {
        this.delegate = delegate;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return this.delegate.filter(exchange, chain);
    }
}
