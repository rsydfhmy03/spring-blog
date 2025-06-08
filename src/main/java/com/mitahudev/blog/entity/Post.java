package com.mitahudev.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private String body;
    @Column(unique = true)
    private String slug;
    private boolean isPublished;
    private boolean isDeleted;
    private Long createdAt;
    private Long publishedAt;
    
    // Constructor tanpa ID untuk membuat post baru
    public Post(String title, String body, String slug, boolean isPublished, 
                boolean isDeleted, Long createdAt, Long publishedAt) {
        this.title = title;
        this.body = body;
        this.slug = slug;
        this.isPublished = isPublished;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
    }
}