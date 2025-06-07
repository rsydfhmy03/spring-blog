package com.mitahudev.blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitahudev.blog.entity.Post;
import com.mitahudev.blog.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    
    public List<Post> getPosts() {
        return (List<Post>) postRepository.findAll();
    }

    public Post getPostBySlug(String slug) {
        return ((List<Post>) postRepository.findAll()).stream()
                    .filter(post -> post.getSlug().equals(slug))
                    .findFirst()
                    .orElse(null);
    }

    public Post createPost(Post newPost) {
        return postRepository.save(newPost);
    }   

    public Post updatePost(String slug, Post updatedPost) {
        Post existingPost = getPostBySlug(slug);
        if (existingPost != null) {
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setBody(updatedPost.getBody());
            existingPost.setSlug(updatedPost.getSlug());
            existingPost.setPublished(updatedPost.isPublished());
            existingPost.setDeleted(updatedPost.isDeleted());
            existingPost.setCreatedAt(updatedPost.getCreatedAt());
            existingPost.setPublishedAt(updatedPost.getPublishedAt());
            return postRepository.save(existingPost);
        }
        return null;
    }

    public boolean deletePost(String slug) {
        Post postToDelete = getPostBySlug(slug);
        if (postToDelete != null) {
            postRepository.delete(postToDelete);
            return true;
        }
        return false;
    }
    
    // Method tambahan untuk operasi CRUD yang lebih efisien
    public Post getPostById(Integer id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }
    
    public Post updatePostById(Integer id, Post updatedPost) {
        Optional<Post> existingPostOpt = postRepository.findById(id);
        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setTitle(updatedPost.getTitle());
            existingPost.setBody(updatedPost.getBody());
            existingPost.setSlug(updatedPost.getSlug());
            existingPost.setPublished(updatedPost.isPublished());
            existingPost.setDeleted(updatedPost.isDeleted());
            existingPost.setCreatedAt(updatedPost.getCreatedAt());
            existingPost.setPublishedAt(updatedPost.getPublishedAt());
            return postRepository.save(existingPost);
        }
        return null;
    }
    
    public boolean deletePostById(Integer id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Post publishPost(Integer id) {
        Post savedPost = postRepository.findById(id).orElse(null);

        if (savedPost == null) {
            return null; // Post tidak ditemukan
        }
        savedPost.setPublished(true);
        savedPost.setPublishedAt((int) (System.currentTimeMillis() / 1000)); // Set waktu publikasi
        return postRepository.save(savedPost);
    }
}