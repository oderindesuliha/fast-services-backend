package org.group6.fastservices.dtos.requests;

import lombok.Data;

@Data
public class OfferingRequest {
    private String name;
    private String description;
    private int estimatedWaitTime;
    private int duration;
    private String organizationId;
}