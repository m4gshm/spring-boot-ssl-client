package m4gshm.springframework.ssl.client.reactivefeign.autoconfigure;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import m4gshm.springframework.ssl.client.netty.handler.NettySslContextBuilderFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactivefeign.webclient.WebClientFeignCustomizer;

@Slf4j
@Configuration
@RequiredArgsConstructor
@ConditionalOnClass({WebClient.Builder.class, WebClientFeignCustomizer.class})
@EnableConfigurationProperties(m4gshm.springframework.ssl.client.reactivefeign.ReactiveFeignHttpClientProperties.class)
public class SslSupportWebClientFeignCustomizerConfig {

    @Bean
    public WebClientFeignCustomizer sslSupportWebClientFeignCustomizer(
            m4gshm.springframework.ssl.client.reactivefeign.ReactiveFeignHttpClientProperties properties,
            ObjectProvider<NettySslContextBuilderFactory> sslContextBuilderFactory) {

        val useClientSsl = properties.isUseClientSsl();
        val sslContextBuilder = useClientSsl ? sslContextBuilderFactory.getObject().newSslContextBuilder() : null;
        if (!useClientSsl) log.info("client ssl disabled");
        return new m4gshm.springframework.ssl.client.reactivefeign.SslSupportWebClientFeignCustomizer(sslContextBuilder);
    }
}
