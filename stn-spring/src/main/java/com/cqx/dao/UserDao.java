package com.cqx.dao;

import com.cqx.model.User;

import java.util.List;

/**
 * Created by BG307435 on 2017/11/6.
 */
public interface UserDao {

    boolean auth(String dn, String credentials);

    void create(User user);

    void update(User user);

    void delete(User user);

    List<String> getAllUserNames();

    List<User> findAll();

    User findByPrimaryKey(String company, String fullname);

    List<User> search(String name);
}
