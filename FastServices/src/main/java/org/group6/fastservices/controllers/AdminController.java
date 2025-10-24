package org.group6.fastservices.controllers;

import jakarta.validation.Valid;
import org.group6.fastservices.data.models.Role;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.dtos.requests.CreateOrgAdminRequest;
import org.group6.fastservices.dtos.requests.CreateStaffRequest;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.dtos.responses.UserResponse;
import org.group6.fastservices.services.UserService;
import org.group6.fastservices.utils.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Create Organization Admin (SUPER_ADMIN only)
     * POST /api/admin/org-admins
     */
    @PostMapping("/org-admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> createOrgAdmin(@Valid @RequestBody CreateOrgAdminRequest request) {
        try {
            // Check if email already exists
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse<>("Email already exists"));
            }

            // Create user with ORG_ADMIN role
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ORG_ADMIN);
            user.setRoles(roles);
            
            User savedUser = userService.createUser(user);
            
            UserResponse userResponse = Mapper.mapToUserResponse(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    /**
     * Create Staff member (ORG_ADMIN only)
     * POST /api/admin/staff
     * 
     * Note: Staff is associated with the ORG_ADMIN's organization
     */
    @PostMapping("/staff")
    @PreAuthorize("hasRole('ORG_ADMIN')")
    public ResponseEntity<?> createStaff(
            @Valid @RequestBody CreateStaffRequest request,
            Authentication authentication) {
        try {
            // Check if email already exists
            if (userService.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponse<>("Email already exists"));
            }

            // Get the current ORG_ADMIN's email (for audit/association)
            String creatorEmail = authentication.getName();
            
            // Create user with STAFF role
            User user = new User();
            BeanUtils.copyProperties(request, user);
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            
            Set<Role> roles = new HashSet<>();
            roles.add(Role.STAFF);
            user.setRoles(roles);
            
            User savedUser = userService.createUser(user);
            
            UserResponse userResponse = Mapper.mapToUserResponse(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    /**
     * Get all Organization Admins (SUPER_ADMIN only)
     * GET /api/admin/org-admins
     */
    @GetMapping("/org-admins")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> getAllOrgAdmins() {
        try {
            List<User> orgAdmins = userService.getAllUsersWithRole(Role.ORG_ADMIN);
            List<UserResponse> responses = orgAdmins.stream()
                    .map(Mapper::mapToUserResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    /**
     * Get all Staff members (ORG_ADMIN only)
     * GET /api/admin/staff
     */
    @GetMapping("/staff")
    @PreAuthorize("hasRole('ORG_ADMIN')")
    public ResponseEntity<?> getAllStaff(Authentication authentication) {
        try {
            List<User> staff = userService.getAllUsersWithRole(Role.STAFF);
            List<UserResponse> responses = staff.stream()
                    .map(Mapper::mapToUserResponse)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    /**
     * Get all users (SUPER_ADMIN only)
     * GET /api/admin/users
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
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
     * Delete user by ID (SUPER_ADMIN only)
     * DELETE /api/admin/users/{id}
     */
    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok().body("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}