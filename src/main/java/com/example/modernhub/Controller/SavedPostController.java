package com.example.modernhub.Controller;

import com.example.modernhub.model.Post;
import com.example.modernhub.model.SavedPost;
import com.example.modernhub.model.User;
import com.example.modernhub.repository.PostRepository;
import com.example.modernhub.repository.SavedPostRepository;
import com.example.modernhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SavedPostController {

    private final SavedPostRepository savedPostRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    private User currentUser(UserDetails principal) {
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Kullanıcı bulunamadı"));
    }

    /** Kaydedilenler sayfası */
    @GetMapping("/saved")
    public String saved(@AuthenticationPrincipal UserDetails principal, Model model) {
        User user = currentUser(principal);
        List<SavedPost> list = savedPostRepository.findByUser(user);
        model.addAttribute("savedPosts", list);
        return "savedPost";
    }

    /** Tekil silme*/
    @PostMapping("/saved/{id}/delete")
    public String deleteOne(@PathVariable Long id,
                            @AuthenticationPrincipal UserDetails principal,
                            @RequestHeader(value = "Referer", required = false) String referer,
                            RedirectAttributes ra) {
        User user = currentUser(principal);
        long n = savedPostRepository.deleteByIdAndUser_Id(id, user.getId());
        ra.addFlashAttribute("msg", n > 0 ? "Kayıt silindi." : "Kayıt bulunamadı.");
        return "redirect:" + (referer != null ? referer : "/saved");
    }

    /** Kaydet butonu - POST*/
    @PostMapping("/save/{postId}")
    public String save(@PathVariable Long postId,
                       @AuthenticationPrincipal UserDetails principal,
                       @RequestHeader(value = "Referer", required = false) String referer,
                       RedirectAttributes ra) {
        User user = currentUser(principal);


        if (savedPostRepository.findByUserAndPostId(user, postId).isPresent()) {
            ra.addFlashAttribute("msg", "Bu post zaten kaydedilmiş.");
            return "redirect:" + (referer != null ? referer : "/saved");
        }

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post bulunamadı"));

        SavedPost sp = new SavedPost();
        sp.setUser(user);
        sp.setPost(post);
        savedPostRepository.save(sp);

        ra.addFlashAttribute("msg", "Kaydedildi.");
        return "redirect:" + (referer != null ? referer : "/saved");
    }
}
