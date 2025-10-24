package org.group6.fastservices.controllers;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.dtos.requests.OfferingRequest;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.dtos.responses.OfferingResponse;
import org.group6.fastservices.services.OfferingService;
import org.group6.fastservices.utils.Mapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/offerings")
public class OfferingController {
    
    @Autowired
    private OfferingService offeringService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> createOffering(@RequestBody OfferingRequest offeringRequest) {
        try {
            Offering offering = new Offering();
            BeanUtils.copyProperties(offeringRequest, offering);
            Offering savedOffering = offeringService.createOffering(offering);
            
            OfferingResponse serviceResponse = Mapper.mapToOfferingResponse(savedOffering);
            return ResponseEntity.status(HttpStatus.CREATED).body(serviceResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOfferingById(@PathVariable String id) {
        try {
            Offering offering = offeringService.getOfferingById(id);
            OfferingResponse serviceResponse = Mapper.mapToOfferingResponse(offering);
            return ResponseEntity.ok(serviceResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllOfferings() {
        try {
            List<Offering> offerings = offeringService.getAllOfferings();
            List<OfferingResponse> serviceResponses = offerings.stream()
                    .map(Mapper::mapToOfferingResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(serviceResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<?> getOfferingsByOrganizationId(@PathVariable String organizationId) {
        try {
            List<Offering> offerings = offeringService.getOfferingsByOrganizationId(organizationId);
            List<OfferingResponse> serviceResponses = offerings.stream()
                    .map(Mapper::mapToOfferingResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(serviceResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> updateOffering(@PathVariable String id, @RequestBody OfferingRequest offeringRequest) {
        try {
            Offering offering = new Offering();
            BeanUtils.copyProperties(offeringRequest, offering);
            Offering updatedOffering = offeringService.updateOffering(id, offering);
            
            OfferingResponse serviceResponse = Mapper.mapToOfferingResponse(updatedOffering);
            return ResponseEntity.ok(serviceResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> deleteOffering(@PathVariable String id) {
        try {
            offeringService.deleteOffering(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}