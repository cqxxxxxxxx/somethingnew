package com.cqx.config;

import org.springframework.context.annotation.Configuration;

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
