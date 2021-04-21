package m4gshm.springframework.ssl.client;

import lombok.Data;

@Data
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
