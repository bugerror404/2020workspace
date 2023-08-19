package com.design.education.mapper;

import com.design.education.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Long oId);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long oId);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}