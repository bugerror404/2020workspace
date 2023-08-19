package com.design.educationplatform.mapper;

import com.design.educationplatform.entity.Authority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);
}