package com.tjl.work.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 功能描述:
 * @author tjl
 * @date 2020/4/30
 */
@SpringBootApplication
@EnableEurekaClient
public class TjlBaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TjlBaseServiceApplication.class, args);
    }

}
