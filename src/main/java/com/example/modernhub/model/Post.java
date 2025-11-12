package com.example.modernhub.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    /** Bu, post silinince saved_post kayıtlarının da gitmesini sağlar. */
    @OneToMany(mappedBy = "post",
            cascade = CascadeType.REMOVE,
            orphanRemoval = true)
    @Builder.Default
    private List<SavedPost> savedPosts = new ArrayList<>();
}
