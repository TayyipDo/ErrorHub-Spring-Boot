package com.example.modernhub.service;

import com.example.modernhub.model.User;
import com.example.modernhub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // Artık parametre "username" → DB’de "username" ile buluyoruz
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Kullanıcı bulunamadı"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername()) // dikkat: username döndürüyoruz
                .password(u.getPassword())
                .roles(u.getRoles().toArray(new String[0]))
                .build();
    }
}
