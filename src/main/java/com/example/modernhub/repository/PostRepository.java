// 📄 PostRepository.java
package com.example.modernhub.repository;

import com.example.modernhub.model.Category;
import com.example.modernhub.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import java.util.List;
import com.example.modernhub.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String q);
    List<Post> findAllByCategoryId(Long categoryId);
}

