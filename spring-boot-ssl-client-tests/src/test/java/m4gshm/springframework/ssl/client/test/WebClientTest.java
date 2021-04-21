package m4gshm.springframework.ssl.client.test;

import lombok.val;
import m4gshm.springframework.ssl.client.test.config.ClientSslPropertiesTestConfig;
import m4gshm.springframework.ssl.client.netty.handler.NettySslContextBuilderFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
        ).get().uri(BaseHttpsTest.getServerUrl()).response((httpClientResponse, byteBufFlux) ->
                byteBufFlux.asString()).blockFirst();
        Assert.assertEquals(HttpsServerUtility.ROOT_RESPONSE, response);

    }

}
