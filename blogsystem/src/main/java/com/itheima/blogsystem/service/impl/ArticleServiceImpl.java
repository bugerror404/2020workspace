package com.itheima.blogsystem.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.blogsystem.entity.Article;
import com.itheima.blogsystem.entity.Statistic;
import com.itheima.blogsystem.mapper.ArticleMapper;
import com.itheima.blogsystem.mapper.CommentMapper;
import com.itheima.blogsystem.mapper.StatisticMapper;
import com.itheima.blogsystem.service.IArticleService;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ArticleServiceImpl implements IArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private StatisticMapper statisticMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommentMapper commentMapper;
    @Override    //分页查询文章列表
    public PageInfo<Article> selectArticelsByPage(Integer page, Integer count) {
        PageHelper.startPage(page,count);
        List<Article> articleList=articleMapper.selectArticles();
        //封装文章的统计数据
        for (Article article:articleList){
            Statistic statistic = statisticMapper.selectByAid(article.getId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
        }
        PageInfo<Article> pageInfo = new PageInfo<>(articleList);
        return pageInfo;
    }

    @Override   //获取热度排名前十的文章列表
    public List<Article> getHeatArticle() {
        List<Statistic> list = statisticMapper.getStatistic();
        ArrayList<Article> articleList = new ArrayList<>();
        int i=0;
        for (Statistic statistic:list){
            Article article = articleMapper.selectById(statistic.getArticleId());
            article.setHits(statistic.getHits());
            article.setCommentsNum(statistic.getCommentsNum());
            articleList.add(article);
            i++;
            if (i>=9){
                break;
            }
        }
        return articleList;
    }

    @Override
    public Article selectArticleById(Integer id) {
        Article article=null;
        Object o = redisTemplate.opsForValue().get("article_" + id);
        if (o!=null){
            article=(Article)o;
        }else{
            article = articleMapper.selectById(id);
            if (article!=null){
                redisTemplate.opsForValue().set("article_"+id,article);
            }
        }
        return article;
    }

    @Override
    public void publishArticle(Article article) {
        //去除文章中的表情
        article.setContent(EmojiParser.parseToAliases(article.getContent()));
        article.setCreated(new Date());
        article.setHits(0);
        article.setCommentsNum(0);

        articleMapper.insert(article);
        //方法一：StatisticMapper中新增一个方法addStatistic(Article article)
        statisticMapper.addStatistic(article);
        //方法二：利用系统自动生成的insert方法，构建所需的Statistic对象
//        Statistic statistic=new Statistic();
//        statistic.setCommentsNum(0);
//        statistic.setHits(0);
//        statistic.setArticleId(article.getId());
//        statisticMapper.insert(statistic);
    }
//修改文章
    @Override
    public void updateArticleById(Article article) {

       article.setModified(new Date());
       articleMapper.updateByPrimaryKeySelective(article);
       redisTemplate.delete("article_"+article.getId());
    }
//删除文章
    @Override
    public void deleteArticleById(int id) {
        //1.删除文章
        articleMapper.deleteByPrimaryKey(id);
        //2.删除缓存
        redisTemplate.delete("article_"+id);
        //3.删除统计数据
        statisticMapper.deleteByPrimaryKey(id);
        //4.删除评论数据
        commentMapper.deleteByAId(id);



    }
}
