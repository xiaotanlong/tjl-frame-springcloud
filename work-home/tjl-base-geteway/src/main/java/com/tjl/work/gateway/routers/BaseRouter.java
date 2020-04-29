package com.tjl.work.gateway.routers;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: jianglong.Tan
 * @Date: 2019/8/27 15:14
 * @Description: 配置路由  可以通过访问Getway服务一个域名地址，然后由这个服务负责获取 注册中心的 服务列表 做路由分发
 * 可以统一处理相关安全测略，拦截日志
 */
@Configuration
public class BaseRouter {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/baidu").uri("http://baidu.com:80/"))
                .route(r -> r.path("/user/**").uri("lb://base-service"))
                .route(r -> r.path("/order/**").uri("lb://base-service"))
                .build();
    }
}
