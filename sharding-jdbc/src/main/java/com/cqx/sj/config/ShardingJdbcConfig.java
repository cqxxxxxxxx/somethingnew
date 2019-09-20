package com.cqx.sj.config;

import com.cqx.sj.DataSourceUtil;
import com.cqx.sj.algorithm.PreciseModuloShardingDatabaseAlgorithm;
import com.cqx.sj.algorithm.PreciseModuloShardingTableAlgorithm;
import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.KeyGeneratorConfiguration;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
import org.apache.shardingsphere.api.config.sharding.strategy.StandardShardingStrategyConfiguration;
import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/19
 */
@Configuration
public class ShardingJdbcConfig {

    /**
     * 替换掉默认的datasource
     *
     * @return
     * @throws SQLException
     */
    @Bean
    DataSource getDataSource() throws SQLException {
        /**
         * 规则配置
         */
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(getUserTableRuleConfiguration());
        shardingRuleConfig.getTableRuleConfigs().add(getAddressTableRuleConfiguration());

        /**
         * ✨不是很清楚
         * 设置绑定表 => 指分片规则一致的主表和子表。
         * 例如：t_order表和t_order_item表，均按照order_id分片，则此两张表互为绑定表关系。绑定表之间的多表关联查询不会出现笛卡尔积关联，关联查询效率将大大提升。
         */
        shardingRuleConfig.getBindingTableGroups().add("t_user, t_user_address");


        /**
         * 设置广播表 => 指所有的分片数据源中都存在的表，表结构和表中的数据在每个数据库中均完全一致。
         * 适用于数据量不大且需要与海量数据的表进行关联查询的场景，例如：字典表。
         */
        shardingRuleConfig.getBroadcastTables().add("t_config");

        /**
         * 主从库设置策略
         */
        shardingRuleConfig.setMasterSlaveRuleConfigs(getMasterSlaveRuleConfigurations());

//        默认的分片规则。
//        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingDatabaseAlgorithm()));
//        shardingRuleConfig.setDefaultTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingTableAlgorithm()));
        return ShardingDataSourceFactory.createDataSource(createDataSourceMap(), shardingRuleConfig, new Properties());
    }


    /**
     * 分片
     * 1. user表名的规则配置
     * 2. key生成策略
     *
     * @return
     */
    TableRuleConfiguration getUserTableRuleConfiguration() {
        /**
         * 参数1. 逻辑表名 => 对应有真实表名 比如 t_user_0, t_user_1 是真实表名， t_user是逻辑表名
         * 参数2. 数据节点 => 数据分片的最小单元。由数据源名称和数据表组成，例：ds_0.t_order_0。 => 这边stn_ds_0是主从库配置那边配置的统一的一个访问数据源
         *
         */
        TableRuleConfiguration configuration = new TableRuleConfiguration("t_user", "stn_ds_${0..1}.t_user_${[0, 1]}");

        /**
         * 不设置的话默认使用自增方案 会有问题，所有datasource都执行了。。
         * SNOWFLAKE 为每个应用设置不同的设置worker.id
         */
        Properties properties = new Properties();
        properties.setProperty("worker.id", "123");
        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE", "user_id");
        configuration.setKeyGeneratorConfig(keyGeneratorConfiguration);

        /**
         *
         * 设置分库的策略 => 根据user_id分片键来区分到底对应到哪个db
         * 设置分表策略 => 根据user_id分片键来区分到底对应到db中哪张表(一个db中有多张同样的表来分片)
         */
        configuration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingDatabaseAlgorithm()));
        configuration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("user_id", new PreciseModuloShardingTableAlgorithm()));
        return configuration;
    }

    /**
     * 分片 address表规则配置
     *
     * @return
     */
    TableRuleConfiguration getAddressTableRuleConfiguration() {
        TableRuleConfiguration configuration = new TableRuleConfiguration("t_user_address", "stn_ds_${0..1}.t_user_address_${[0, 1]}");
        Properties properties = new Properties();
        properties.setProperty("worker.id", "123");
        KeyGeneratorConfiguration keyGeneratorConfiguration = new KeyGeneratorConfiguration("SNOWFLAKE", "address_id");
        configuration.setKeyGeneratorConfig(keyGeneratorConfiguration);
        configuration.setDatabaseShardingStrategyConfig(new StandardShardingStrategyConfiguration("address_id", new PreciseModuloShardingDatabaseAlgorithm()));
        configuration.setTableShardingStrategyConfig(new StandardShardingStrategyConfiguration("address_id", new PreciseModuloShardingTableAlgorithm()));
        return configuration;
    }

    /**
     * 配置主库与从库的对应关系
     * name是读写分离数据源名称，通过这个来访问读写分离库
     *
     * @return
     */
    List<MasterSlaveRuleConfiguration> getMasterSlaveRuleConfigurations() {
        MasterSlaveRuleConfiguration masterSlaveRuleConfig1 = new MasterSlaveRuleConfiguration("stn_ds_0", "stn_ds_master_0", Arrays.asList("stn_ds_master_0_slave_0", "stn_ds_master_0_slave_1"));
        MasterSlaveRuleConfiguration masterSlaveRuleConfig2 = new MasterSlaveRuleConfiguration("stn_ds_1", "stn_ds_master_1", Arrays.asList("stn_ds_master_1_slave_0", "stn_ds_master_1_slave_1"));
        return Lists.newArrayList(masterSlaveRuleConfig1, masterSlaveRuleConfig2);
    }


    /**
     * 针对各个分片 与 从库 创建对应的datasource
     *
     * @return
     */
    Map<String, DataSource> createDataSourceMap() {
        final Map<String, DataSource> result = new HashMap<>();
        result.put("stn_ds_master_0", DataSourceUtil.createDataSource("stn_ds_master_0"));
        result.put("stn_ds_master_0_slave_0", DataSourceUtil.createDataSource("stn_ds_master_0_slave_0"));
        result.put("stn_ds_master_0_slave_1", DataSourceUtil.createDataSource("stn_ds_master_0_slave_1"));
        result.put("stn_ds_master_1", DataSourceUtil.createDataSource("stn_ds_master_1"));
        result.put("stn_ds_master_1_slave_0", DataSourceUtil.createDataSource("stn_ds_master_1_slave_0"));
        result.put("stn_ds_master_1_slave_1", DataSourceUtil.createDataSource("stn_ds_master_1_slave_1"));
        return result;
    }

}
