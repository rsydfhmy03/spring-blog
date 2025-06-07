package com.mitahudev.blog.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;

import com.mitahudev.blog.entity.Post;
import com.mitahudev.blog.services.PostService;
import com.mitahudev.blog.response.ApiResponse;
import com.mitahudev.blog.response.ResponseHelper;
import com.mitahudev.blog.response.MetaData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // GET /api/posts - Mendapatkan semua posts
    @GetMapping
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
        try {
            List<Post> posts = postService.getPosts();
            MetaData meta = MetaData.simple(posts.size());
            return ResponseHelper.success(posts, "Posts retrieved successfully", meta);
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to retrieve posts: " + e.getMessage());
        }
    }

    // GET /api/posts/{slug} - Mendapatkan post berdasarkan slug
    @GetMapping("/{slug}")
    public ResponseEntity<ApiResponse<Post>> getPostBySlug(@PathVariable String slug) {
        try {
            Post post = postService.getPostBySlug(slug);
            if (post != null) {
                return ResponseHelper.success(post, "Post found successfully");
            }
            return ResponseHelper.notFound("Post with slug '" + slug + "' not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to retrieve post: " + e.getMessage());
        }
    }

    // GET /api/posts/id/{id} - Mendapatkan post berdasarkan ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable Integer id) {
        try {
            Post post = postService.getPostById(id);
            if (post != null) {
                return ResponseHelper.success(post, "Post found successfully");
            }
            return ResponseHelper.notFound("Post with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to retrieve post: " + e.getMessage());
        }
    }

    // POST /api/posts - Membuat post baru
    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(@RequestBody Post newPost) {
        try {
            // Validation
            if (newPost.getTitle() == null || newPost.getTitle().trim().isEmpty()) {
                return ResponseHelper.badRequest("Title is required");
            }
            if (newPost.getSlug() == null || newPost.getSlug().trim().isEmpty()) {
                return ResponseHelper.badRequest("Slug is required");
            }
            
            // Set timestamp untuk post baru
            newPost.setCreatedAt((int) (System.currentTimeMillis() / 1000));
            Post createdPost = postService.createPost(newPost);
            return ResponseHelper.created(createdPost, "Post created successfully");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to create post: " + e.getMessage());
        }
    }

    // PUT /api/posts/{slug} - Update post berdasarkan slug
    @PutMapping("/{slug}")
    public ResponseEntity<ApiResponse<Post>> updatePostBySlug(@PathVariable String slug, @RequestBody Post updatedPost) {
        try {
            Post post = postService.updatePost(slug, updatedPost);
            if (post != null) {
                return ResponseHelper.success(post, "Post updated successfully");
            }
            return ResponseHelper.notFound("Post with slug '" + slug + "' not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to update post: " + e.getMessage());
        }
    }

    // PUT /api/posts/id/{id} - Update post berdasarkan ID
    @PutMapping("/id/{id}")
    public ResponseEntity<ApiResponse<Post>> updatePostById(@PathVariable Integer id, @RequestBody Post updatedPost) {
        try {
            Post post = postService.updatePostById(id, updatedPost);
            if (post != null) {
                return ResponseHelper.success(post, "Post updated successfully");
            }
            return ResponseHelper.notFound("Post with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to update post: " + e.getMessage());
        }
    }

    // DELETE /api/posts/{slug} - Hapus post berdasarkan slug
    @DeleteMapping("/{slug}")
    public ResponseEntity<ApiResponse<String>> deletePostBySlug(@PathVariable String slug) {
        try {
            boolean deleted = postService.deletePost(slug);
            if (deleted) {
                return ResponseHelper.success("Post deleted successfully", "Post with slug '" + slug + "' has been deleted");
            }
            return ResponseHelper.notFound("Post with slug '" + slug + "' not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to delete post: " + e.getMessage());
        }
    }

    // DELETE /api/posts/id/{id} - Hapus post berdasarkan ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<ApiResponse<String>> deletePostById(@PathVariable Integer id) {
        try {
            boolean deleted = postService.deletePostById(id);
            if (deleted) {
                return ResponseHelper.success("Post deleted successfully", "Post with ID " + id + " has been deleted");
            }
            return ResponseHelper.notFound("Post with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to delete post: " + e.getMessage());
        }
    }

    // PUT /api/posts/id/{id}/publish - Publish post berdasarkan ID
    @PutMapping("/id/{id}/publish")
    public ResponseEntity<ApiResponse<Post>> publishPost(@PathVariable Integer id) {
        try {
            Post publishedPost = postService.publishPost(id);
            if (publishedPost != null) {
                return ResponseHelper.success(publishedPost, "Post published successfully");
            }
            return ResponseHelper.notFound("Post with ID " + id + " not found");
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to publish post: " + e.getMessage());
        }
    }

    // GET /api/posts/published - Mendapatkan semua post yang dipublikasikan
    @GetMapping("/published")
    public ResponseEntity<ApiResponse<List<Post>>> getPublishedPosts() {
        try {
            List<Post> publishedPosts = postService.getPosts().stream()
                    .filter(Post::isPublished)
                    .filter(post -> !post.isDeleted())
                    .toList();
            
            MetaData meta = MetaData.simple(publishedPosts.size());
            return ResponseHelper.success(publishedPosts, "Published posts retrieved successfully", meta);
        } catch (Exception e) {
            return ResponseHelper.internalError("Failed to retrieve published posts: " + e.getMessage());
        }
    }
}