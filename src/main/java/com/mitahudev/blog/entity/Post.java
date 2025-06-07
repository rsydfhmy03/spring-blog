package com.mitahudev.blog.entity;
import lombok.Data;
@Data
public class Post {
    private Integer id;
    private String title;
    private String body;
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Integer createdAt;
    private Integer publishedAt;
    
    public Post(Integer id, String title, String body, String slug, boolean isPublished, boolean isDeleted, Integer createdAt, Integer publishedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.slug = slug;
        this.isPublished = isPublished;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
    }
} 
