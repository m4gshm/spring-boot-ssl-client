package org.springframework.boot.web.client;

import io.netty.handler.ssl.SslContextBuilder;
import lombok.RequiredArgsConstructor;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;

import static io.netty.handler.ssl.SslContextBuilder.forClient;

@RequiredArgsConstructor
public class NettySslContextBuilderFactory {
    private final KeyManagerFactory keyManagerFactory;
    private final TrustManagerFactory trustManagerFactory;

    public SslContextBuilder newSslContextBuilder() {
        return forClient().trustManager(trustManagerFactory).keyManager(keyManagerFactory);
    }
}
