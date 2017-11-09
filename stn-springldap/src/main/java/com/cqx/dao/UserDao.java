package com.cqx.dao;

import com.cqx.model.User;

import java.util.List;

/**
 * Created by BG307435 on 2017/11/6.
 */
public interface UserDao {

    User findByDn(String dn);
}
