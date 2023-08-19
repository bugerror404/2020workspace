package com.design.educationplatform.mapper;

import com.design.educationplatform.entity.Curriculum;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CurriculumMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Curriculum record);

    int insertSelective(Curriculum record);

    Curriculum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Curriculum record);

    int updateByPrimaryKeyWithBLOBs(Curriculum record);

    int updateByPrimaryKey(Curriculum record);
}