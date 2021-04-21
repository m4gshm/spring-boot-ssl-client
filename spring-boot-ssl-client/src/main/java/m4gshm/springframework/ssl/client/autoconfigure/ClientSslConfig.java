package m4gshm.springframework.ssl.client.autoconfigure;

import m4gshm.springframework.ssl.client.ClientSsl;
import m4gshm.springframework.ssl.client.ClientSslProperties;
import m4gshm.springframework.ssl.client.autoconfigure.options.ClientSslPropertiesConfig;
import m4gshm.springframework.ssl.client.autoconfigure.services.ClientSslServicesConfig;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(ClientSslPropertiesConfig.class)
public class ClientSslConfig {

    @Bean
    @ConditionalOnMissingBean
    public ClientSsl clientSsl(ClientSslProperties clientSslProperties) {
        return new ClientSsl(clientSslProperties);
    }

}
