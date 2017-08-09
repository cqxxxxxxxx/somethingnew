package com.cqx.excel.dao;

import com.cqx.excel.model.V5Addr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface V5AddrMapper {
    int insert(V5Addr record);

    int insertSelective(V5Addr record);

    int batchInsert(@Param("records")List<V5Addr> v5Addrs);


}