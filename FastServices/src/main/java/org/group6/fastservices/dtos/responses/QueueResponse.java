package org.group6.fastservices.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QueueResponse {
    private String id;
    private String name;
    private String description;
    private String organizationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AppointmentResponse> appointments;
}