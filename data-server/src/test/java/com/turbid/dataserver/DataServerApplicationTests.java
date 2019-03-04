package com.turbid.dataserver;

import com.turbid.basicapi.tools.MD5;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataServerApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    //直接使用redisTemplate存取字符串
    public void setAndGet() {
        redisTemplate.opsForValue().set("test:set", "testValue1");
        Assert.assertEquals("testValue1", redisTemplate.opsForValue().get("test:set"));
    }

    @Test
    //直接使用redisTemplate存取对象
    public void setAndGetAUser() {

//        redisTemplate.opsForValue().set("test:setUser", user);
//        Assert.assertEquals(user.getUsername(), ((User) redisTemplate.opsForValue().get("test:setUser")).getUsername());
    }

    @Test
    //使用Redis缓存对象，getUser只会被调用一次
    public void testCache() {
//        User user;
//        System.out.println(new Date());
//        user = userService.selectUserByID("17312460001");
//        System.out.println(new Date());
//        user = userService.selectUserByID("17312460001");
//        System.out.println(new Date());
//        user = userService.selectUserByID("17312460001");
//        System.out.println(new Date());
//        System.out.println(user);
        System.out.println(MD5.strAdMd5("123456"));
    }


}
