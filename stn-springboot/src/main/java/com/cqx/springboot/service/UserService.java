package com.cqx.springboot.service;

import com.cqx.springboot.entity.User;
import com.cqx.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public User getById(Integer id) {
        User byId = userMapper.getById(id);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return byId;
    }
}
