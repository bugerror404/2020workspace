package com.itheima.blogsystem.controller.admin;

import com.github.pagehelper.PageInfo;
import com.itheima.blogsystem.entity.Article;
import com.itheima.blogsystem.entity.Comment;
import com.itheima.blogsystem.entity.responseData.ResponseData;
import com.itheima.blogsystem.entity.responseData.StaticticsBo;
import com.itheima.blogsystem.service.IArticleService;
import com.itheima.blogsystem.service.ISiteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
   @Autowired
    private ISiteService siteService;
   @Autowired
   private IArticleService articleService;
   @GetMapping(value = {"","/index"})
    public String index(HttpServletRequest request){
       //获取后台主页所需的数据
       List<Article> articles=siteService.recentArticles(5);
       List<Comment> comments = siteService.recentComments(5);
       StaticticsBo staticticsBo=siteService.getStatistics();
       //向request中存放前台所需的数据
       request.setAttribute("articles",articles);
       request.setAttribute("comments",comments);
       request.setAttribute("statictics",staticticsBo);
       return "back/index";
   }
   //跳转到文章的首页
   @GetMapping("/article/toEditPage")
   public String newArticle(){
      return "back/article_edit";
   }
   //发布文章
   @PostMapping("/article/publish")
   @ResponseBody
   public ResponseData publishArticle(Article article){
      if (StringUtils.isBlank(article.getCategories())){
         article.setCategories("默认分类");
      }
      try{
         articleService.publishArticle(article);
         return ResponseData.ok();

      }catch (Exception e){
         return ResponseData.ok();
      }
   }
   //跳转到后台文章列表页面
   @GetMapping("/article")
   public String index(@RequestParam(value = "page",defaultValue = "1") int page,
                       @RequestParam(value = "count",defaultValue = "10") int count,
                       HttpServletRequest request){
      PageInfo<Article> pageInfo = articleService.selectArticelsByPage(page, count);
      request.setAttribute("articles",pageInfo);
      return "back/article_list";
   }
//跳转到文章修改页面
   @GetMapping("/article/{id}")
   public String editArticle(@PathVariable("id") Integer id,HttpServletRequest request){
      Article article = articleService.selectArticleById(id);
      request.setAttribute("contents",article);
      request.setAttribute("categories",article.getCategories());
      return "back/article_edit";

   }
   //修改文章
   @PostMapping("/article/modify")
   @ResponseBody
   public ResponseData modifyArticle(Article article){
      try{
         articleService.updateArticleById(article);
         return  ResponseData.ok();
      }catch (Exception e){
         return ResponseData.fail();
      }
   }
//删除文章
   @PostMapping("/article/delete")
   @ResponseBody
   public ResponseData deleteArticle(@RequestParam int id){
      try{
         articleService.deleteArticleById(id);
         return ResponseData.ok();
      }catch (Exception e){
         return ResponseData.fail();
      }
   }
}
