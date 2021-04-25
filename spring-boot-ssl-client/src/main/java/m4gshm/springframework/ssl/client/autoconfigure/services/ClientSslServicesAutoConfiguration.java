package m4gshm.springframework.ssl.client.autoconfigure.services;

import io.netty.handler.ssl.SslContextBuilder;
import lombok.RequiredArgsConstructor;
import m4gshm.springframework.ssl.client.autoconfigure.ClientSslAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import m4gshm.springframework.ssl.client.ClientSsl;
import m4gshm.springframework.ssl.client.netty.handler.NettySslContextBuilderFactory;
import m4gshm.springframework.ssl.client.web.client.RestTemplateFactory;
import m4gshm.springframework.ssl.client.utils.SslContextHolder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
@AutoConfigureAfter(ClientSslAutoConfiguration.class)
public class ClientSslServicesAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ClientSsl.class)
    @ConditionalOnClass(SslContextBuilder.class)
    public NettySslContextBuilderFactory nettySslContextBuilderFactory(ClientSsl clientSsl) {
        return new NettySslContextBuilderFactory(clientSsl.getKeyManagerFactory(), clientSsl.getTrustManagerFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ClientSsl.class)
    public SslContextHolder sslContextHolder(ClientSsl clientSsl) {
        return new SslContextHolder(clientSsl.getKeyManagerFactory(), clientSsl.getTrustManagerFactory());
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ClientSsl.class)
    public RestTemplateFactory restTemplateFactory(ClientSsl clientSsl, SslContextHolder sslContextHolder) {
        return new RestTemplateFactory(sslContextHolder, clientSsl.getHostnameVerifier());
    }

}
