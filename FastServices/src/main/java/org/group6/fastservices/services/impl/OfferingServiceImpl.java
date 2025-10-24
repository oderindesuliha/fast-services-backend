package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Offering;
import org.group6.fastservices.data.repositories.OfferingRepository;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.OfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferingServiceImpl implements OfferingService {
    
    @Autowired
    private OfferingRepository offeringRepository;
    
    @Override
    public Offering createOffering(Offering offering) {
        
        return offeringRepository.save(offering);
    }
    
    @Override
    public Offering getOfferingById(String id) {
        Optional<Offering> offering = offeringRepository.findById(id);
        return offering.orElseThrow(() -> new ResourceNotFoundException("Offering not found with id: " + id));
    }
    
    @Override
    public List<Offering> getOfferingsByOrganizationId(String organizationId) {
        return offeringRepository.findByOrganizationId(organizationId);
    }
    
    @Override
    public List<Offering> getAllOfferings() {
        
        return offeringRepository.findAll();
    }
    
    @Override
    public Offering updateOffering(String id, Offering offering) {
        if (offeringRepository.existsById(id)) {
            offering.setId(id);
            return offeringRepository.save(offering);
        }
        throw new ResourceNotFoundException("Offering not found with id: " + id);
    }
    
    @Override
    public void deleteOffering(String id) {
        offeringRepository.deleteById(id);
    }
}