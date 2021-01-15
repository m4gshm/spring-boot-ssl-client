package org.springframework.boot.web.client;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.NoValidationTrustManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.security.KeyStore;

@Slf4j
public class ClientSsl {
    @Getter
    final KeyManagerFactory keyManagerFactory;
    @Getter
    final TrustManagerFactory trustManagerFactory;

    public ClientSsl(ClientSslProperties properties) {
        keyManagerFactory = newKeyManagerFactory(properties.getKeyStore(), properties.getKeyStorePassword(),
                properties.getKeyStoreType(), properties.getKeyStoreProvider());

        val insecure = properties.isDisableSslValidation();
        trustManagerFactory = insecure ? NoValidationTrustManagerFactory.INSTANCE
                : newTrustManagerFactory(properties.getTrustStore(), properties.getTrustStorePassword(),
                properties.getKeyStoreType(), properties.getTrustStoreProvider());
    }

    @SneakyThrows
    public static TrustManagerFactory newTrustManagerFactory(String path, String password, String type, String provider) {
        if (type == null) type = "jks";
        val trustStore = provider != null ? KeyStore.getInstance(type, provider) : KeyStore.getInstance(type);

        if (path != null) try (val fis = new FileInputStream(path)) {
            log.trace("load truststore {}", path);
            trustStore.load(fis, password != null ? password.toCharArray() : null);
        }
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);
        return trustManagerFactory;
    }

    @SneakyThrows
    public static KeyManagerFactory newKeyManagerFactory(String path, String password, String type, String provider) {
        if (type == null) type = "pkcs12";

        val keyStore = provider != null ? KeyStore.getInstance(type, provider) : KeyStore.getInstance(type);

        val passwordChars = password != null ? password.toCharArray() : null;
        if (path != null) try (val fis = new FileInputStream(path)) {
            log.trace("load keystore {}", path);
            keyStore.load(fis, passwordChars);
        }
        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, passwordChars);
        return keyManagerFactory;
    }

}
