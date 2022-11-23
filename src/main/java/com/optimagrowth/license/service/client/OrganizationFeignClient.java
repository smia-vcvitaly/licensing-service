package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * OrganizationFeignClient.
 *
 * @author Vitalii Chura
 */
@FeignClient("organization-service")
public interface OrganizationFeignClient {

    @GetMapping(value="/v1/organization/{organizationId}", consumes="application/json")
    Organization getOrganization(@PathVariable("organizationId") String organizationId);
}
