package org.springframework.boot.web.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("client.ssl")
public class ClientSslProperties {

    private String keyStore;
    private String keyStorePassword;
    private String keyStoreType;
    private String keyStoreProvider;

    private boolean disableSslValidation = false;
    private String trustStore;
    private String trustStorePassword;
    private String trustStoreType;
    private String trustStoreProvider;

}
