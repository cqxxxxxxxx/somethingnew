package com.cqx;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by cqx on 2017/8/16.
 */
public class JedisTest {


    @Test
    public void ping() {
        //实例化一个客户端
        Jedis jedis = new Jedis("47.92.6.210",6379);
        //ping下，看看是否通的
        System.out.println("Server is running: " + jedis.ping());

        //保存一个
        jedis.set("leiTest", "localhost Connection  sucessfully");
        //获取一个
        String leite=jedis.get("leiTest");
        System.out.println("leiTest键值为: " +leite);
    }

}
