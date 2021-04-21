package m4gshm.springframework.ssl.client.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

@UtilityClass
public class SslContextUtils {
    @SneakyThrows
    public static SSLContext newSslContext(String protocol, KeyManager[] keyManagers, TrustManager[] trustManagers) {
        val sslContext = SSLContext.getInstance(protocol);
        sslContext.init(keyManagers, trustManagers, null);
        return sslContext;
    }

    public static SSLContext newSslContext(KeyManager[] keyManagers, TrustManager[] trustManagers) {
        return newSslContext("TLS", keyManagers, trustManagers);
    }
}
