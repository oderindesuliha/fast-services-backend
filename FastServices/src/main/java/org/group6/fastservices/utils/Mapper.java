package org.group6.fastservices.utils;

import org.group6.fastservices.data.models.*;
import org.group6.fastservices.dtos.responses.*;
import org.springframework.beans.BeanUtils;

public class Mapper {
    
    public static UserResponse mapToUserResponse(User user) {
        if (user == null) return null;
        
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(user, userResponse);
        return userResponse;
    }
    
    public static OrganizationResponse mapToOrganizationResponse(Organization organization) {
        if (organization == null) return null;
        
        OrganizationResponse organizationResponse = new OrganizationResponse();
        BeanUtils.copyProperties(organization, organizationResponse);
        // Avoid circular reference by not mapping services and queues which contain references back to organization
        return organizationResponse;
    }
    
    public static OfferingResponse mapToOfferingResponse(Offering offering) {
        if (offering == null) return null;
        
        OfferingResponse offeringResponse = new OfferingResponse();
        BeanUtils.copyProperties(offering, offeringResponse);
        if (offering.getOrganization() != null) {
            offeringResponse.setOrganizationId(offering.getOrganization().getId());
        }
        // Avoid circular reference by not mapping appointments which contain references back to offering
        return offeringResponse;
    }
    
    public static QueueResponse mapToQueueResponse(Queue queue) {
        if (queue == null) return null;
        
        QueueResponse queueResponse = new QueueResponse();
        BeanUtils.copyProperties(queue, queueResponse);
        if (queue.getOrganization() != null) {
            queueResponse.setOrganizationId(queue.getOrganization().getId());
        }
        // Avoid circular reference by not mapping appointments which contain references back to queue
        return queueResponse;
    }
    
    public static AppointmentResponse mapToAppointmentResponse(Appointment appointment) {
        if (appointment == null) return null;
        
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        BeanUtils.copyProperties(appointment, appointmentResponse);
        if (appointment.getUser() != null) {
            appointmentResponse.setUserId(appointment.getUser().getId());
        }
        if (appointment.getOffering() != null) {
            appointmentResponse.setOfferingId(appointment.getOffering().getId());
        }
        if (appointment.getQueue() != null) {
            appointmentResponse.setQueueId(appointment.getQueue().getId());
        }
        return appointmentResponse;
    }
}