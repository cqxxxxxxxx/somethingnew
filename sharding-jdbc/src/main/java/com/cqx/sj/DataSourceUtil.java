package com.cqx.sj;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/19
 */
public class DataSourceUtil {

//    public static final String FACTORIES_RESOURCE_LOCATION = "datasource.properties";
//
//    private static Properties druidProperties;

    public static DataSource createDataSource(String name) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://localhost:3306/%s?characterEncoding=utf-8", name));
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }

//    private Properties getDruidProperties() throws IOException {
//        ClassLoader classLoader = this.getClass().getClassLoader();
//        Enumeration<URL> urls = (classLoader != null ?
//                classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
//                ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
//        while (urls.hasMoreElements()) {
//            URL url = urls.nextElement();
//            UrlResource resource = new UrlResource(url);
//            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
//        }
//
//    }

}
