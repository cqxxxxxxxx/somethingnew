package com.cqx.redisson;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.api.RedissonRxClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/8/7
 */
public class RedissonDemo {


    public static void main(String[] args) throws IOException {
        // 1. Create config object
        Config config = new Config();
        config.useClusterServers()
                // use "rediss://" for SSL connection
                .addNodeAddress("redis://127.0.0.1:7181");

        // or read config from file
        config = Config.fromYAML(new File("config-file.yaml"));


        // 2. Create RedissonDemo instance

// Sync and Async API
        RedissonClient redisson = Redisson.create(config);

// Reactive API
        RedissonReactiveClient redissonReactive = Redisson.createReactive(config);

// RxJava2 API
        RedissonRxClient redissonRx = Redisson.createRx(config);


        // 4. Get Redis based Lock
        RLock lock = redisson.getLock("myLock");
        lock.lock();
    }
}
