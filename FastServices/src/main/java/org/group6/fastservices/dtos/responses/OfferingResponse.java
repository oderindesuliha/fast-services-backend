package org.group6.fastservices.dtos.responses;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OfferingResponse {
    private String id;
    private String name;
    private String description;
    private int estimatedWaitTime;
    private int duration;
    private String organizationId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<AppointmentResponse> appointments;
}