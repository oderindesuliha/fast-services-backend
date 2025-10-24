package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Queue;

import java.util.List;

public interface QueueService {
    Queue createQueue(Queue queue);
    Queue getQueueById(String id);
    List<Queue> getQueuesByOrganizationId(String organizationId);
    List<Queue> getQueuesByOfferingId(String offeringId);
    List<Queue> getAllQueues();
    Queue updateQueue(String id, Queue queue);
    void deleteQueue(String id);
}