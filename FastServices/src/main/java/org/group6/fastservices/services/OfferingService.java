package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Offering;

import java.util.List;

public interface OfferingService {
    Offering createOffering(Offering offering);
    Offering getOfferingById(String id);
    List<Offering> getOfferingsByOrganizationId(String organizationId);
    List<Offering> getAllOfferings();
    Offering updateOffering(String id, Offering offering);
    void deleteOffering(String id);
}