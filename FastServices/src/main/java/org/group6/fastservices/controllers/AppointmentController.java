package org.group6.fastservices.controllers;

import org.group6.fastservices.data.models.Appointment;
import org.group6.fastservices.data.models.User;
import org.group6.fastservices.dtos.requests.AppointmentRequest;
import org.group6.fastservices.dtos.responses.AppointmentResponse;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.services.AppointmentService;
import org.group6.fastservices.services.EmailService;
import org.group6.fastservices.services.OfferingService;
import org.group6.fastservices.services.UserService;
import org.group6.fastservices.utils.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private UserService userService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private OfferingService offeringService;
    
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentRequest appointmentRequest) {
        try {
            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(appointmentRequest, appointment);
            Appointment savedAppointment = appointmentService.createAppointment(appointment);
            
            // Send email notification
            User user = userService.getUserById(appointmentRequest.getUserId());
            if (user != null) {
                String appointmentDetails = String.format(
                    "Offering ID: %s\nDate: %s\nStatus: %s",
                    appointmentRequest.getOfferingId(),
                    appointmentRequest.getAppointmentDate(),
                    appointmentRequest.getStatus()
                );
                emailService.sendAppointmentConfirmation(user.getEmail(), appointmentDetails);
            }
            
            AppointmentResponse appointmentResponse = Mapper.mapToAppointmentResponse(savedAppointment);
            return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable String id) {
        try {
            Appointment appointment = appointmentService.getAppointmentById(id);
            AppointmentResponse appointmentResponse = Mapper.mapToAppointmentResponse(appointment);
            return ResponseEntity.ok(appointmentResponse);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentService.getAllAppointments();
            List<AppointmentResponse> appointmentResponses = appointments.stream()
                    .map(Mapper::mapToAppointmentResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(appointmentResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<?> getAppointmentsByCustomerId(@PathVariable String customerId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByCustomerId(customerId);
            List<AppointmentResponse> appointmentResponses = appointments.stream()
                    .map(Mapper::mapToAppointmentResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(appointmentResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/offering/{offeringId}")
    public ResponseEntity<?> getAppointmentsByOfferingId(@PathVariable String offeringId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByOfferingId(offeringId);
            List<AppointmentResponse> appointmentResponses = appointments.stream()
                    .map(Mapper::mapToAppointmentResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(appointmentResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/queue/{queueId}")
    public ResponseEntity<?> getAppointmentsByQueueId(@PathVariable String queueId) {
        try {
            List<Appointment> appointments = appointmentService.getAppointmentsByQueueId(queueId);
            List<AppointmentResponse> appointmentResponses = appointments.stream()
                    .map(Mapper::mapToAppointmentResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(appointmentResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable String id, @RequestBody AppointmentRequest appointmentRequest) {
        try {
            Appointment appointment = new Appointment();
            BeanUtils.copyProperties(appointmentRequest, appointment);
            Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
            
            // Send email notification for update
            User user = userService.getUserById(appointmentRequest.getUserId());
            if (user != null) {
                String appointmentDetails = String.format(
                    "AppointmentID: %s\nOffering ID: %s\nDate: %s\nStatus: %s",
                    id,
                    appointmentRequest.getOfferingId(),
                    appointmentRequest.getAppointmentDate(),
                    appointmentRequest.getStatus()
                );
                emailService.sendAppointmentConfirmation(user.getEmail(), appointmentDetails);
            }
            
            AppointmentResponse appointmentResponse = Mapper.mapToAppointmentResponse(updatedAppointment);
            return ResponseEntity.ok(appointmentResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable String id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}