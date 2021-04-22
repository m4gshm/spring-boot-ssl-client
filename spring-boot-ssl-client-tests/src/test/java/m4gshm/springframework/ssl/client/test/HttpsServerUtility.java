package m4gshm.springframework.ssl.client.test;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.net.InetSocketAddress;
import java.security.KeyStore;

import static com.sun.net.httpserver.HttpsServer.create;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.Objects.requireNonNull;

@UtilityClass
public class HttpsServerUtility {

    public static final String ROOT_RESPONSE = "ok";

    @SneakyThrows
    public static HttpsServer newHttpsServer(String hostname) {
        val httpServer = create(new InetSocketAddress(hostname, 0), 0);
        httpServer.setHttpsConfigurator(new HttpsConfigurator(newSslContext()));

        httpServer.createContext("/").setHandler(exchange -> {
            val response = ROOT_RESPONSE.getBytes();
            val responseBody = exchange.getResponseBody();
            exchange.sendResponseHeaders(HTTP_OK, response.length);
            responseBody.write(response);
            responseBody.close();
        });

        httpServer.start();
        return httpServer;
    }

    @SneakyThrows
    private static SSLContext newSslContext() {
        val passphrase = "password".toCharArray();
        val ks = KeyStore.getInstance("PKCS12");
        ks.load(requireNonNull(HttpsServerUtility.class.getResourceAsStream("/keystore.p12"),"/keystore.p12"), passphrase);

        val kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, passphrase);

        val tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        val sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext;
    }
}
