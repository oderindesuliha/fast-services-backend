package org.group6.fastservices.data.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("USER")

public class User implements UserDetails {
  @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

   @NotBlank
   @Column(name = "first_name")
   private String firstName;

   @NotBlank
   @Column(name = "last_name")
   private String lastName;

   @Email
   @NotBlank
   @Column(unique = true)
   private String email;

   @NotBlank
   private String password;

   @NotBlank
   private String phone;

   @NotBlank
   private String roles; // Store roles as comma-separated values

   @CreationTimestamp
   @Column(name = "created_at")
   private LocalDateTime createdAt;
   
   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
   
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
       if (roles == null || roles.isEmpty()) {
           return new ArrayList<>();
       }
       
       return Arrays.stream(roles.split(","))
               .map(String::trim)
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
               .collect(Collectors.toList());
   }

   @Override
   public String getUsername() {
       return email;
   }

   @Override
   public boolean isAccountNonExpired() {
       return true;
   }

   @Override
public boolean isAccountNonLocked() {
       return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
       return true;
   }

   @Override
   public boolean isEnabled() {
       return true;
   }
   
   // Helper method to set roles
   public void setRoles(Set<Role> roleSet) {
       StringBuilder rolesBuilder = new StringBuilder();
       boolean first = true;
       for (Role role : roleSet) {
           if (!first) {
               rolesBuilder.append(",");
           }
           rolesBuilder.append(role.name());
           first = false;
       }
       this.roles = rolesBuilder.toString();
    }
   
   // Helper method to set roles directly as string
   public void setRoles(String roles) {
       this.roles = roles;
   }
   
   // Helper method to get roles as Set
   public Set<Role> getRolesAsSet() {
       if (roles == null || roles.isEmpty()) {
           return new HashSet<>();
       }
       
       Set<Role> roleSet = new HashSet<>();
       String[] roleArray = roles.split(",");
       for (String role : roleArray) {
           try {
               roleSet.add(Role.valueOf(role.trim()));
           } catch (IllegalArgumentException e) {
               // Ignore invalid roles
           }
       }
       return roleSet;
   }
}