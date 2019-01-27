package com.cqx.jdbc;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cqx on 2017/7/13.
 */
public class JDBCDemo {

    @Test
    public void test(){
        List list = queryForList();
        list.stream().forEach(System.out::println);
    }

    public static List<Map<String,Object>> queryForList(){
        Connection connection = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();

        try {
            // 加载JDBC驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/somethingnew";

            String user = "root";
            String password = "123456";

            // 获取数据库连接
            connection = DriverManager.getConnection(url,user,password);

            String sql = "select * from stn_user where id = ? ";
            // 创建Statement对象（每一个Statement为一次数据库执行请求）
            stmt = connection.prepareStatement(sql);

            // 设置传入参数
            stmt.setString(1, "1");
            System.out.println(stmt.getResultSet());
            // 执行SQL语句
            rs = stmt.executeQuery();
            stmt.execute()

            // 处理查询结果（将查询结果转换成List<Map>格式）
            ResultSetMetaData rsmd = rs.getMetaData();
            int num = rsmd.getColumnCount();

            while(rs.next()){
                Map map = new HashMap();
                for(int i = 0;i < num;i++){
                    String columnName = rsmd.getColumnName(i+1);
                    map.put(columnName,rs.getString(columnName));
                }
                resultList.add(map);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭结果集
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                // 关闭执行
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultList;
    }

}
