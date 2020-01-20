package com.tjl.work.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TjlBaseServiceApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("tjl","tjl-redis",10000, TimeUnit.MINUTES);
    }

}
