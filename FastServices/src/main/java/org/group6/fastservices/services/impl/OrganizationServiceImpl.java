package org.group6.fastservices.services.impl;

import org.group6.fastservices.data.models.Organization;
import org.group6.fastservices.data.repositories.OrganizationRepository;
import org.group6.fastservices.exceptions.ResourceNotFoundException;
import org.group6.fastservices.services.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    @Autowired
    private OrganizationRepository organizationRepository;
    
    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
    
    @Override
    public Organization getOrganizationById(String id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
    }
    
    @Override
    public Organization getOrganizationByCode(String code) {
        Optional<Organization> organization = organizationRepository.findByCode(code);
        return organization.orElseThrow(() -> new ResourceNotFoundException("Organization not found with code: " + code));
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }
    
    @Override
    public Organization updateOrganization(String id, Organization organization) {
        if (organizationRepository.existsById(id)) {
            organization.setId(id);
            return organizationRepository.save(organization);
        }
        throw new ResourceNotFoundException("Organization not found with id: " + id);
    }
    
    @Override
    public void deleteOrganization(String id) {
        organizationRepository.deleteById(id);
    }
    
    @Override
    public boolean existsByCode(String code) {
        return organizationRepository.existsByCode(code);
    }
}