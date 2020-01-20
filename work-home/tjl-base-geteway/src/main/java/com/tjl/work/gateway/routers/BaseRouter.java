package com.tjl.work.gateway.routers;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jianglong.Tan
 * @Date: 2019/8/27 15:14
 * @Description: 代码 配置 路由demo
 */
@Configuration
public class BaseRouter {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/baidu").uri("http://baidu.com:80/"))
                .route("2",r -> r.path("/baidu2").uri("http://baidu.com:80/"))
                .build();
    }
}
