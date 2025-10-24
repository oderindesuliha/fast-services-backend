package org.group6.fastservices.dtos.responses;

import lombok.Data;
import org.group6.fastservices.data.models.AppointmentStatus;

import java.time.LocalDateTime;

@Data
public class AppointmentResponse {
    private String id;
    private String userId;
    private String offeringId;
    private String queueId;
    private LocalDateTime appointmentDate;
    private AppointmentStatus status;
    private int queuePosition;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}