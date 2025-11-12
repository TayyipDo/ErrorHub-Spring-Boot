package com.example.modernhub.Controller;

import com.example.modernhub.model.Category;
import com.example.modernhub.repository.CategoryRepository;
import com.example.modernhub.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepo;
    private final PostRepository postRepo;


    @GetMapping("/category/{id}")
    public String categoryPage(@PathVariable Long id, Model model) {
        var category = categoryRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kategori bulunamadı: " + id));

        model.addAttribute("category", category);
        model.addAttribute("posts", postRepo.findAllByCategoryId(id));
        return "category";
    }

}
