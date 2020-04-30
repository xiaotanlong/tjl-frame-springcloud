package com.tjl.work.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 功能描述: gateway demo
 * @author tjl
 * @date: 2019/8/27
 */
@SpringBootApplication
@EnableEurekaClient
public class TjlBaseGetewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TjlBaseGetewayApplication.class, args);
    }

}
