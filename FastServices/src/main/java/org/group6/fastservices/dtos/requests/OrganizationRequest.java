package org.group6.fastservices.dtos.requests;

import lombok.Data;

@Data
public class OrganizationRequest {
    private String name;
    private String password;
    private String address;
    private String contactEmail;
    private String contactPhone;
}