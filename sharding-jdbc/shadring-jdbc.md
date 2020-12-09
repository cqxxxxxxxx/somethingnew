### 名词

https://shardingsphere.apache.org/document/current/cn/features/sharding/concept/sql/  

https://shardingsphere.apache.org/document/current/cn/features/sharding/concept/sharding/

# Getting Started

### 介绍

本demo的分片规则如下:  
分为2个库，然后每个库进行分表，拆分2张表  
分库优点 => 有效的分散对数据库单点的访问量  
分表优点 => 虽然无法缓解数据库压力，但却能够提供尽量将分布式事务转化为本地事务的可能，一旦涉及到跨库的更新操作，分布式事务往往会使问题变得复杂

本demo的主从规则如下:  
目前是一主二从  
主从配置的优点 => 1.高可用，容灾 2.读写分离，提高吞吐量


主库: `stn_ds_master_0`  
从库: `stn_ds_master_0_slave_0`,`stn_ds_master_0_slave_1`

### 其他

分库分表后考虑
1. 事务
2. sql改写 
3. 分页相关

读写分离后考虑
1. 主从库数据同步的延迟
2. 同一线程且同一数据库连接内，如有写入操作，以后的读操作均从主库读取，用于保证数据一致性。


### 遇到的问题

1. shard()是返回的datasource为null，找不到匹配的数据源  
原因：mybatis的xml里表名没有修改还是`t_user_0`，因此找不到匹配的逻辑表名，因此尝试使用默认的`DefaultDatabaseShardingStrategyConfig`来处理。但是因为没有配置这个选项，所以返回了null。


2. 配置的SNOWFLAKE不起作用，没有自动生成id,插入时报异常`Caused by: java.lang.IllegalArgumentException: Sharding value must implements Comparable.
`
原因：mybatis的xml配置里insert语句把主键也带进去了，只要删掉就好了
```$xslt
当时mybatis的xml配置的插入sql
insert into t_user (user_id, `name`, age, create_time, update_time, deleted ) 
values (?, ?, ?,        ?, ?, ?       )
错误的把配置了SNOWFLAKE来生成的主键user_id也放进去了，只要把这个删掉就好了。

debug源码中相关的地方
ShardingInsertColumns类中方法
 //获取普通的列名
 private Collection<String> getRegularColumnNamesFromSQLStatement(final InsertStatement insertStatement) {
        Collection<String> result = new LinkedHashSet<>(insertStatement.getColumns().size(), 1);
        for (ColumnSegment each : insertStatement.getColumns()) {
            result.add(each.getName());
        }
        if (insertStatement.getSetAssignment().isPresent()) {
            for (AssignmentSegment each : insertStatement.getSetAssignment().get().getAssignments()) {
                result.add(each.getColumn().getName());
            }
        }
        //问题就出在这个调用里面，因为上述的sql里添加了user_id的部分，里面判断这个user_id需要用户手动填入
        //而不是sharding-jdbc用SNOWFLAKE来帮你生成，所以没有删掉。 所以认为user_id是一个普通列，需要用户手动赋值
        if (isGenerateKeyFromSQLStatement(insertStatement)) {
            result.remove(generateKeyColumnName);
        }
        return result;
    }
    //主要通过插入的列数量与value数量是否一致来判断
    private boolean isGenerateKeyFromSQLStatement(final InsertStatement insertStatement) {
        return null != generateKeyColumnName && !insertStatement.getColumns().isEmpty() && insertStatement.getColumns().size() != insertStatement.getValueSize();
    }
    
    
    
GeneratedKey类中
public static Optional<GeneratedKey> getGenerateKey(final ShardingRule shardingRule, final List<Object> parameters, 
                                                        final InsertStatement insertStatement, final ShardingInsertColumns insertColumns, final Collection<InsertValue> insertValues) {
        Optional<String> generateKeyColumnName = shardingRule.findGenerateKeyColumnName(insertStatement.getTable().getTableName());
        if (!generateKeyColumnName.isPresent()) {
            return Optional.absent();
        }
        //判断配置的user_id是否在普通的列名中,因为上述没有吧user_id从普通列里剔除掉，所以这边是true，调用第一个方法
        //如果调用第二个方法则会自动生成id
        return insertColumns.getRegularColumnNames().contains(generateKeyColumnName.get()) 
                ? findGeneratedKey(parameters, insertColumns, insertValues, generateKeyColumnName.get())
                : Optional.of(createGeneratedKey(shardingRule, insertStatement, insertValues, generateKeyColumnName.get()));
    }

```


3. select的时候一直查不到数据  
原因：配置了读写分离，但是没有做主从同步的功能，从库一直空。。
需要做下主从同步

### sql

```
CREATE DATABASE `stn_ds_master_0` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_0`;

CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#######################
CREATE DATABASE `stn_ds_master_0_slave_0` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_0_slave_0`;
CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

#######################
CREATE DATABASE `stn_ds_master_0_slave_1` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_0_slave_1`;

CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


#############
CREATE DATABASE `stn_ds_master_1` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_1`;

CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

##################
CREATE DATABASE `stn_ds_master_1_slave_0` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_1_slave_0`;
CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


###################
CREATE DATABASE `stn_ds_master_1_slave_1` 
DEFAULT CHARACTER SET = `utf8mb4` DEFAULT COLLATE = `utf8mb4_general_ci`;
USE `stn_ds_master_1_slave_1`;
CREATE TABLE `t_config` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `key` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_0` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_0` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_1` (
  `user_id` bigint(20) unsigned NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `age` tinyint(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `t_user_address_1` (
  `address_id` bigint(20) unsigned NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`address_id`),
  KEY `index_uid` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```