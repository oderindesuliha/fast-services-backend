package org.group6.fastservices.data.repositories;

import org.group6.fastservices.data.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findByUserId(String userId);
    List<Appointment> findByOfferingId(String offeringId);
    List<Appointment> findByQueueId(String queueId);
}