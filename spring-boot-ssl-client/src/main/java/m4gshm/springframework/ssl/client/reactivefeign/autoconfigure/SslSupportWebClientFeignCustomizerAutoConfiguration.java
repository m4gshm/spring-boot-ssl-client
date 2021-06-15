package m4gshm.springframework.ssl.client.reactivefeign.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import m4gshm.springframework.ssl.client.netty.handler.NettySslContextBuilderFactory;
import m4gshm.springframework.ssl.client.reactivefeign.ReactiveFeignHttpClientProperties;
import m4gshm.springframework.ssl.client.reactivefeign.SslSupportWebClientFeignCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.webclient.WebClientFeignCustomizer;

import static m4gshm.springframework.ssl.client.reactivefeign.ReactiveFeignHttpClientProperties.REACTIVEFEIGN_HTTPCLIENT_USE_CLIENT_SSL;

@Slf4j
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass({WebClient.Builder.class, WebClientFeignCustomizer.class})
@EnableConfigurationProperties(ReactiveFeignHttpClientProperties.class)
public class SslSupportWebClientFeignCustomizerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(SslSupportWebClientFeignCustomizer.class)
    @ConditionalOnBean(NettySslContextBuilderFactory.class)
    @ConditionalOnProperty(value = REACTIVEFEIGN_HTTPCLIENT_USE_CLIENT_SSL, havingValue = "true", matchIfMissing = true)
    public SslSupportWebClientFeignCustomizer sslSupportWebClientFeignCustomizer(
            NettySslContextBuilderFactory sslContextBuilderFactory
    ) {
        return new SslSupportWebClientFeignCustomizer(sslContextBuilderFactory.newSslContextBuilder());
    }
}
