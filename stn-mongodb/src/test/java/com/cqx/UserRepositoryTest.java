package com.cqx;

import com.cqx.dao.UserRepository;
import com.cqx.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Consumer;

/**
 * mongodb JPA实现 测试
 * Created by BG307435 on 2017/8/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    /**
     * 用于打印到Console
     */
    private static final Consumer<UserEntity> toConsole =
            userEntity -> System.out.println(userEntity.toString());

    @Autowired
    UserRepository userRepository;
    @Autowired
    MongoRepository mongoRepository;


    /**
     * 增加
     */
    @Test
    public void saveJPA(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("我是你亲爹啊1");
        userEntity.setPassWord("我是你亲娘啊1");
        UserEntity userEntity1 = userRepository.save(userEntity);
        System.out.println(userEntity1.getIdString());  //idString作为ObjectId
    }

    /**
     * 修改
     */
    @Test
    public void updateJPA(){
        UserEntity userEntity = new UserEntity();
        userEntity.setIdString("5994169f997713029067b6c4");
        userEntity.setUserName("我才不是你亲爹呢");
        userEntity.setPassWord("我才不是你亲娘呢");
        userRepository.save(userEntity);
        System.out.println("=============================================================");
        findJPA();
    }

    /**
     * 查找
     */
    @Test
    public void findJPA(){
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.stream().forEach(toConsole);
    }

    /**
     * 删除
     */
    @Test
    public void deleteJPA(){
        UserEntity userEntity = new UserEntity();
        userEntity.setIdString("5994169f997713029067b6c4");
        userRepository.delete(userEntity);
        System.out.println("=============================================================");
        findJPA();
    }


}
