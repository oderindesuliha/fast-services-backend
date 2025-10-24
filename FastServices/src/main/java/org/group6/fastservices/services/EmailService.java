package org.group6.fastservices.services;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendAppointmentConfirmation(String to, String appointmentDetails);
    void sendQueueNotification(String to, String queueDetails);
}