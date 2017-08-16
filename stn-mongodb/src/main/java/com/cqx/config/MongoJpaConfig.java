package com.cqx.config;

import com.cqx.dao.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 开启MongoDB的JPA支持
 * Created by BG307435 on 2017/8/16.
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = UserRepository.class)
public class MongoJpaConfig {
}
