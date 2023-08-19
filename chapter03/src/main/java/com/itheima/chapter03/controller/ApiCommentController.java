package com.itheima.chapter03.controller;

import com.itheima.chapter03.domain.Comment;
import com.itheima.chapter03.service.ApiCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api") //映射路径
public class ApiCommentController {
    @Autowired
    private ApiCommentService apiCommentService;
    @GetMapping("/get")
    public Comment findById(int id){
        Comment comment = apiCommentService.findById(id);
        return comment;
    }
}
