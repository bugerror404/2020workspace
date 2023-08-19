package com.design.educationplatform.mapper;

import com.design.educationplatform.entity.Statistic;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Statistic record);

    int insertSelective(Statistic record);

    Statistic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Statistic record);

    int updateByPrimaryKey(Statistic record);
}