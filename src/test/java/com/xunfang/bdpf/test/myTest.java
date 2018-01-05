package com.xunfang.bdpf.test;
import com.xunfang.bdpf.redis.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @author ylh
 * @version V1.0
 * @ClassName:
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * Copyright: Copyright (c) 2017
 * Company:深圳市讯方技术股份有限公司
 * @date 2017/12/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:applicationContext.xml","classpath:spring-redis.xml"})
public class myTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testList(){
        Jedis jedis = new Jedis("192.168.98.135");
        String pong = jedis.ping();
        System.out.println("=======远程redis连接成功 ====="+pong);
    }
    @Test
    public void RedisUtilTest(){


        redisUtil.set("hello","Scala is good");
        Set<String> keys =  redisUtil.getAllKeys();

        System.out.println("------redis keys number--------"+keys);

        redisUtil.remove("hello");
        System.out.println("------redis keys number--------"+redisUtil.getAllKeys());
    }


    @Test
    public void test2AddRedisList(){
        List<Map<String,String>> mapList = new ArrayList<>();

        for (int i = 1;i<5;i++){
            Map<String,String> map = new HashMap<>();
            map.put("k"+i,"v"+i);
            mapList.add(map);
        }
        System.out.println("mapList ------->"+mapList);

        redisUtil.set("key_1",mapList);

        redisUtil.addList("key_2",mapList);

        System.out.println(redisUtil.getAllKeys());

        List list1 = (List) redisUtil.get("key_1");
        List list2 = redisUtil.getList("key_2");

        System.out.println("set ------->"+list1);
        System.out.println("addList ------->"+list2);
    }
}
