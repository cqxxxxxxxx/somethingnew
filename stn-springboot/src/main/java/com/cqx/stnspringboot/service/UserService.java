package com.cqx.stnspringboot.service;

import com.cqx.stnspringboot.entity.User;
import com.cqx.stnspringboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public User getById(Integer id) {
        User byId = userMapper.getById(id);
        return byId;
    }
}
