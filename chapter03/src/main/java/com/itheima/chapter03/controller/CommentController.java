package com.itheima.chapter03.controller;

import com.itheima.chapter03.domain.Comment;
import com.itheima.chapter03.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/get")
    public Comment findById(int id){
        Comment comment = commentService.findById(id);
        return comment;
    }
   /* @GetMapping("/get/{id}")
    public Comment findById(@PathVariable("id") int comment_id){ //起别名
        Comment comment = commentService.findById(comment_id);
        return  comment;
    }*/
}



