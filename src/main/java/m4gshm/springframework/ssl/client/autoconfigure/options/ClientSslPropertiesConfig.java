package m4gshm.springframework.ssl.client.autoconfigure.options;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import m4gshm.springframework.ssl.client.ClientSslProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties
public class ClientSslPropertiesConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties("client.ssl")
    public ClientSslProperties clientSslProperties() {
        return new ClientSslProperties();
    }
}
