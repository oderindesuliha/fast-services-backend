package org.group6.fastservices.service;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.services.AppointmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testGetAppointmentById() {
        Appointment appointment = appointmentService.getAppointmentById("apt100");
        assertNotNull(appointment);
        assertEquals("12345", appointment.getUser().getId());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testGetAppointmentsByCustomerId() {
        List<Appointment> appointments = appointmentService.getAppointmentsByCustomerId("12345");
        assertEquals(2, appointments.size());
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    void testGetAppointmentsByOfferingId() {
        List<Appointment> appointments = appointmentService.getAppointmentsByOfferingId("off123");
        assertEquals(1, appointments.size());
    }
}