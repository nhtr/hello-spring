package com.nhtr.authorizationservice;

import org.keycloak.common.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EnableConfigurationProperties({KeycloakProperties.class, KeycloakCustomProperties.class})
@ServletComponentScan
public class AuthorizationServiceApplication {

    private static final Logger LOG = LoggerFactory.getLogger(AuthorizationServiceApplication.class);

    private final ServerProperties serverProperties;

    private final KeycloakCustomProperties customProperties;

    public AuthorizationServiceApplication(ServerProperties serverProperties, KeycloakCustomProperties customProperties) {
        this.serverProperties = serverProperties;
        this.customProperties = customProperties;
    }

    public static void main(String[] args) {

        LOG.info("Using Keycloak Version: {}", Version.VERSION_KEYCLOAK);
        // LOG.info("Enabled Keycloak Features (Deprecated): {}", Profile.getDeprecatedFeatures());
        // LOG.info("Enabled Keycloak Features (Preview): {}", Profile.getPreviewFeatures());
        // LOG.info("Enabled Keycloak Features (Experimental): {}", Profile.getExperimentalFeatures());
        // LOG.info("Enabled Keycloak Features (Disabled): {}", Profile.getDisabledFeatures());

        SpringApplication.run(AuthorizationServiceApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onApplicationReadyEventListener() {

        return (evt) -> {

            Integer port = serverProperties.getPort();
            String keycloakContextPath = customProperties.getServer().getContextPath();

            LOG.info("Embedded Keycloak started: Browse to <http://localhost:{}{}> to use keycloak", port, keycloakContextPath);
        };
    }

}
