package com.tjl.work.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * 功能描述 token 统一检验 角色权限筛查
 * @author tjl
 * @Type TokenGlobalFilter
 * @date 2020/5/20 15:51
 * @Version 1.0
 */
public class TokenGlobalFilter implements GlobalFilter, Ordered {
    private Logger logger = LoggerFactory.getLogger(TokenGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders httpHeaders = exchange.getRequest().getHeaders();
        List<String> tokens = httpHeaders.get("x-auth-token");
        logger.info("=======全局过滤器 token is {}...", tokens);
        //TODO 基于登录成功存放的 token 获取当前用户的信息和角色权限
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
