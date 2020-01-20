package com.tjl.work.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jianglong.Tan
 * @Date: 2019/8/27 15:31
 * @Description: 测试请求
 */
@RestController
public class BaseController {

    @RequestMapping("/hystrixfallback")
    public String hystrixfallback() {
        return "This is a fallback";
    }
}
