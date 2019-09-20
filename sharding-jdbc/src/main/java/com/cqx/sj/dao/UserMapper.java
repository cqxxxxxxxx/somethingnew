package com.cqx.sj.dao;

import com.cqx.sj.model.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    User selectByPrimaryKey(Long userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);
}