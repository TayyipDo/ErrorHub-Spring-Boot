package com.example.modernhub.Controller;

import com.example.modernhub.repository.CategoryRepository;
import com.example.modernhub.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(value = "q", required = false) String q, Model model) {
        model.addAttribute("categories", categoryRepository.findAll());

        if (q != null && !q.isBlank()) {
            model.addAttribute("posts", postRepository.findByTitleContainingIgnoreCase(q)); // <-- örnek üzerinden çağrı
            model.addAttribute("q", q);
        } else {
            model.addAttribute("posts", postRepository.findAll()); // <-- örnek üzerinden çağrı
        }
        return "home";
    }
}
