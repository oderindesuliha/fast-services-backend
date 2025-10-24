package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OrganizationRepository extends JpaRepository<Organization, String> {
    Optional<Organization> findByCode(String code);
    boolean existsByCode(String code);
}