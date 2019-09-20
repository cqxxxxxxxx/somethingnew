package com.cqx.sj.dao;

import com.cqx.sj.model.Config;

import java.util.List;

public interface ConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Config record);

    Config selectByPrimaryKey(Integer id);

    List<Config> selectAll();

    int updateByPrimaryKey(Config record);
}