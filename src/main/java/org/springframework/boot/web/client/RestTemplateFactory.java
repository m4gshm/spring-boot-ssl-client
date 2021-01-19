package org.springframework.boot.web.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

@RequiredArgsConstructor
public class RestTemplateFactory {
    final SslContextHolder sslContextHolder;

    public static RestTemplate newClientSslVerificationRestTemplate(@NonNull SSLContext sslContext) {
        return new RestTemplate(new SslVerificationHttpRequestFactory(sslContext));
    }

    public RestTemplate newRestTemplate() {
        return newClientSslVerificationRestTemplate(sslContextHolder.getSslContext());
    }
}
