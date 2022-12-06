package com.optimagrowth.license.events.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * OrganizationChangeModel.
 *
 * @author Vitalii Chura
 */
@Data
@AllArgsConstructor
public class OrganizationChangeModel {

    private String type;
    private String action;
    private String organizationId;
    private String correlationId;
}
