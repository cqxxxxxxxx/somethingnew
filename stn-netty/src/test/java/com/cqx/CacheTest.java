package com.cqx;

import com.cqx.cqxhat.model.User;
import com.cqx.cqxhat.support.cache.CachePool;
import com.cqx.cqxhat.support.cache.UserPool;
import org.junit.Test;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class CacheTest {

    @Test
    public void test() {
        CachePool<String, User> cachePool = UserPool.getInstance();
        cachePool.put("aa", User.of("1", "2", null));
        User user = cachePool.get("aa");
    }
}
