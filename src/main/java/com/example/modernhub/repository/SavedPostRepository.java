package com.example.modernhub.repository;

import com.example.modernhub.model.SavedPost;
import com.example.modernhub.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SavedPostRepository extends JpaRepository<SavedPost, Long> {

    Optional<SavedPost> findByUserAndPostId(User user, Long postId);

    List<SavedPost> findByUser(User user);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("delete from SavedPost sp where sp.post.id = :postId")
    void deleteByPostId(@Param("postId") Long postId);

    // Tekil silme
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    long deleteByIdAndUser_Id(Long id, Long userId);

    boolean existsByIdAndUser_Id(Long id, Long userId);
}
