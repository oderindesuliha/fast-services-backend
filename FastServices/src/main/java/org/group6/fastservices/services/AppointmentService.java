package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment createAppointment(Appointment appointment);
    Appointment getAppointmentById(String id);
    List<Appointment> getAppointmentsByCustomerId(String customerId);
    List<Appointment> getAppointmentsByOfferingId(String offeringId);
    List<Appointment> getAppointmentsByQueueId(String queueId);
    List<Appointment> getAllAppointments();
    Appointment updateAppointment(String id, Appointment appointment);
    void deleteAppointment(String id);
}