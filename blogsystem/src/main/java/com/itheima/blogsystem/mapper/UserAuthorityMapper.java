package com.itheima.blogsystem.mapper;

import com.itheima.blogsystem.entity.UserAuthority;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAuthorityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAuthority record);

    int insertSelective(UserAuthority record);

    UserAuthority selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAuthority record);

    int updateByPrimaryKey(UserAuthority record);
}