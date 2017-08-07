package com.cqx.dao;

import com.cqx.model.UserEntity;

/**
 * Created by BG307435 on 2017/8/4.
 */
public interface UserDao {

    void saveUser(UserEntity user);

    /**
     * 根据用户名查询对象
     *
     * @param userName
     * @return
     */
    UserEntity findUserByUserName(String userName);

    /**
     * 更新对象
     *
     * @param user
     */
    void updateUser(UserEntity user);

    /**
     * 删除对象
     *
     * @param id
     */
    void deleteUserById(Long id);
}
