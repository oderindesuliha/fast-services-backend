package org.group6.fastservices.dtos.requests;

import lombok.Data;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Data
public class AppointmentRequest {
    private String userId;
    private String offeringId;
    private String queueId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
}