package com.optimagrowth.license.model;

import lombok.Data;

/**
 * License.
 *
 * @author Vitalii Chura
 */
@Data
public class License {

    private int id;
    private String licenseId;
    private String description;
    private String organizationId;
    private String productName;
    private String licenseType;
}
