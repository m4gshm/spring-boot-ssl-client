package net.ssl;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class AcceptAnyHostname implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
