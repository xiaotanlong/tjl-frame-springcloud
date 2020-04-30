package com.tjl.work.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tjl
 * @Date: 2019/8/27 15:31
 * @Description: 测试请求
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @RequestMapping("/test")
    public String test() {
        return "order test get way";
    }
}
