package com.design.education.controller.client;

import com.design.education.entity.Article;
import com.design.education.entity.User;
import com.design.education.service.IArticleService;
import com.design.education.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {
//    @Autowired
//    private IUserService userService;
//    @GetMapping(value = "/order/{id}")
//    public String getUserById(@PathVariable("id") Integer id, HttpServletRequest request) {
//        //1.获取当前登录的用户
//        User user=userService.selectById(id);
//        if (user!=null){
//            request.setAttribute("user", user);
//            return "client/orderDetails";
//        }
//        else {
//            return "comm/error_404";
//        }
//    }
//    @RequestMapping("/order")
//    public String goOrderDetails(){
//
//        return "client/orderDetails";
//    }
@Autowired
private IArticleService articleService;
    //跳转到订单详情页面
    @GetMapping(value = "/order/{id}")
    public String getArticleById(@PathVariable("id") Integer id, HttpServletRequest request) {
        //1.获取点击的文章对象
        Article article = articleService.selectArticleById(id);
        if (article != null) {
            request.setAttribute("article", article);
            return "client/orderDetails";
        } else {
            return "comm/error_404";
        }
    }
    //向支付页面跳转
    @RequestMapping("/pay")
    public String pay(){

        return "client/payDetails";
    }
    //向支付成功页面跳转
    @RequestMapping("/pay/success")
    public String paysuccess(){
        return "client/paySuccess";
    }
//    @PostMapping("/pay")
//    @ResponseBody

}
