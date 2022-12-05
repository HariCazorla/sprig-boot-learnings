package com.shreeharibi.basicauth.config;

import com.shreeharibi.basicauth.model.AppUser;
import com.shreeharibi.basicauth.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class init {

    private final BCryptPasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository) {
        return args -> {
            AppUser admin = new AppUser("admin", passwordEncoder.encode("pass"), "admin");
            AppUser normal = new AppUser("shree", passwordEncoder.encode("shree"), "user");
            appUserRepository.saveAll(List.of(admin, normal));
        };
    }
}
