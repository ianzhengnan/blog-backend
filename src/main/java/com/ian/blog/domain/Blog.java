package com.ian.blog.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document
public class Blog implements Serializable{

    @Id
    private String id;
    private String title;
    private String uri;
    private Catalog catalog;
    private String content;
    private Long visitCount = 0L;
//    private List<Comment> comments = new ArrayList<>();
    private LocalDateTime createAt;
    private LocalDateTime lastModifyAt;
    private int year;
    private int month;

    public Blog() {

    }

    {
        lastModifyAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

//    public List<Comment> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<Comment> comments) {
//        this.comments = comments;
//    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
        setYear(this.createAt.getYear());
        setMonth(this.createAt.getMonth().getValue());
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public LocalDateTime getLastModifyAt() {
        return lastModifyAt;
    }

    public void setLastModifyAt(LocalDateTime lastModifyAt) {
        this.lastModifyAt = lastModifyAt;
    }
}
