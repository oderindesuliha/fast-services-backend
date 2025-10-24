package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Queue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QueueRepository extends JpaRepository<Queue, String> {
    List<Queue> findByOrganizationId(String organizationId);
    List<Queue> findByAppointmentsOfferingId(String offeringId);
}