package com.itheima.chapter03.service;

import com.itheima.chapter03.domain.Comment;
import com.itheima.chapter03.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class ApiCommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    public Comment findById(int id){
        //先从缓存中读取数据
        Object object = redisTemplate.opsForValue().get("comment_" + id);
        if(object!=null){
            return (Comment) object;//缓存中有数据就从缓存中返回数据
        }
        else {
            Comment comment = commentMapper.findById(id);
            //将查询结果放到缓存，并设置有限期为一天
            redisTemplate.opsForValue().set("comment_"+id,comment,1, TimeUnit.DAYS);
            return comment;
        }

    }
}
