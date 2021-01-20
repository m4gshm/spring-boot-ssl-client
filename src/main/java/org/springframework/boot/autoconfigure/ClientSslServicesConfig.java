package org.springframework.boot.autoconfigure;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import io.net.ssl.client.ClientSsl;
import io.netty.handler.ssl.NettySslContextBuilderFactory;
import org.springframework.web.client.RestTemplateFactory;
import io.net.ssl.SslContextHolder;
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
