package com.example.modernhub.Controller;

import com.example.modernhub.model.User;
import com.example.modernhub.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() { return "login"; }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult result) {
        if (userService.usernameExists(user.getUsername())) {
            result.rejectValue("username", "error.user", "Bu kullanıcı adı zaten alınmış.");
        }
        if (userService.emailExists(user.getEmail())) {
            result.rejectValue("email", "error.user", "Bu e-posta zaten kayıtlı.");
        }
        if (result.hasErrors()) {
            return "register";
        }
        userService.registerUser(user);
        return "redirect:/login?registered=true";
    }
}
