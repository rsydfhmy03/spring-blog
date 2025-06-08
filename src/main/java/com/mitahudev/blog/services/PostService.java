package com.mitahudev.blog.services;

import java.time.Instant;
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
        // return ((List<Post>) postRepository.findAll()).stream()
        //             .filter(post -> post.getSlug().equals(slug))
        //             .findFirst()
        //             .orElse(null);
        return postRepository.findBySlugAndIsDeletedFalse(slug)
                .orElse(null);
    }

    public Post createPost(Post newPost) {
        newPost.setCreatedAt(Instant.now().getEpochSecond()); // Set waktu pembuatan
        // newPost.setPublished(false); // Default tidak dipublikasikan
        // newPost.setDeleted(false); // Default tidak dihapus
        // newPost.setPublishedAt(null); // Set waktu publikasi ke null
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
            postToDelete.setDeleted(true); // Tandai sebagai dihapus
            postRepository.save(postToDelete);
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
            // Tandai sebagai dihapus
            postRepository.findById(id).ifPresent(post -> post.setDeleted(true));
            postRepository.save(postRepository.findById(id).get());
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
        savedPost.setPublishedAt(Instant.now().getEpochSecond()); // Set waktu publikasi
        return postRepository.save(savedPost);
    }
}