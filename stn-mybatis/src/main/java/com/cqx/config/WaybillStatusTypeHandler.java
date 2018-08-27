package com.cqx.config;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by BG307435 on 2018/8/27.
 */
public class WaybillStatusTypeHandler implements TypeHandler<OrderStatus> {
    private static final String OLD_STATUS = "ABORTED";
    private static final String EXPECT_STATUS = "ABORT";

    @Override
    public void setParameter(PreparedStatement ps, int i, OrderStatus parameter, JdbcType jdbcType) throws SQLException {
        //设置第i个参数的值为传入sex的code值，preparedStatement为执行数据库操纵的对象
        //传值的时候是一个sex对象，但是当进行映射插入的时候就会转化为sex的code值进行存储
        ps.setString(i, parameter.name());
    }

    @Override
    public OrderStatus getResult(ResultSet rs, String columnName) throws SQLException {
        //获取数据库存储的status
        String result = rs.getString(columnName);
        if (OLD_STATUS.equals(result)) {
            result = EXPECT_STATUS;
        }
        return OrderStatus.valueOf(result);
    }

    @Override
    public OrderStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
        //获取数据库存储的status
        String result = rs.getString(columnIndex);
        if (OLD_STATUS.equals(result)) {
            result = EXPECT_STATUS;
        }
        return OrderStatus.valueOf(result);
    }

    @Override
    public OrderStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        if (OLD_STATUS.equals(result)) {
            result = EXPECT_STATUS;
        }
        return OrderStatus.valueOf(result);
    }
}
