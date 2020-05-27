package com.tjl.work.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 功能描述 全局ip过滤
 * @author tjl
 * @Type IpAddressGlobalFilter
 * @date 2020/5/20 15:25
 * @Version 1.0
 */
public class IpAddressGlobalFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(IpAddressGlobalFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String hostString = exchange.getRequest().getRemoteAddress().getHostString();
        //TODO 做ip白名单黑名单限制
        logger.info( "=========作用于全局 IpAddress is {}...======", hostString );
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
