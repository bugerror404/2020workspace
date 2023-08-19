package com.itheima.blogsystem.service;

import com.github.pagehelper.PageInfo;
import com.itheima.blogsystem.entity.Article;


import java.util.List;

public interface IArticleService {
    //分页查询文章列表
    public PageInfo<Article> selectArticelsByPage(Integer page,Integer count);
    //获取热度排名前十的文章列表
    public List<Article> getHeatArticle();
    //根据id查询文章
    public Article selectArticleById(Integer id);
    //发布文章
    public void publishArticle(Article article);
    //修改文章
    public void updateArticleById(Article article);
    //删除文章
    public void deleteArticleById(int id);
}
