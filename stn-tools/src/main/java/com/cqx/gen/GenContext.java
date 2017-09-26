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

    private String path;
    private Class targetClass;
    private String tableName;
    private String columns;
    private Function enhance;
    private DateFormat dateFormat;  //excel处理日期类型
    private ClazzContext clazzContext;  //保存类信息
    private ExcelConsumer excelConsumer;    //处理excel 转成list
    private InsertSQLGen insertSQLGen;      //根据list生成sql


    /**
     * 生成sql
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public String genSql() throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        init();
        excelConsumer.consume(path);
        List results = excelConsumer.parseToList(excelConsumer.getSheets().get(0));
        if (enhance != null) {
            results = (List) results.stream().map(enhance).collect(Collectors.toList());  //增强处理 enhance
        }
        String sql = insertSQLGen.gen(results);
        return sql;
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

        excelConsumer.setClazzContext(clazzContext);
        insertSQLGen.setClazzContext(clazzContext);
        excelConsumer.setDateFormat(dateFormat == null ? DEFAULT_DATE_FORMAT : dateFormat);
        insertSQLGen.setDateFormat(dateFormat == null ? DEFAULT_DATE_FORMAT : dateFormat);
        insertSQLGen.setTableName(tableName);    //"order_history_address"
        insertSQLGen.setColumns(columns); //"address_id, company_id, province, city, district, detail, operation_time,operator_id,province_id,city_id,district_id,contact_name, contact_phone"
    }


    public static final class Builder {
        private String path;
        private Class targetClass;
        private String tableName;
        private String columns;
        private Function enhance;
        private DateFormat dateFormat;  //excel处理日期类型

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

        public GenContext build() {
            GenContext genContext = new GenContext();
            genContext.tableName = this.tableName;
            genContext.targetClass = this.targetClass;
            genContext.columns = this.columns;
            genContext.enhance = this.enhance;
            genContext.path = this.path;
            genContext.dateFormat = this.dateFormat;
            return genContext;
        }
    }
}
