package m4gshm.springframework.ssl.client;

import com.sun.net.httpserver.HttpsServer;
import lombok.val;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static m4gshm.springframework.ssl.client.HttpsServerUtility.newHttpsServer;

public class BaseHttpsTest {
    public static volatile HttpsServer httpsServer;

    public static String getServerUrl() {
        val address = httpsServer.getAddress();
        val hostName = address.getHostName();
        val port = address.getPort();
        return "https://" + hostName + ":" + port;
    }

    @BeforeClass
    public static void startHttpsServer() {
        httpsServer = newHttpsServer();
    }

    @AfterClass
    public static void stopHttpsServer() {
        if (httpsServer != null) httpsServer.stop(0);
    }
}
