package m4gshm.springframework.ssl.client.http.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.net.HttpURLConnection;

@Slf4j
@RequiredArgsConstructor
public class SslVerificationHttpRequestFactory extends SimpleClientHttpRequestFactory {

    final protected SSLContext sslContext;
    final protected HostnameVerifier hostnameVerifier;

    @Override
    protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
        if (connection instanceof HttpsURLConnection) prepareHttpsConnection((HttpsURLConnection) connection);
        super.prepareConnection(connection, httpMethod);
    }

    protected void prepareHttpsConnection(HttpsURLConnection connection) {
        connection.setSSLSocketFactory(sslContext.getSocketFactory());
        if (hostnameVerifier != null) connection.setHostnameVerifier(hostnameVerifier);
    }

}
