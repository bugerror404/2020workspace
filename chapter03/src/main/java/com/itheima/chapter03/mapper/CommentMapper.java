package com.itheima.chapter03.mapper;

import com.itheima.chapter03.domain.Comment;
import org.apache.ibatis.annotations.*;
//注解的方式进行数据库的增删改查
//接口中的方法
@Mapper //表示CommentMapper是一个MyBatis接口文件，保证能够被SpringBoot
       // 自动扫描到Spring容器中。
public interface CommentMapper {
    //根据评论的id号查询
    @Select("SELECT * FROM t_comment WHERE id=#{id}")
    public Comment findById(Integer id);
    //插入
    @Insert("INSERT INTO t_comment(content,author,a_id) VALUES(#{content},#{author},#{aId})")
    public int insertComment(Comment comment);
    //修改
    @Update("UPDATE t_comment SET content=#{content},author=#{author} WHERE id=#{id}")
    public int updateComment(Comment comment);
    //删除 根据主键删除
    @Delete("DELETE FROM t_comment WHERE id=#{id}")
    public int deleteComment(Integer id);

}
