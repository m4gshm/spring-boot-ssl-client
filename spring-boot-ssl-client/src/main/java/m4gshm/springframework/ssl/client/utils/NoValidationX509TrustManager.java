package m4gshm.springframework.ssl.client.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.Socket;
import java.security.Principal;
import java.security.cert.X509Certificate;

/**
 * X509ExtendedTrustManager inheritance is needed to disable validation of hostname or IP by matching with certificate subject
 */
@Slf4j
public class NoValidationX509TrustManager extends X509ExtendedTrustManager implements X509TrustManager {
    @Getter
    private final X509Certificate[] acceptedIssuers = new X509Certificate[0];

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) {
        log.trace("Accepting a client certificate {}", getSubjectDN(chain));
    }

    private Principal getSubjectDN(X509Certificate[] chain) {
        return chain != null && chain.length > 0 ? chain[0].getSubjectDN() : null;
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) {
        log.trace("Accepting a server certificate {}", getSubjectDN(chain));
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) {
        log.trace("Accepting a client certificate {}", getSubjectDN(chain));
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) {
        log.trace("Accepting a server certificate {}", getSubjectDN(chain));
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
        log.trace("Accepting a client certificate {}", getSubjectDN(chain));
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) {
        log.trace("Accepting a server certificate {}", getSubjectDN(chain));
    }
}
