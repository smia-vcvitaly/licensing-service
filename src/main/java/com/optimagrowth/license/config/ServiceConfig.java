package com.optimagrowth.license.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * ServiceConfig.
 *
 * @author Vitalii Chura
 */
@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "example")
public class ServiceConfig {

    private String property;
}
