package com.cqx.lock;

import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.Random;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/3
 */
public class DistributeLock {
    private static final String key = "lock-key";
    private final String requestId = new Random().nextInt() + "";
    private static final Long RELEASE_SUCCESS = 1L;
    private static Jedis jedis = new Jedis("localhost", 6379);


    public static void main(String[] args) {
        System.out.println(jedis.get("name"));
        DistributeLock distributeLock = new DistributeLock();
        distributeLock.lock();
        distributeLock.lock();
        distributeLock.release();
    }

    public void lock() {
        String result = jedis.set(key, requestId, "NX", "PX", 100000);
        if (result != null && result.equals("OK")) {
            System.out.println("lock ok l");
            System.out.println(jedis.get(key));
        } else {
            System.out.println("lock shibaile");
        }
    }

    public void release() {
        Jedis jedis = new Jedis("localhost", 6379);
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList(requestId));
        System.out.println(RELEASE_SUCCESS.equals(result));
    }


}
