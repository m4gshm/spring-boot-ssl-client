package org.springframework.boot.web.client.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.ClientSsl;
import org.springframework.boot.web.client.ClientSslProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientSslConfig {

    @Bean
    @ConditionalOnMissingBean
    public ClientSsl clientSsl(ClientSslProperties clientSslProperties) {
        return new ClientSsl(clientSslProperties);
    }

}
