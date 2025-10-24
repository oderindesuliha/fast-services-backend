package org.group6.fastservices.controllers;

import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.dtos.requests.QueueRequest;
import org.group6.fastservices.dtos.responses.ErrorResponse;
import org.group6.fastservices.dtos.responses.QueueResponse;
import org.group6.fastservices.services.QueueService;
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
@RequestMapping("/api/queues")
public class QueueController {
    
    @Autowired
    private QueueService queueService;
    
    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> createQueue(@RequestBody QueueRequest queueRequest) {
        try {
            Queue queue = new Queue();
            BeanUtils.copyProperties(queueRequest,queue);
            Queue savedQueue = queueService.createQueue(queue);
            
            QueueResponse queueResponse = Mapper.mapToQueueResponse(savedQueue);
            return ResponseEntity.status(HttpStatus.CREATED).body(queueResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
}
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getQueueById(@PathVariable String id) {
        try {
            Queue queue = queueService.getQueueById(id);
            QueueResponse queueResponse = Mapper.mapToQueueResponse(queue);
            return ResponseEntity.ok(queueResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllQueues() {
        try {
            List<Queue> queues = queueService.getAllQueues();
            List<QueueResponse> queueResponses = queues.stream()
                    .map(Mapper::mapToQueueResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(queueResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<?> getQueuesByOrganizationId(@PathVariable String organizationId) {
        try {
            List<Queue> queues = queueService.getQueuesByOrganizationId(organizationId);
            List<QueueResponse> queueResponses = queues.stream()
                    .map(Mapper::mapToQueueResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(queueResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
       }
    }
    
    @GetMapping("/offering/{offeringId}")
    public ResponseEntity<?> getQueuesByOfferingId(@PathVariable String offeringId) {
        try {
            List<Queue> queues = queueService.getQueuesByOfferingId(offeringId);
            List<QueueResponse> queueResponses = queues.stream()
                    .map(Mapper::mapToQueueResponse)
                    .collect(Collectors.toList());
            
            return ResponseEntity.ok(queueResponses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
       }
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> updateQueue(@PathVariable String id, @RequestBody QueueRequest queueRequest) {
        try {
            Queue queue = new Queue();
            BeanUtils.copyProperties(queueRequest, queue);
            Queue updatedQueue = queueService.updateQueue(id, queue);
            
            QueueResponse queueResponse = Mapper.mapToQueueResponse(updatedQueue);
            return ResponseEntity.ok(queueResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ORG_ADMIN')")
    public ResponseEntity<?> deleteQueue(@PathVariable String id) {
        try {
            queueService.deleteQueue(id);
          return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse<>(e.getMessage()));
        }
    }
}