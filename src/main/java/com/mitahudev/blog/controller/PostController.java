package com.mitahudev.blog.controller;

import org.springframework.web.bind.annotation.RestController;

import com.mitahudev.blog.entity.Post;
import com.mitahudev.blog.services.PostService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class PostController {
    
    // Post post1 = new Post(1, "First Post", "This is the body of the first post", "slug1", true, false, 1633036800, 1633123200);
    // Post post2 = new Post(2, "Second Post", "This is the body of the second post", "slug2", true, false, 1633123200, 1633209600);

    // List<Post> posts = List.of(post1, post2);
    // List<Post> posts = new ArrayList<>(List.of(
    //     new Post(1, "First Post", "This is the body of the first post", "slug1", true, false, 1633036800, 1633123200),
    //     new Post(2, "Second Post", "This is the body of the second post", "slug2", true, false, 1633123200, 1633209600)
    // ));

    @Autowired
    private PostService postService;


    @GetMapping("/")
    public List<Post> getPosts() {
        // This method should return a list of posts
        // For now, we will return an empty list
        return postService.getPosts();
    }

    @GetMapping("/{slug}")
    public Post getPostBySlug(@PathVariable String slug) {
        // This method should return a post by its slug
        // For now, we will return the first post if the slug matches
        return postService.getPostBySlug(slug);
    }

    @PostMapping("/")
    public Post createPost(@RequestBody Post newPost) {
        // This method should create a new post
        // For now, we will return the post as is
        // In a real application, you would save the post to a database
        return postService.createPost(newPost);
    }

    @PutMapping("/{slug}")
    public Post updatePost(@PathVariable String slug, @RequestBody Post updatedPost) {
        // This method should update an existing post by its slug
        // For now, we will return the updated post if the slug matches
        return postService.updatePost(slug, updatedPost);
    }

    @DeleteMapping("/{id}")
    public boolean deletePostById(@PathVariable Integer id) {
        // This method should delete a post by its slug
        // For now, we will just remove the post from the list if it exists
        return postService.deletePost(String.valueOf(id));
    }
    
}
