package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.User;
import org.group6.fastservices.data.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testFindByEmail() {
        Optional<User> user = userRepository.findByEmail("chinedu.okonkwo@gmail.com");
        assertTrue(user.isPresent());
        assertEquals("Chinedu", user.get().getFirstName());
        assertEquals("Okonkwo", user.get().getLastName());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testExistsByEmail() {
        boolean exists = userRepository.existsByEmail("adunni.alao@gmail.com");
        assertTrue(exists);
    }


}