package org.springframework.boot.web.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.ClientSslProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class ClientSslPropertiesConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("client.ssl")
    ClientSslProperties clientSslProperties() {
        return new ClientSslProperties();
    }
}
