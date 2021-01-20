package io.net.ssl;

import lombok.RequiredArgsConstructor;
import io.net.ssl.SslContextUtils;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

@RequiredArgsConstructor
public class SslContextHolder {
    private final KeyManagerFactory keyManagerFactory;
    private final TrustManagerFactory trustManagerFactory;

    private volatile SSLContext sslContext;

    public SSLContext getSslContext() {
        if (sslContext == null) synchronized (this) {
            if (sslContext == null) sslContext = newSslContext();
        }
        return sslContext;
    }

    public SSLContext newSslContext() {
        return SslContextUtils.newSslContext(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers());
    }

}
