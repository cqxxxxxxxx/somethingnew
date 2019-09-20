package com.cqx.sj;

import com.cqx.sj.dao.ConfigMapper;
import com.cqx.sj.dao.UserAddressMapper;
import com.cqx.sj.dao.UserMapper;
import com.cqx.sj.model.Config;
import com.cqx.sj.model.User;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShardingJdbcApplication.class)
public class ShardingJdbcApplicationTests {

    @Autowired
    ConfigMapper configMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserAddressMapper userAddressMapper;

    @Test
    public void contextLoads() {
    }


    @Test
    public void insertTest() {
        Random random = new Random();
        for (int i = 1; i < 400; i++) {
            User user = new User();
//        user.setUserId(1L);
            user.setAge(random.nextInt(100));
            user.setName("陈奇星" + i);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setDeleted(false);
            userMapper.insert(user);
        }
        Config config = new Config();
        config.setKey("key0");
        config.setValue("value0");
        configMapper.insert(config);
    }

    @Test
    public void selectTest() {
        List<Long> ids = Lists.newArrayList(381989852641492992L, 381989878423879680L, 381989884245573633L, 381989939367116801L);
        User user0 = userMapper.selectByPrimaryKey(381989852641492992L);
        List<User> users = userMapper.selectAll();
    }

}
