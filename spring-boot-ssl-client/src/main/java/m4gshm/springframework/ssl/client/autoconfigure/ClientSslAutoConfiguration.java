package m4gshm.springframework.ssl.client.autoconfigure;

import m4gshm.springframework.ssl.client.ClientSsl;
import m4gshm.springframework.ssl.client.ClientSslProperties;
import m4gshm.springframework.ssl.client.autoconfigure.options.ClientSslPropertiesAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(ClientSslPropertiesAutoConfiguration.class)
public class ClientSslAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ClientSsl clientSsl(ClientSslProperties clientSslProperties) {
        return new ClientSsl(clientSslProperties);
    }

}
