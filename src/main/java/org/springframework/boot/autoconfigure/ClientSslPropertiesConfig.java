package org.springframework.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import io.net.ssl.client.ClientSslProperties;
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
