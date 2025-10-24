package org.group6.fastservices.config;

import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            try {
                String superAdminEmail = "oderindesuliha@gmail.com";

                if (!userRepository.existsByEmail(superAdminEmail)) {
                    User superAdmin = new User();
                    superAdmin.setFirstName("Suliha");
                    superAdmin.setLastName("Oderinde");
                    superAdmin.setEmail(superAdminEmail);
                    superAdmin.setPhone("08139338208");

                    // Secure password (you can override via environment variable)
                    String defaultPassword = System.getenv().getOrDefault("SUPER_ADMIN_PASSWORD", "FastService@123");
                    superAdmin.setPassword(passwordEncoder.encode(defaultPassword));

                    // Assign SUPER_ADMIN role
                    superAdmin.setRoles(Set.of(Role.SUPER_ADMIN));

                    userRepository.save(superAdmin);
                    System.out.println("✅ Super Admin created: " + superAdminEmail + " / " + defaultPassword);
                } else {
                    System.out.println("✅ Super Admin already exists.");
                }
            } catch (Exception e) {
                System.err.println("❌ Error creating Super Admin: " + e.getMessage());
                e.printStackTrace();
            }
        };
    }
}
