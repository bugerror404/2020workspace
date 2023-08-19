package com.itheima.chapter07.mapper;

import com.itheima.chapter07.entity.CustomerAuthority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerAuthority record);

    int insertSelective(CustomerAuthority record);

    CustomerAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerAuthority record);

    int updateByPrimaryKey(CustomerAuthority record);
}