package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.repositories.AppointmentRepository;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Override
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    
    @Override
    public Appointment getAppointmentById(String id) {
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        return appointment.orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + id));
    }
    
    @Override
    public List<Appointment> getAppointmentsByCustomerId(String customerId) {
        return appointmentRepository.findByUserId(customerId);
    }
    
    @Override
    public List<Appointment> getAppointmentsByOfferingId(String offeringId) {
        return appointmentRepository.findByOfferingId(offeringId);
    }
    
    @Override
    public List<Appointment> getAppointmentsByQueueId(String queueId) {
        return appointmentRepository.findByQueueId(queueId);
    }
    
    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    
    @Override
    public Appointment updateAppointment(String id, Appointment appointment) {
        if (appointmentRepository.existsById(id)) {
            appointment.setId(id);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("Appointment not found with id: " + id);
    }
    
    @Override
    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }
}