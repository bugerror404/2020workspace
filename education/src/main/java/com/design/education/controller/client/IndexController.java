package com.design.education.controller.client;


import com.design.education.entity.Article;
import com.design.education.entity.Comment;
import com.design.education.service.IArticleService;
import com.design.education.service.ICommentService;
import com.design.education.service.ISiteService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ISiteService siteService;
    @Autowired
    private ICommentService commentService;
    //1.跳转到博客首页
    @GetMapping("/")
    public String index(HttpServletRequest request){

        return this.index(request,1,7);
    }

    //2.跳转到文章的第几页
    @GetMapping("/page/{p}")

    public String index(HttpServletRequest request, @PathVariable("p") int page, @RequestParam(value = "count",defaultValue = "7")int count){
        PageInfo<Article> articles = articleService.selectArticelsByPage(page, count);
        List<Article> articleList = articleService.getHeatArticle();
        request.setAttribute("articles",articles);
        request.setAttribute("articleList",articleList);
        return "client/index";
    }
    //跳转到文章详情页面
    @GetMapping(value = "/article/{id}")
    public String getArticleById(@PathVariable("id") Integer id, HttpServletRequest request){
        //1.获取点击的文章对象
        Article article = articleService.selectArticleById(id);
        //当点击文章链接后，文章的点击量就改变了，统计数据就改变了
        if (article!=null){
            //更新文章的点击量
            siteService.updateStatistic(article);
            //获取文章对应的评论内容
            //getCommentById(request,article);
            request.setAttribute("article",article);
            return "client/articleDetails";
        }else{
            return "comm/error_404";
        }


    }
    private void getCommentById(HttpServletRequest request,Article article){
        if (article.getAllowComment()){
            //cp表示评论页码，从页面传来参数
            String cp=request.getParameter("cp");
            cp= StringUtils.isBlank(cp)?"1":cp;
            request.setAttribute("cp",cp);
            PageInfo<Comment> comments = commentService.getComments(article.getId(), Integer.parseInt(cp), 3);
            request.setAttribute("comments",comments);
        }

    }
}
