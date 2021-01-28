package m4gshm.springframework.ssl.client.config;

import m4gshm.springframework.ssl.client.ClientSslProperties;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientSslPropertiesTestConfig {

    @Bean
    public ClientSslProperties clientSslProperties() {
        val clientSslProperties = new ClientSslProperties();
        clientSslProperties.setTrustStorePassword("password");
        clientSslProperties.setTrustStore("classpath:keystore.p12");
        clientSslProperties.setTrustStoreType("PKCS12");
        return clientSslProperties;
    }
}
