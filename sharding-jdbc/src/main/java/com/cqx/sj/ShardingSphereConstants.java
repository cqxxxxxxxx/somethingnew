package com.cqx.sj;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/19
 */
public class ShardingSphereConstants {

    /**
     * 一个数据库200条数据
     * 分片键每200 一个数据库
     */
    public static int DATABASE_DATA_LIMIT = 200;

    /**
     * 数据库内部2张相同结构的表
     * 分片键每100 一张表
     */
    public static int TABLE_DATA_LIMIT = 100;

}
