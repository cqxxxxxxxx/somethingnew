package com.cqx.sql_gen;

/**
 * Created by BG307435 on 2017/9/18.
 */
public interface InsertAbility extends Gen {
    void setInsertColumns(String columns);

    void setTableName(String tableName);
}
