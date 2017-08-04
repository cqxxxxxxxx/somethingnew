package com.cqx;

import com.cqx.dao.UserDao;
import com.cqx.model.UserEntity;
import com.mongodb.Mongo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created by BG307435 on 2017/8/4.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    Mongo mongo;
    @Autowired
    UserDao userDao;
    @Autowired
    MongoDbFactory mongoDbFactory;
    @Autowired
    GridFsTemplate gridFsTemplate;
//    @Resource(name = "gridFsTemplateSTN")
//    GridFsTemplate gridFsTemplateStn;
//    @Resource(name = "mongoDbFactorySTN")
//    MongoDbFactory mongoDbFactoryStn;

    @Test
    public void testSaveUser() throws Exception {
        UserEntity user=new UserEntity();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName(){
        UserEntity user= userDao.findUserByUserName("小明");
        System.out.println("user is "+user);
    }

    @Test
    public void updateUser(){
        UserEntity user=new UserEntity();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById(){
        userDao.deleteUserById(1l);
    }
}
