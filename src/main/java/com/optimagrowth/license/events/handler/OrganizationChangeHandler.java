package com.optimagrowth.license.events.handler;

import com.optimagrowth.license.events.CustomChannels;
import com.optimagrowth.license.events.model.OrganizationChangeModel;
import com.optimagrowth.license.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * OrganizationChangeHandler.
 *
 * @author Vitalii Chura
 */
@EnableBinding(CustomChannels.class)
public class OrganizationChangeHandler {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationChangeHandler.class);

    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;

    @StreamListener("inboundOrgChanges")
    public void loggerSink(OrganizationChangeModel organization) {
        LOG.debug("Received a message of type {}", organization.getType());
        LOG.debug("Received a message with an event {} from the organization service for the organization id {} ",
        organization.getAction(), organization.getOrganizationId());
        if (organization.getAction().equals("DELETE")) {
            organizationRedisRepository.deleteById(organization.getOrganizationId());
        }
    }
}
