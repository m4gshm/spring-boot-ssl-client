package org.springframework.web.client;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.springframework.web.client.HttpsServerUtility.ROOT_RESPONSE;
import static org.springframework.web.client.HttpsServerUtility.newHttpsServer;


@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = ClientSslPropertiesTestConfig.class)
public class RestTemplateTest {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Test
    public void testRestTemplate() {
        val httpServer = newHttpsServer();
        val address = httpServer.getAddress();
        val hostName = address.getHostName();
        val port = address.getPort();

        val restTemplate = restTemplateFactory.newRestTemplate();

        val entity = restTemplate.getForEntity("https://" + hostName + ":" + port, String.class);
        val body = entity.getBody();
        assertEquals(ROOT_RESPONSE, body);

        httpServer.stop(0);
    }


}
