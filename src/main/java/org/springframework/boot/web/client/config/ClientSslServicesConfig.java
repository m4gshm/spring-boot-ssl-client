package org.springframework.boot.web.client.config;

import io.netty.handler.ssl.SslContextBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.client.ClientSsl;
import org.springframework.boot.web.client.NettySslContextBuilderFactory;
import org.springframework.boot.web.client.RestTemplateFactory;
import org.springframework.boot.web.client.SslContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@ConditionalOnBean(ClientSsl.class)
public class ClientSslServicesConfig {

    final ClientSsl clientSsl;

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(SslContextBuilder.class)
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
    @ConditionalOnClass(RestTemplate.class)
    public RestTemplateFactory restTemplateFactory(SslContextHolder sslContextHolder) {
        return new RestTemplateFactory(sslContextHolder);
    }

}
