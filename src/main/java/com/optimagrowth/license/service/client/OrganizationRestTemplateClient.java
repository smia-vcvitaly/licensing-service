package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.OrganizationRedisRepository;
import com.optimagrowth.license.utils.UserContext;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * OrganizationRestTemplateClient.
 *
 * @author Vitalii Chura
 */
@Component
public class OrganizationRestTemplateClient {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);

    @Autowired
    private KeycloakRestTemplate restTemplate;

    @Autowired
    private OrganizationRedisRepository redisRepository;

    private Organization checkRedisCache(String organizationId) {
        try {
            return redisRepository
                    .findById(organizationId)
                    .orElse(null);
        } catch (Exception ex){
            LOG.error("Error encountered while trying to retrieve organization {} check Redis Cache.",
            organizationId, ex);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization organization) {
        try {
            redisRepository.save(organization);
        } catch (Exception ex){
            LOG.error("Unable to cache organization {} in Redis.",
            organization.getId(), ex);
        }
    }

    public Organization getOrganization(String organizationId) {
        LOG.debug("In Licensing Service.getOrganization: {}", UserContext.getCorrelationId());

        Organization organization = checkRedisCache(organizationId);
        if (organization != null) {
            LOG.debug("I have successfully retrieved an organization {} from the redis cache: {}", organizationId,
            organization);
            return organization;
        }

        LOG.debug("Unable to locate organization from the redis cache: {}.", organizationId);
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://localhost:8072/organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET,
                        null, Organization.class, organizationId);

        organization = restExchange.getBody();
        if (organization != null) {
            cacheOrganizationObject(organization);
        }
        return organization;
    }
}
