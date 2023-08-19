package com.itheima.chapter03.domain;

import java.util.List;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private List<Comment> commentlist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getCommentlist() {
        return commentlist;
    }

    public void setCommentlist(List<Comment> commentlist) {
        this.commentlist = commentlist;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", commentlist=" + commentlist +
                '}';
    }
}
