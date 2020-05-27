package com.tjl.work.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tjl.work.core.web.ResponseVO;
import com.tjl.work.core.web.RetResponse;
import com.tjl.work.service.entity.UserDO;
import com.tjl.work.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tjl
 * @Date: 2019/8/27 15:31
 * @Description: 测试请求
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;


    @RequestMapping("/test")
    public String test() {
        System.out.println(userService.list());
        redisTemplate.opsForValue().set("tjl-test",userService.list().get(0));
        return "user test get way";
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserDO user) {
        if(user == null){
            return RetResponse.success("no user ! login error !");
        }

        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<UserDO>(user);
        UserDO userDO = userService.getOne(queryWrapper);
        if(userDO != null){
            redisTemplate.opsForValue().set("11", JSONObject.toJSONString(userDO));
        }
        return RetResponse.success(userDO);
    }

    @PostMapping("/getOne")
    public UserDO getOne(@RequestBody UserDO user) {
        return  userService.getById(user.getId());
    }
}
