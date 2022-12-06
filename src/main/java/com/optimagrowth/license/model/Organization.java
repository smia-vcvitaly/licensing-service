package com.optimagrowth.license.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.hateoas.RepresentationModel;

/**
 * Organization.
 *
 * @author Vitalii Chura
 */
@Data
@RedisHash("organization")
public class Organization extends RepresentationModel<Organization> {

    String id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}
