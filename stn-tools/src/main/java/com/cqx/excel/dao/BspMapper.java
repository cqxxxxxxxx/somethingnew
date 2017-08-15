package com.cqx.excel.dao;

import com.cqx.excel.model.Bsp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BspMapper {
    int deleteByPrimaryKey(Integer cityId);

    int insert(Bsp record);

    int insertSelective(Bsp record);

    Bsp selectByPrimaryKey(Integer cityId);

    int updateByPrimaryKeySelective(Bsp record);

    int updateByPrimaryKey(Bsp record);

    int batchInsert(@Param("records")List<Bsp> bsps);
}