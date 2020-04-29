package com.tjl.work.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author tanjianglong
 * @Type ConfigController
 * @date 2020/4/23 16:06
 * @Version 1.0
 */
@RestController
public class ConfigController {
    @RequestMapping("/actuator/info")
    public String actuatorInfo() {
        return "tjl test server";
    }
}
