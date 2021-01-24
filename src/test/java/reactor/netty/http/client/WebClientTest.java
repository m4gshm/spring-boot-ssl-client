package reactor.netty.http.client;

import io.net.BaseHttpsTest;
import org.springframework.config.ClientSslPropertiesTestConfig;
import io.netty.handler.ssl.NettySslContextBuilderFactory;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static io.net.HttpsServerUtility.ROOT_RESPONSE;
import static org.junit.Assert.assertEquals;
import static reactor.netty.http.client.HttpClient.create;

@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = ClientSslPropertiesTestConfig.class)
public class WebClientTest extends BaseHttpsTest {

    @Autowired
    NettySslContextBuilderFactory sslContextBuilderFactory;

    @Test
    public void testWebClient() {

        val response = create().secure(sslContextSpec ->
                sslContextSpec.sslContext(sslContextBuilderFactory.newSslContextBuilder())
        ).get().uri(getServerUrl()).response((httpClientResponse, byteBufFlux) ->
                byteBufFlux.asString()).blockFirst();
        assertEquals(ROOT_RESPONSE, response);

    }

}
