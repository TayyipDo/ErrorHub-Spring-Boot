package com.example.modernhub.Controller;

import com.example.modernhub.model.Category;
import com.example.modernhub.model.Post;
import com.example.modernhub.repository.CategoryRepository;
import com.example.modernhub.repository.PostRepository;
import com.example.modernhub.repository.SavedPostRepository;
import com.example.modernhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Transactional
public class AdminController {

    private final CategoryRepository categoryRepo;
    private final PostRepository postRepo;
    private final SavedPostRepository savedPostRepo;
    private final UserRepository userRepo;


    @GetMapping
    public String adminPanel(Model m) {
        m.addAttribute("users", userRepo.findAll());
        m.addAttribute("categories", categoryRepo.findAll());
        m.addAttribute("posts", postRepo.findAll());
        return "admin";
    }

    // POST SİL
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id, RedirectAttributes ra) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        savedPostRepo.deleteByPostId(id); // bağlı kayıtları temizle
        postRepo.delete(post);

        ra.addFlashAttribute("msg", "Post silindi");
        return "redirect:/admin";
    }

    // KATEGORİ SİL
    @PostMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable Long id, RedirectAttributes ra) {
        Category cat = categoryRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Post> posts = postRepo.findAllByCategoryId(id);
        for (Post p : posts) {
            savedPostRepo.deleteByPostId(p.getId());
        }
        postRepo.deleteAll(posts);
        categoryRepo.delete(cat);

        ra.addFlashAttribute("msg", "Kategori silindi");
        return "redirect:/admin";
    }

    // KATEGORİ EKLE
    @PostMapping("/add-category")
    public String addCategory(@RequestParam String name, RedirectAttributes ra) {
        Category newCategory = Category.builder().name(name).build();
        categoryRepo.save(newCategory);
        ra.addFlashAttribute("msg", "Kategori eklendi");
        return "redirect:/admin";
    }

    // POST EKLE
    @PostMapping("/add-post")
    public String addPost(@RequestParam String title,
                          @RequestParam String content,
                          @RequestParam Long categoryId,
                          RedirectAttributes ra) {

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Post newPost = Post.builder()
                .title(title)
                .content(content)
                .category(category)
                .build();

        postRepo.save(newPost);
        ra.addFlashAttribute("msg", "Post eklendi");
        return "redirect:/admin";
    }
}
