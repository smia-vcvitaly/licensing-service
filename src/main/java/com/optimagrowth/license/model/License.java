package com.optimagrowth.license.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * License.
 *
 * @author Vitalii Chura
 */
@Data
public class License extends RepresentationModel<License> {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
