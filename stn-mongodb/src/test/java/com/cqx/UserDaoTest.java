package com.cqx;

import com.cqx.dao.UserDao;
import com.cqx.model.UserEntity;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

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


    /**
     * GridFS是一种将大型文件存储在MongoDB的文件规范
     * 用来进行文件的操作
     */
    @Autowired
    GridFsTemplate gridFsTemplate;

//    @Resource(name = "gridFsTemplateSTN")
//    GridFsTemplate gridFsTemplateStn;
//    @Resource(name = "mongoDbFactorySTN")
//    MongoDbFactory mongoDbFactoryStn;

    /**
     * 文件存储
     *
     * @throws FileNotFoundException
     */
    @Test
    public void testFileSave() throws FileNotFoundException {
        File file = new File("E:\\projects\\somethingnew\\stn-mongodb\\src\\main\\resources\\demo.jpg");
        InputStream fileInputStream = new FileInputStream(file);
        GridFSFile gridFSFile = gridFsTemplate.store(fileInputStream, "aaa", "照片呀");
        System.out.println(gridFSFile.getId());
    }

    /**
     * GridFSDBFile 里面有文件保存时候的相关信息
     * 文件获取输出
     */
    @Test
    public void testFileGet() throws IOException {
        String fileId = "59842022c937571bb8444fb4";
        GridFSDBFile gridFSDBFile = gridFsTemplate.findOne(query(where("_id").is(fileId)));
        InputStream in = gridFSDBFile.getInputStream();
        Assert.assertNotNull(in);
        File file = new File("C://sss.jpg");
        OutputStream out = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[1024];
        while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
        out.close();
        in.close();
    }

    @Test
    public void testSaveUser() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("小明");
        user.setPassWord("fffooo123");
        userDao.saveUser(user);
    }

    @Test
    public void findUserByUserName() {
        UserEntity user = userDao.findUserByUserName("小明");
        System.out.println("user is " + user);
    }

    @Test
    public void updateUser() {
        UserEntity user = new UserEntity();
        user.setId(2l);
        user.setUserName("天空");
        user.setPassWord("fffxxxx");
        userDao.updateUser(user);
    }

    @Test
    public void deleteUserById() {
        userDao.deleteUserById(1l);
    }
}
