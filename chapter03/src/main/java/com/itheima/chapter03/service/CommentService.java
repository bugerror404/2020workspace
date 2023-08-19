package com.itheima.chapter03.service;

import com.itheima.chapter03.domain.Comment;
import com.itheima.chapter03.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    public Comment findById(int id){
        Comment comment = commentMapper.findById(id);
        return comment;
    }
}
