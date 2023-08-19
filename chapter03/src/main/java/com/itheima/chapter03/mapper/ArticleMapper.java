package com.itheima.chapter03.mapper;

import com.itheima.chapter03.domain.Article;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ArticleMapper {
    //根据评论的id号查询
    
    public Article findById(Integer id);
    //插入
    
    public int insertArticle(Article article);
    //修改
    
    public int updateArticle(Article article);
    //删除 根据主键删除
   
    public int deleteArticle(Integer id);
}
