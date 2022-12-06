package com.optimagrowth.license.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ServiceConfig.
 *
 * @author Vitalii Chura
 */
@Data
@Component
public class ServiceConfig {

    @Value("${example.property}")
    private String property;

    @Value("${redis.server}")
    private String redisServer;

    @Value("${redis.port}")
    private String redisPort;
}
