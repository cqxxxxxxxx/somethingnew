package com.cqx.sj.algorithm;

import com.cqx.sj.ShardingSphereConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * precise 精确的
 * Modulo 取模
 * <p>
 * 分库策略
 * 对应于DatabaseShardingStrategy。用于配置数据被分配的目标数据源。
 * <p>
 * 每个库200条数据
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/19
 */
@Slf4j
public class PreciseModuloShardingDatabaseAlgorithm implements PreciseShardingAlgorithm<Long> {

    /**
     * Sharding.
     *
     * @param availableTargetNames 有效的实际数据库名
     * @param shardingValue        分片键的值
     * @return sharding result for data source or table's name
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        log.info("collection:" + availableTargetNames + ",preciseShardingValue:" + shardingValue);
        /**
         * 这边暂时采用硬编码方式做下。
         * 即 id 0 1 => db0
         *      2 3 => db1
         */
        long index = shardingValue.getValue() % 4;
        index = index < 2 ? 0 : 1;
        for (String name : availableTargetNames) {
            // =与IN中分片键对应的值
            if (name.endsWith("_" + index)) {
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 计算该量级的数据在哪个数据库
     *
     * @return
     * @Deprecated 用于根据id范围确定位置
     */
    @Deprecated
    private String getDatabaseNo(long columnValue, int i) {
        // ShardingSphereConstants每个库中定义的数据量
        long left = ShardingSphereConstants.DATABASE_DATA_LIMIT * i;
        long right = ShardingSphereConstants.DATABASE_DATA_LIMIT * (i + 1);
        if (left < columnValue && columnValue <= right) {
            return String.valueOf(i);
        } else {
            i++;
            return getDatabaseNo(columnValue, i);
        }
    }
}
