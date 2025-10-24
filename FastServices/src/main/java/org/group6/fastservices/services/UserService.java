package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.models.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(String id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    List<User> getAllUsersWithRole(Role role);
    User updateUser(String id, User user);
    void deleteUser(String id);
    boolean existsByEmail(String email);
}