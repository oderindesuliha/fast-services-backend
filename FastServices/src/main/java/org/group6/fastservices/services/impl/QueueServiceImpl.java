package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Queue;
import org.group6.fastservices.data.repositories.QueueRepository;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {
    
    @Autowired
    private QueueRepository queueRepository;
    
    @Override
    public Queue createQueue(Queue queue) {
        return queueRepository.save(queue);
    }
    
    @Override
    public Queue getQueueById(String id) {
        Optional<Queue> queue = queueRepository.findById(id);
        return queue.orElseThrow(() -> new ResourceNotFoundException("Queue not found with id: " + id));
    }
    
    @Override
    public List<Queue> getQueuesByOrganizationId(String organizationId) {
        return queueRepository.findByOrganizationId(organizationId);
    }
    
    @Override
    public List<Queue> getQueuesByOfferingId(String offeringId) {
        return queueRepository.findByAppointmentsOfferingId(offeringId);
    }
    
    @Override
    public List<Queue> getAllQueues() {
        return queueRepository.findAll();
    }
    
    @Override
    public Queue updateQueue(String id, Queue queue) {
        if (queueRepository.existsById(id)) {
            queue.setId(id);
            return queueRepository.save(queue);
        }
        throw new ResourceNotFoundException("Queue not found with id: " + id);
    }
    
    @Override
    public void deleteQueue(String id) {
        queueRepository.deleteById(id);
    }
}