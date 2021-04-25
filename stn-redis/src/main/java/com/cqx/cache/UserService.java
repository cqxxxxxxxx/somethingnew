package com.cqx.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Shan on 2017/1/15.
 */
@Service
public class UserService {


    /**
     * 根据name在redis缓存或者数据库中查找user
     * 吧name作为缓存的key
     * 使用的缓存是user
     *
     * @param name
     * @return
     */
    @Transactional
//    @Cacheable(value = "user", key = "#name")
    public User selectByName(String name) {
        System.out.println("selectByName 来自数据库 user");
        return new User();
    }

    /**
     * 缓存key的生成采用customKeyGenerator的生成策略
     * 设置缓存条件 name长度>=3 的时候才进行缓存
     * 使用的缓存是 test1
     *
     * @param name
     * @return
     */
    @Cacheable(value = "test1", keyGenerator = "customKeyGenerator", condition = "#name.length() >= 3")
    public User selectByName1(String name) {
        User user = new User();
        System.out.println("selectByName 来自数据库 test1");
        return user;
    }


    /**
     * @param name
     */
    @CacheEvict(value = "user", key = "#name")
    public void deleteByName(String name) {
        //nop
    }
}
