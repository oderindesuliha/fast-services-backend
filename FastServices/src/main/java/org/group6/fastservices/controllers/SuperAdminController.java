package org.group6.fastservices.controllers;

import org.group6.fastservices.data.models.User;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.dtos.responses.UserResponse;
import org.group6.fastservices.services.UserService;
import org.group6.fastservices.utils.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/super-admin")
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class SuperAdminController {

    private final UserService userService;

    public SuperAdminController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all users in the system
     * GET /api/super-admin/users
     */
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            List<UserResponse> responses = users.stream()
                    .map(Mapper::mapToUserResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    /**
     * Delete any user by ID
     * DELETE /api/super-admin/users/{id}
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    /**
     * Get system statistics
     * GET /api/super-admin/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<?> getSystemStats() {
        try {
            List<User> allUsers = userService.getAllUsers();
            long totalUsers = allUsers.size();
            long orgAdmins = allUsers.stream()
                    .filter(user -> user.getRolesAsSet().contains(org.group6.fastservices.data.models.Role.ORG_ADMIN))
                    .count();
            long staff = allUsers.stream()
                    .filter(user -> user.getRolesAsSet().contains(org.group6.fastservices.data.models.Role.STAFF))
                    .count();
            long customers = allUsers.stream()
                    .filter(user -> user.getRolesAsSet().contains(org.group6.fastservices.data.models.Role.CUSTOMER))
                    .count();

            return ResponseEntity.ok(new SystemStatsResponse(totalUsers, orgAdmins, staff, customers));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    // Inner class for system stats response
    private static class SystemStatsResponse {
        private final long totalUsers;
        private final long orgAdmins;
        private final long staff;
        private final long customers;

        public SystemStatsResponse(long totalUsers, long orgAdmins, long staff, long customers) {
            this.totalUsers = totalUsers;
            this.orgAdmins = orgAdmins;
            this.staff = staff;
            this.customers = customers;
        }

        public long getTotalUsers() {
            return totalUsers;
        }

        public long getOrgAdmins() {
            return orgAdmins;
        }

        public long getStaff() {
            return staff;
        }

        public long getCustomers() {
            return customers;
        }
    }
}