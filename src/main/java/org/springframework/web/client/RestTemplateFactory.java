package org.springframework.web.client;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import io.net.ssl.SslContextHolder;
import org.springframework.http.client.SslVerificationHttpRequestFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;

@RequiredArgsConstructor
public class RestTemplateFactory {
    final SslContextHolder sslContextHolder;
    final HostnameVerifier hostnameVerifier;

    public static RestTemplate newClientSslVerificationRestTemplate(@NonNull SSLContext sslContext,
                                                                    HostnameVerifier hostnameVerifier) {
        return new RestTemplate(new SslVerificationHttpRequestFactory(sslContext, hostnameVerifier));
    }

    public RestTemplate newRestTemplate() {
        return newClientSslVerificationRestTemplate(sslContextHolder.getSslContext(), hostnameVerifier);
    }
}
