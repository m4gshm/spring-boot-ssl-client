package org.springframework.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import io.net.ssl.client.ClientSsl;
import io.net.ssl.client.ClientSslProperties;
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
