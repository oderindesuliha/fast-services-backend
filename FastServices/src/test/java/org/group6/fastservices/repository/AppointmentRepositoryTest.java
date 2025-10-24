package org.group6.fastservices.repository;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testFindByUserId() {
        List<Appointment> appointments = appointmentRepository.findByUserId("12345");
        assertEquals(2, appointments.size());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testFindByOfferingId() {
        List<Appointment> appointments = appointmentRepository.findByOfferingId("off123");
        assertEquals(1, appointments.size());
        assertEquals("apt100", appointments.get(0).getId());
    }


}