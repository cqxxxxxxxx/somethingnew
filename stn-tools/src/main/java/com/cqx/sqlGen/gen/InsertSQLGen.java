package com.cqx.sqlGen.gen;

import com.cqx.sqlGen.ClazzContext;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by BG307435 on 2017/9/21.
 */
public class InsertSQLGen implements InsertAbility {
    private List<String> columns = new ArrayList<>(50);
    private String table;
    private static final String PREFIX = "(";
    private static final String SUFFIX = ")";
    private static final String COMMA = ",";
    private static final String QUOTES = "\'";
    private static final String ACCENT = "`";
    private StringBuilder sb = new StringBuilder();
    private ClazzContext clazzContext;
    private DateFormat dateFormat;

    @Override
    public void setTableAndColumns(String tableName, String columns) {
        String[] strs = columns.split(",");
        this.columns = Arrays.asList(strs);
        this.table = tableName;

    }

    @Override
    public String gen(List list) {
        sb.append("INSERT INTO ")
                .append(table);
        genColumn();
        sb.append(" VALUES ");
        execList(list);
        return sb.toString();
    }

    private void execList(List list) {
        if (list.size() < 1) {
            throw new IllegalArgumentException("sheet rows must bigger than 1");
        }

        list.stream().forEach(x -> {
            addPrefix();
            clazzContext.getGetMethods().stream().forEach(method -> {
                try {
                    Object val = method.invoke(x);
                    if (val == null) {
                        sb.append("NULL");
                    } else if (val instanceof Date) {
                        wrap(dateFormat.format(val));
                    } else {
                        wrap(val.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                addComma();
            });
            addSuffix();
            addComma();
        });
        if (sb.lastIndexOf(COMMA) == sb.length() - 1) {
            //删除逗号
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private void genColumn() {
        addPrefix();
        columns.stream().forEach(x -> {
            wrapAccent(x);
            addComma();
        });
        addSuffix();
    }

    private void addComma() {
        sb.append(COMMA);
    }

    private void wrap(String str) {
        if (StringUtils.isEmpty(str)) {
            sb.append("NULL");
        } else {
            sb.append(QUOTES)
                    .append(str.trim())
                    .append(QUOTES);
        }
    }

    private void wrapAccent(String str) {
        if (StringUtils.isEmpty(str)) {
            sb.append("NULL");
        } else {
            sb.append(ACCENT)
                    .append(str.trim())
                    .append(ACCENT);
        }
    }

    private void addPrefix() {
        sb.append(PREFIX);
    }

    private void addSuffix() {
        if (sb.lastIndexOf(COMMA) == sb.length() - 1) {
            //删除逗号
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(SUFFIX);
    }

    public void setClazzContext(ClazzContext clazzContext) {
        this.clazzContext = clazzContext;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}
