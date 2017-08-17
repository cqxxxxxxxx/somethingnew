package com.cqx;

import com.cqx.redis.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis数据库的一些操作，CRUD之类的
 * Created by Shan on 2017/1/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisKeyValueTemplate redisKeyValueTemplate;

    @Resource(name = "userRedisTemplate")    //按名注入
    private RedisTemplate<String, User> userRedisTemplate;


    @Test
    public void publishOrder() throws InterruptedException {
//        Thread listenerThread = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(20);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        listenerThread.start();
        stringRedisTemplate.convertAndSend("order", "萨范德萨发送到啊");
        stringRedisTemplate.convertAndSend("order", "我是你子欲养而亲不待的亲爹啊");

    }

    @Test
    public void redisTest() {

        stringRedisTemplate.opsForValue().set("cqx", "nmb");

        stringRedisTemplate.opsForHash().put("cqxhash", "hashkey1", "hashvalue1");
        stringRedisTemplate.opsForHash().put("cqxhash", "hashkey2", "hashvalue2");

        stringRedisTemplate.opsForList().leftPush("cqxlist", "111");
        stringRedisTemplate.opsForList().leftPush("cqxlist", "222");

        Assert.assertEquals("nmb", stringRedisTemplate.opsForValue().get("cqx"));

    }

    @Test
    public void redisUserTest() {
        User user1 = new User();
        user1.setAge(22);
        user1.setName("陈奇星");
        user1.setSex("男");
        userRedisTemplate.opsForValue().set(user1.getName(), user1);

        User user2 = new User();
        user2.setName("陈奇星二号");
        user2.setSex("蓝");
        user2.setAge(22);
        userRedisTemplate.opsForValue().set(user2.getName(), user2);

        Assert.assertEquals("男", userRedisTemplate.opsForValue().get("陈奇星").getSex());
        Assert.assertEquals("蓝", userRedisTemplate.opsForValue().get("陈奇星二号").getSex());

        System.out.println("xxxxxx:" + userRedisTemplate.opsForValue().get("陈奇星"));
        System.out.println("xxxxxx:" + user1);

    }


    /**
     * ffn的redis cluster
     */
    @Test
    public void test() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        //Jedis Cluster will attempt to discover cluster nodes automatically
        jedisClusterNodes.add(new HostAndPort("47.92.6.210", 7000));
        JedisCluster jc = new JedisCluster(jedisClusterNodes);
//        jc.sadd("ffn", "无敌的");
        System.out.println(jc.get("1"));
//        System.out.println(jc.get("ffn"));
    }



}
