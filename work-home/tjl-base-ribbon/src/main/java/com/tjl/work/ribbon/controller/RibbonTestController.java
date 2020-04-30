package com.tjl.work.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 功能描述
 * @author tjl
 * @Type RibbonTestController
 * @date 2020/4/29 16:11
 * @Version 1.0
 */
@RestController
public class RibbonTestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 功能描述:测试 服务之间使用ribbon进行调用（消费者模式）
     * 调用 BASE-SERVICE 服务的 /user/test接口
     * @return java.lang.String
     * @date 2020/4/29
     */
    @GetMapping(value = "/test")
    public String test() {
        return restTemplate.getForEntity("http://BASE-SERVICE/user/test", String.class).getBody();
    }
}
