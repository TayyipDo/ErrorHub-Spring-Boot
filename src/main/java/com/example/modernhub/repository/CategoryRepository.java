package com.example.modernhub.repository;

import com.example.modernhub.model.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;



public interface CategoryRepository extends JpaRepository<Category, Long> {
}

