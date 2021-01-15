package org.springframework.boot.web.client;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ClientSslProperties.class)
public class ClientSslConfig {

    @Bean
    public ClientSsl clientSsl(ClientSslProperties clientSslProperties) {
        return new ClientSsl(clientSslProperties);
    }
}
