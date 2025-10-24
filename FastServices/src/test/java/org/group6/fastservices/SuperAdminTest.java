package org.group6.fastservices;

import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class SuperAdminTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testCreateSuperAdmin() {
        // Create super admin user
        User superAdmin = new User();
        superAdmin.setFirstName("Super");
        superAdmin.setLastName("Admin");
        superAdmin.setEmail("admin@fastservices.com");
        superAdmin.setPhone("1234567890");
        superAdmin.setPassword(passwordEncoder.encode("SuperAdmin@123"));

        Set<Role> roles = new HashSet<>();
        roles.add(Role.SUPER_ADMIN);
        superAdmin.setRoles(roles);

        // Save super admin
        User savedUser = userRepository.save(superAdmin);

        // Verify the super admin was saved
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getEmail()).isEqualTo("admin@fastservices.com");
        assertThat(savedUser.getRolesAsSet()).contains(Role.SUPER_ADMIN);

        // Clean up - delete the test user
        userRepository.deleteById(savedUser.getId());
    }

    @Test
    public void testFindUsersByRole() {
        // First create a test user with ORG_ADMIN role
        User orgAdmin = new User();
        orgAdmin.setFirstName("Org");
        orgAdmin.setLastName("Admin");
        orgAdmin.setEmail("orgadmin@test.com");
        orgAdmin.setPhone("1234567891");
        orgAdmin.setPassword(passwordEncoder.encode("TestPass123"));

        Set<Role> roles = new HashSet<>();
        roles.add(Role.ORG_ADMIN);
        orgAdmin.setRoles(roles);

        User savedOrgAdmin = userRepository.save(orgAdmin);

        // Find users with ORG_ADMIN role
        var orgAdmins = userRepository.findByRolesContaining("ORG_ADMIN");
        
        assertThat(orgAdmins).isNotEmpty();
        assertThat(orgAdmins.get(0).getEmail()).isEqualTo("orgadmin@test.com");

        // Clean up
        userRepository.deleteById(savedOrgAdmin.getId());
    }
}