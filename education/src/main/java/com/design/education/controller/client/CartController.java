package com.design.education.controller.client;

import com.design.education.entity.Article;
import com.design.education.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CartController {
    @Autowired
    private IArticleService articleService;
    //跳转到购物车详情页面
    @GetMapping(value = "/cart/{id}")
    public String getArticleById(@PathVariable("id") Integer id, HttpServletRequest request) {
        //1.获取点击的文章对象
        Article article = articleService.selectArticleById(id);
        if (article != null) {
            request.setAttribute("article", article);
            return "client/cart";
        } else {
            return "comm/error_404";
        }
    }
}
