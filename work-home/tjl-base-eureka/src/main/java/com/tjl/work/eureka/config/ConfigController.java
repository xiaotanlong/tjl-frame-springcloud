package com.tjl.work.eureka.config;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述
 *
 * @author tanjianglong
 * @Type ConfigController
 * @date 2020/4/23 16:00
 * @Version 1.0
 */
@RequestMapping
public class ConfigController {
    @RequestMapping("/error")
    public String errorPath(){
        return "for error";
    }
}
