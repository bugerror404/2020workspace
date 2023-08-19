package com.itheima.blogsystem;

import com.itheima.blogsystem.entity.Article;
import com.itheima.blogsystem.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogsystemApplicationTests {
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void selectArticle(){
        Article article = articleMapper.selectById(1);
        System.out.println(article);


    }

}
