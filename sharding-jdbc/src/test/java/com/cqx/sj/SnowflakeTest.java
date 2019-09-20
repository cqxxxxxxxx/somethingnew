package com.cqx.sj;

import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.junit.Test;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/21
 */
public class SnowflakeTest {

    @Test
    public void test0() {
        System.out.println(381990016072548353L % 4);

    }

    @Test
    public void test() {
        SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();
        for (int i = 0; i < 100; i++) {
            Long key = (Long) keyGenerator.generateKey();
            System.out.println(key % 4);
        }

    }
}
