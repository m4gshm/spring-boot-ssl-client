package m4gshm.springframework.ssl.client.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

@Slf4j
public class NoValidationX509TrustManager implements X509TrustManager {
    @Getter
    private final X509Certificate[] acceptedIssuers = new X509Certificate[0];

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        log.trace("Accepting a client certificate {}", chain[0].getSubjectDN());
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        log.trace("Accepting a server certificate {}", chain[0].getSubjectDN());
    }

}
