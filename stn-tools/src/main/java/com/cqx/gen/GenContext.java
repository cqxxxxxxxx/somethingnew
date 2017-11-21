package com.cqx.gen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by BG307435 on 2017/9/22.
 */

public class GenContext {
    private static final Logger log = LoggerFactory.getLogger(GenContext.class);
    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    private String path;            //excel路径
    private Class targetClass;

    private Function enhance;       //对list进行一些处理
    private DateFormat dateFormat;  //excel处理日期类型
    private ClazzContext clazzContext;  //保存类信息
    private ExcelConsumer excelConsumer;    //处理excel 转成list

    private InsertSQLGen insertSQLGen;      //根据list生成sql
    private String tableName;       //表名
    private String columns;         //insert 的列名，用逗号分隔

    private UpdateSQLGen updateSQLGen;      //
    private String updateSQL;   //sql模版 update user set name = {1}, sex = {2} where id = {3}

    /**
     * 生成sql
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public String insertSQL() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List results = null;
        try {
            results = excelToList(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = insertSQLGen.gen(results);
        return sql;
    }

    public String updateSQL() {
        List results = null;
        try {
            results = excelToList(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = updateSQLGen.gen(results);
        return sql;
    }

    private List excelToList(String path) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        excelConsumer.consume(path);
        List results = excelConsumer.parseToList(excelConsumer.getSheets().get(0));
        if (enhance != null) {
            results = (List) results.stream().map(enhance).collect(Collectors.toList());  //增强处理 enhance
        }
        return results;
    }


    /**
     * 初始化处理类
     * @throws IOException
     */
    private void init() throws IOException {
        log.info("初始化处理类...");
        clazzContext = new ClazzContext(targetClass);
        excelConsumer = new ExcelConsumer();
        insertSQLGen = new InsertSQLGen();
        updateSQLGen = new UpdateSQLGen(updateSQL == null ? "" : updateSQL, clazzContext);

        excelConsumer.setClazzContext(clazzContext);
        insertSQLGen.setClazzContext(clazzContext);

        excelConsumer.setDateFormat(dateFormat == null ? DEFAULT_DATE_FORMAT : dateFormat);
        insertSQLGen.setDateFormat(dateFormat == null ? DEFAULT_DATE_FORMAT : dateFormat);

        insertSQLGen.setTableName(tableName == null ? "" : tableName);    //"order_history_address"
        insertSQLGen.setColumns(columns == null ? "" : columns); //"address_id, company_id, province, city, district, detail, operation_time,operator_id,province_id,city_id,district_id,contact_name, contact_phone"
    }


    public static final class Builder {
        private String path;            //excel路径
        private Class targetClass;
        private String tableName;       //表名
        private String columns;         //insert 的列名，用逗号分隔
        private Function enhance;       //对list进行一些处理
        private DateFormat dateFormat;  //excel处理日期类型
        private String updateSQL;   //

        private Builder() {
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public Builder withTargetClass(Class targetClass) {
            this.targetClass = targetClass;
            return this;
        }

        public Builder withTableName(String tableName) {
            this.tableName = tableName;
            return this;
        }

        public Builder withColumns(String columns) {
            this.columns = columns;
            return this;
        }

        public Builder withEnhance(Function enhance) {
            this.enhance = enhance;
            return this;
        }

        public Builder withDateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }

        public Builder withUpdateSQL(String updateSQL) {
            this.updateSQL = updateSQL;
            return this;
        }

        public GenContext build() throws IOException {
            GenContext genContext = new GenContext();
            genContext.tableName = this.tableName;
            genContext.updateSQL = this.updateSQL;
            genContext.path = this.path;
            genContext.enhance = this.enhance;
            genContext.targetClass = this.targetClass;
            genContext.dateFormat = this.dateFormat;
            genContext.columns = this.columns;
            genContext.init();
            return genContext;
        }
    }
}
