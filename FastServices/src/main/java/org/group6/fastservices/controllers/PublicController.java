package org.group6.fastservices.controllers;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.dtos.responses.OfferingResponse;
import org.group6.fastservices.dtos.responses.OrganizationResponse;
import org.group6.fastservices.services.OfferingService;
import org.group6.fastservices.services.OrganizationService;
import org.group6.fastservices.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    private final OrganizationService organizationService;
    private final OfferingService offeringService;

    @Autowired
    public PublicController(OrganizationService organizationService, OfferingService offeringService) {
        this.organizationService = organizationService;
        this.offeringService = offeringService;
    }

    // Get organization by ID (for QR code scanning)
    @GetMapping("/organizations/{id}")
    public ResponseEntity<?> getOrganizationById(@PathVariable String id) {
        try {
            Organization organization = organizationService.getOrganizationById(id);
            if (organization == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse<>("Organization not found with id: " + id));
            }

            OrganizationResponse organizationResponse = Mapper.mapToOrganizationResponse(organization);
            return ResponseEntity.ok(organizationResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    // Get all offerings for an organization (for QR code scanning)
    @GetMapping("/organizations/{id}/offerings")
    public ResponseEntity<?> getOfferingsByOrganizationId(@PathVariable String id) {
        try {
            List<Offering> offerings = offeringService.getOfferingsByOrganizationId(id);
            List<OfferingResponse> serviceResponses = offerings.stream()
                    .map(Mapper::mapToOfferingResponse)
                    .collect(Collectors.toList());

            return ResponseEntity.ok(serviceResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }

    // Get a specific offering
    @GetMapping("/offerings/{id}")
    public ResponseEntity<?> getOfferingById(@PathVariable String id) {
        try {
            Offering offering = offeringService.getOfferingById(id);
            if (offering == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse<>("Offering not found with id: " + id));
            }

            OfferingResponse serviceResponse = Mapper.mapToOfferingResponse(offering);
            return ResponseEntity.ok(serviceResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}