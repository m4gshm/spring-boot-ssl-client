package org.springframework.boot.autoconfigure;

import io.netty.handler.ssl.NettySslContextBuilderFactory;
import reactivefeign.webclient.SslSupportWebClientFeignCustomizer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.webclient.WebClientFeignCustomizer;
import org.springframework.boot.ReactiveFeignHttpClientProperties;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass({WebClient.Builder.class, WebClientFeignCustomizer.class})
@EnableConfigurationProperties(ReactiveFeignHttpClientProperties.class)
public class SslSupportWebClientFeignCustomizerConfig {

    @Bean
    public WebClientFeignCustomizer sslSupportWebClientFeignCustomizer(
            ReactiveFeignHttpClientProperties properties, ObjectProvider<NettySslContextBuilderFactory> sslContextBuilderFactory) {
        val useClientSsl = properties.isUseClientSsl();
        val sslContextBuilder = useClientSsl ? sslContextBuilderFactory.getObject().newSslContextBuilder() : null;
        if (!useClientSsl) log.info("client ssl disabled");
        return  new SslSupportWebClientFeignCustomizer(sslContextBuilder);
    }
}
