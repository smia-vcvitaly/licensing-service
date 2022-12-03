package com.optimagrowth.license.config;

import com.optimagrowth.license.utils.UserContextInterceptor;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.client.ClientHttpRequestInterceptor;

import java.util.Collections;
import java.util.List;

/**
 * KeycloakConfig.
 *
 * @author Vitalii Chura
 */
@Configuration
public class KeycloakConfig {

    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KeycloakRestTemplate keycloakRestTemplate() {
        KeycloakRestTemplate template = new KeycloakRestTemplate(keycloakClientRequestFactory());
        List<ClientHttpRequestInterceptor> interceptors = template.getInterceptors();
        if (interceptors == null){
            template.setInterceptors(Collections.singletonList(
                    new UserContextInterceptor()));
        } else {
            interceptors.add(new UserContextInterceptor());
            template.setInterceptors(interceptors);
        }
        return template;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public KeycloakClientRequestFactory keycloakClientRequestFactory() {
        return new KeycloakClientRequestFactory();
    }
}
