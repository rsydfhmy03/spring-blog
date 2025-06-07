package com.mitahudev.blog.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mitahudev.blog.entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    
    // Method untuk mencari post berdasarkan slug
    Optional<Post> findBySlug(String slug);
    
    // Method untuk mencari post yang dipublikasikan
    List<Post> findByIsPublishedTrue();
    
    // Method untuk mencari post yang tidak dihapus
    List<Post> findByIsDeletedFalse();
    
    // Method untuk mencari post yang dipublikasikan dan tidak dihapus
    List<Post> findByIsPublishedTrueAndIsDeletedFalse();
    
    // Custom query untuk mencari post berdasarkan title yang mengandung keyword
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:keyword% AND p.isDeleted = false")
    List<Post> findByTitleContainingAndNotDeleted(@Param("keyword") String keyword);
    
    // Method untuk menghitung jumlah post yang dipublikasikan
    long countByIsPublishedTrue();
}