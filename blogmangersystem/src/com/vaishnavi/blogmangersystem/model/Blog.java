package com.vaishnavi.blogmangersystem.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private int blogId;
    private String title;
    private LocalDate date;
    private String content;

    public Blog() {
    }

    // Constructor useful for searching by ID
    public Blog(int blogId) {
        this.blogId = blogId;
    }

    public Blog(int blogId, String title, LocalDate date, String content) {
        this.blogId = blogId;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog [blogId=" + blogId + ", title=" + title + ", date=" + date + ", content=" + content + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + blogId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Blog other = (Blog) obj;
        if (blogId != other.blogId)
            return false;
        return true;
    }

}
