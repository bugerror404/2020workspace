package com.itheima.chapter03;

import com.itheima.chapter03.domain.Article;
import com.itheima.chapter03.domain.Comment;
import com.itheima.chapter03.mapper.ArticleMapper;
import com.itheima.chapter03.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Chapter03ApplicationTests {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Test
    void contextLoads() {
    }
    @Test
    public void findById(){
        Comment comment = commentMapper.findById(1);
        System.out.println(comment);

    }
    @Test
    public void insertComment(){
        Comment comment=new Comment("yyyyyy","hhhh",2);
        int result = commentMapper.insertComment(comment);
        System.out.println(result);
    }
    @Test
    public void updateComment(){
        Comment coment=new Comment();
        coment.setId(6);
        coment.setContent("mmm");
        coment.setAuthor("wwww");
        //Comment comment = new Comment("mmm","www",2);
        //int result1 = commentMapper.updateComment(coment);

        int result1 = commentMapper.updateComment(coment);

        System.out.println(result1);
    }
    @Test
    public void deleteComment(){

        commentMapper.deleteComment(6);
    }
    @Test
    public void selectArticle(){
        Article article = articleMapper.findById(1);
        System.out.println(article);
    }
    @Test
    public void updateArticle(){
        Article article=new Article();
        article.setId(1);
        article.setContent("美好");
        article.setTitle("二月二十三");
        articleMapper.updateArticle(article);
    }
}
