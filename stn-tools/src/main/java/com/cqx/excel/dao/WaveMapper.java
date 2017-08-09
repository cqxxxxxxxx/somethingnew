package com.cqx.excel.dao;


import com.cqx.excel.model.Wave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WaveMapper {
    int insert(Wave record);

    int insertSelective(Wave record);

    int batchInsert(@Param("records") List<Wave> v5Addrs);
}