package com.tjl.work.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: jianglong.Tan
 * @Date: 2019/8/27 15:31
 * @Description: 测试请求
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/test")
    public String test() {
        return "user test get way";
    }
}
