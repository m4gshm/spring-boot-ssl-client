package org.springframework.boot.web.client.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.ClientSsl;
import org.springframework.boot.web.client.NettySslContextBuilderFactory;
import org.springframework.boot.web.client.RestTemplateFactory;
import org.springframework.boot.web.client.SslContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@ConditionalOnBean(ClientSsl.class)
public class ClientSslServicesConfig {

    final ClientSsl clientSsl;

    @Bean
    @ConditionalOnMissingBean
    public NettySslContextBuilderFactory nettySslContextBuilderFactory() {
        return new NettySslContextBuilderFactory(clientSsl.getKeyManagerFactory(), clientSsl.getTrustManagerFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public SslContextHolder sslContextHolder() {
        return new SslContextHolder(clientSsl.getKeyManagerFactory(), clientSsl.getTrustManagerFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplateFactory restTemplateFactory(SslContextHolder sslContextHolder) {
        return new RestTemplateFactory(sslContextHolder, clientSsl.getHostnameVerifier());
    }

}
