package org.group6.fastservices.dtos.requests;

import lombok.Data;

@Data
public class QueueRequest {
    private String name;
    private String description;
    private String organizationId;
}