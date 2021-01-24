package org.springframework.web.client;

import io.net.BaseHttpsTest;
import org.springframework.config.ClientSslPropertiesTestConfig;
import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static io.net.HttpsServerUtility.ROOT_RESPONSE;


@RunWith(SpringJUnit4ClassRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = ClientSslPropertiesTestConfig.class)
public class RestTemplateTest extends BaseHttpsTest {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Test
    public void testRestTemplate() {

        val body = restTemplateFactory.newRestTemplate().getForEntity(getServerUrl(), String.class).getBody();
        assertEquals(ROOT_RESPONSE, body);

    }


}
