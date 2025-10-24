package org.group6.fastservices.services;

import org.group6.fastservices.data.models.Organization;

import java.util.List;

public interface OrganizationService {
    Organization createOrganization(Organization organization);
    Organization getOrganizationById(String id);
    Organization getOrganizationByCode(String code);
    List<Organization> getAllOrganizations();
    Organization updateOrganization(String id, Organization organization);
    void deleteOrganization(String id);
    boolean existsByCode(String code);
}