package com.cqx.sj.dao;

import com.cqx.sj.model.UserAddress;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(UserAddress record);

    UserAddress selectByPrimaryKey(Long addressId);

    List<UserAddress> selectAll();

    int updateByPrimaryKey(UserAddress record);
}