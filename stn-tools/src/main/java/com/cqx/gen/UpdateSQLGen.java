package com.cqx.gen;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by BG307435 on 2017/11/13.
 */
public class UpdateSQLGen implements UpdateAbility {

    private String updateSQL;
    private ClazzContext clazzContext;


    @Override
    public String gen(List list) {
        String sql = updateSQL;
        ClazzContext context = clazzContext;
        StringBuilder sb = new StringBuilder();
        list.stream().forEach(x -> {
            String result = Parser.parse1(sql, context.getGetMethodsSQL().stream().map(method -> {
                Object obj = new Object();
                try {
                    obj = method.invoke(x);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (obj instanceof String) {
                    return "\'" + obj + "\'";
                }
                if (obj instanceof Integer) {
                    return obj + "";
                }
                return obj;
            }).toArray());
            sb.append(result)
                    .append(";\n");

        });
        return sb.toString();
    }

    @Override
    public void setUpdateSQL(String sql) {
        this.updateSQL = sql;
    }

    public void setClazzContext(ClazzContext clazzContext) {
        this.clazzContext = clazzContext;
    }
}
