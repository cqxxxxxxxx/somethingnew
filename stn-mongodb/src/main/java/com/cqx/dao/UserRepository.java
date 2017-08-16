package com.cqx.dao;

import com.cqx.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 基于spring-data 实现的mongoDB操作
 *
 * notice: 用long做主键会报错  Mongo ObjectIds don't map to a java Long type.  use String or BigInteger
 * 因为 mongoDB的ObjectId是字符串类型的把？ 。。。。。
 *  Created by BG307435 on 2017/8/16.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {


    List<UserEntity> findByUserName(String lastname);
}
