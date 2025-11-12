package com.example.modernhub;

import com.example.modernhub.repository.CategoryRepository;
import com.example.modernhub.repository.PostRepository;
import com.example.modernhub.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ModernhubApplication {



    @Bean
    CommandLineRunner seed(CategoryRepository categoryRepo, PostRepository postRepo) {
        return args -> {
            if (categoryRepo.count() == 0) {
                var linux  = categoryRepo.save(com.example.modernhub.model.Category.builder().name("Linux").build());
                var docker = categoryRepo.save(com.example.modernhub.model.Category.builder().name("Docker").build());
                postRepo.save(com.example.modernhub.model.Post.builder()
                        .title("bash -x hatası")
                        .content("Bash scriptini debug etmek için -x bayrağı kullanılır. Hata çıktısını detaylandırır.")
                        .category(linux).build());
                postRepo.save(com.example.modernhub.model.Post.builder()
                        .title("sudo: command not found")
                        .content("sudo paketi eksik olabilir veya PATH yanlış. Debian/Ubuntu: apt install sudo")
                        .category(linux).build());
                postRepo.save(com.example.modernhub.model.Post.builder()
                        .title("docker: permission denied")
                        .content("Kullanıcıyı docker grubuna ekleyip oturumu yeniden başlatın: usermod -aG docker $USER")
                        .category(docker).build());
            }
        };
    }


    @Bean
    CommandLineRunner makeAdmin(UserRepository users, PasswordEncoder encoder) {
        return args -> users.findByUsername("admin").orElseGet(() ->
                users.save(com.example.modernhub.model.User.builder()
                        .username("admin")
                        .email("admin@example.com")
                        .password(encoder.encode("Admin1234"))
                        .roles(new java.util.HashSet<>(java.util.List.of("ADMIN","USER")))
                        .build())
        );
    }



    public static void main(String[] args) {

        SpringApplication.run(ModernhubApplication.class, args);
	}

}
