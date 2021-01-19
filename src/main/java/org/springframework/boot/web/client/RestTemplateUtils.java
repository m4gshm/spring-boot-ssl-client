package org.springframework.boot.web.client;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@UtilityClass
public class RestTemplateUtils {
    public static RestTemplate newClientSslVerificationRestTemplate(@NonNull SSLContext sslContext) {
        return new RestTemplate(new SslVerificationHttpRequestFactory(sslContext));
    }
}
