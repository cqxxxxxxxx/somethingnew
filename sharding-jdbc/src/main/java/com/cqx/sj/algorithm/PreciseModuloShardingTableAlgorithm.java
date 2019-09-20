package com.cqx.sj.algorithm;

import com.cqx.sj.ShardingSphereConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * 分表策略
 * 对应于TableShardingStrategy。用于配置数据被分配的目标表，该目标表存在与该数据的目标数据源内。故表分片策略是依赖与数据源分片策略的结果的。
 * <p>
 * 每个表100条数据存放
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/19
 */
@Slf4j
public class PreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {
        log.info("collection:" + availableTargetNames + ",preciseShardingValue:" + shardingValue);
        for (String name : availableTargetNames) {
            // =与IN中分片键对应的值
            long index = shardingValue.getValue() % 2;
            if (name.endsWith("_" + index)) {
                return name;
            }
        }
        throw new UnsupportedOperationException();
    }

    /**
     * 计算该量级的数据在数据库哪个表
     *
     * @return
     */
    private String getTableNo(long columnValue, int i) {
        // ShardingSphereConstants每个库中定义的数据量
        long left = ShardingSphereConstants.DATABASE_DATA_LIMIT * i;
        long right = ShardingSphereConstants.DATABASE_DATA_LIMIT * (i + 1);
        if (left < columnValue && columnValue <= right) {
            return String.valueOf(i);
        } else {
            i++;
            return getTableNo(columnValue, i);
        }
    }
}
