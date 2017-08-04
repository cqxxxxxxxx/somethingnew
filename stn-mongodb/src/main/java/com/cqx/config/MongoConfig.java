package com.cqx.config;

import com.mongodb.DB;
import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.net.UnknownHostException;

/**
 * Created by BG307435 on 2017/8/4.
 */
@Configuration
public class MongoConfig {

//    @Autowired
//    Mongo mongo;
//
//    @Autowired
//    MongoTemplate mongoTemplate;

//    /**
//     * 不需要 spring-boot 已经自动给你生成好了 直接@Autowired就好了
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "mongoDbFactorySTN")
//    public MongoDbFactory mongoDbFactorySTN(Mongo mongo) throws Exception {
//        UserCredentials userCredentials = new UserCredentials("root", "123456");
//        return new SimpleMongoDbFactory(mongo, "stn");
//    }
//
//
//
//    /**
//     * 不需要 spring-boot 已经自动给你生成好了 直接@Autowired就好了
//     * @return
//     * @throws Exception
//     */
//    @Bean(name = "gridFsTemplateSTN")
//    public GridFsTemplate gridFsTemplate(MongoDbFactory mongoDbFactory, MongoTemplate mongoTemplate) {
//        return new GridFsTemplate(mongoDbFactory, mongoTemplate.getConverter());
//    }

}
