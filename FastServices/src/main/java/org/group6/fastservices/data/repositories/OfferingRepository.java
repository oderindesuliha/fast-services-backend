package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Offering;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OfferingRepository extends JpaRepository<Offering, String> {
    List<Offering> findByOrganizationId(String organizationId);
}