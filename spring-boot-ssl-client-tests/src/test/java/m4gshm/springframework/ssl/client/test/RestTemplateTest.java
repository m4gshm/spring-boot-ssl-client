package m4gshm.springframework.ssl.client.test;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import m4gshm.springframework.ssl.client.test.config.ClientSslPropertiesTestConfig;
import m4gshm.springframework.ssl.client.web.client.RestTemplateFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static m4gshm.springframework.ssl.client.test.HttpsServerUtility.ROOT_RESPONSE;
import static org.junit.Assert.assertEquals;


//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
@ContextConfiguration(classes = {ClientSslPropertiesTestConfig.class})
@Slf4j
public class RestTemplateTest extends BaseHttpsTest {

    @Autowired
    RestTemplateFactory restTemplateFactory;

    @Test
    public void testRestTemplate() {

        val body = restTemplateFactory.newRestTemplate().getForEntity(getServerUrl(), String.class).getBody();
        assertEquals(ROOT_RESPONSE, body);

    }

}
