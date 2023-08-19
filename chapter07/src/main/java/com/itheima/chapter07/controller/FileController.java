package com.itheima.chapter07.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

//访问页面的控制类
@Controller
public class FileController {
    @GetMapping("/detail/{type}/{path}")
    public String toDetail(@PathVariable("type") String type, @PathVariable("path") String path){
         return "detail/"+type+"/"+path;  //type决定用户 path决定页面路径
     }
     @GetMapping("/userLogin")
    public String toLoginPage(){
        return "login/login";
     }
     @GetMapping("/getUserBySession")
     @ResponseBody
    public void getUser(HttpSession session) {
         //从当前的Session中获取绑定到此会话的所有对象的名称
         Enumeration<String> names = session.getAttributeNames();
         while (names.hasMoreElements()) {
             //获取HttpSession中会话的名称
             String element = names.nextElement();
             //获取HttpSession中的应用上下文
             SecurityContextImpl attribute = (SecurityContextImpl) session.getAttribute(element);
             System.out.println("element:" + element);
             System.out.println("attribute" + attribute);
             //获取用户的相关信息
             Authentication authentication = attribute.getAuthentication();
             UserDetails principal = (UserDetails) authentication.getPrincipal();
             System.out.println(principal);
             System.out.println("username:" + principal.getUsername());

         }
     }
     @GetMapping("/getUserByContext")
     @ResponseBody
    public void getUser2(){ //推荐使用这种方式
        //获取应用上下文
         SecurityContext context = SecurityContextHolder.getContext();
         System.out.println("userDetails"+context);
         //获取用户相关信息
         Authentication authentication = context.getAuthentication();
         UserDetails principal = (UserDetails) authentication.getPrincipal();
         System.out.println(principal);
         System.out.println("username:" + principal.getUsername());


     }
}
