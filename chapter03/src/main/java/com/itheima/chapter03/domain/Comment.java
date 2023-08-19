package com.itheima.chapter03.domain;

import java.io.Serializable;

public class Comment implements Serializable { //实体类缓存序列化
    private Integer id;
    private String content;
    private String author;
    private Integer aId; //文章编号

    public Comment() {
    }

    public Comment(String content, String author, Integer aId) {
        this.content = content;
        this.author = author;
        this.aId = aId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}
