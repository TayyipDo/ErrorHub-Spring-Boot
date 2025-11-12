package com.example.modernhub.Controller; // Projendeki diğer controller’larla aynı paket!

import com.example.modernhub.model.User;
import com.example.modernhub.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserRepository userRepository;

    /** Admin yap*/
    @PostMapping("/users/{id}/make-admin")
    @Transactional
    public String makeAdmin(@PathVariable Long id,
                            @AuthenticationPrincipal UserDetails principal,
                            RedirectAttributes ra) {

        User target = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kullanıcı bulunamadı"));

        // roller set değilse başlat
        if (target.getRoles() == null) target.setRoles(new HashSet<>());

        boolean added = target.getRoles().add("ADMIN"); // Set => tekrar eklemez
        userRepository.save(target);

        ra.addFlashAttribute("msg", added
                ? (target.getUsername() + " admin yapıldı.")
                : (target.getUsername() + " zaten admin."));
        return "redirect:/admin"; // Zaten AdminController GET /admin sayfasını render ediyor
    }
}
