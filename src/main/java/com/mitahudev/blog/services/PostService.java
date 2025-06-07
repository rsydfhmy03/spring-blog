package com.mitahudev.blog.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mitahudev.blog.entity.Post;

@Service
public class PostService {

    private List<Post> posts = new ArrayList<>(List.of(
        new Post(1, "First Post", "This is the body of the first post", "slug1", true, false, 1633036800, 1633123200),
        new Post(2, "Second Post", "This is the body of the second post", "slug2", true, false, 1633123200, 1633209600)
    ));

    public List<Post> getPosts() {
        // This method should return a list of posts
        return posts;
    }

    public Post getPostBySlug(String slug) {
        // This method should return a post by its slug
        return posts.stream()
                    .filter(post -> post.getSlug().equals(slug))
                    .findFirst()
                    .orElse(null);
    }

    public Post createPost(Post newPost) {
        // This method should create a new post
        // In a real application, you would save the post to a database
        posts.add(newPost);
        return newPost;
    }   

    public Post updatePost(String slug, Post updatedPost) {
        // This method should update an existing post by its slug
        Post savedPost = posts.stream()
                              .filter(post -> post.getSlug().equals(slug))
                              .findFirst()
                              .orElse(null);
        if (savedPost != null) {
            savedPost.setTitle(updatedPost.getTitle());
            savedPost.setBody(updatedPost.getBody());
            savedPost.setPublished(updatedPost.isPublished());
            savedPost.setDeleted(updatedPost.isDeleted());
            savedPost.setCreatedAt(updatedPost.getCreatedAt());
            savedPost.setPublishedAt(updatedPost.getPublishedAt());
        }
        return savedPost;
    }

    public boolean deletePost(String slug) {
        // This method should delete a post by its slug
        Post postToDelete = posts.stream()
                                 .filter(post -> post.getSlug().equals(slug))
                                 .findFirst()
                                 .orElse(null);
        if (postToDelete != null) {
            posts.remove(postToDelete);
            return true;
        }
        return false;
    }
}
