package com.tjl.work.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jianglong.Tan
 * @Date: 2019/8/27 15:31
 * @Description: 测试请求
 */
@RestController
public class BaseController {

    @RequestMapping("/hystrixFallback")
    public String hystrixFallback() {
        return "This is a fallback";
    }
}
