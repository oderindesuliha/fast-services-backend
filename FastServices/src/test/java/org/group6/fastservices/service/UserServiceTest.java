package org.group6.fastservices.service;

import org.group6.fastservices.data.models.User;
import org.group6.fastservices.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testGetUserById() {
        User user = userService.getUserById("12345");
        assertNotNull(user);
        assertEquals("Chinedu", user.getFirstName());
        assertEquals("Okonkwo", user.getLastName());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testGetUserByEmail() {
        User user = userService.getUserByEmail("adunni.alao@gmail.com");
        assertNotNull(user);
        assertEquals("Adunni", user.getFirstName());
        assertEquals("Alao", user.getLastName());
    }
}