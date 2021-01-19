package reactivefeign.webclient.spring;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("reactivefeign.httpclient")
public class ReactiveFeignHttpClientProperties {
    private boolean useClientSsl = true;
}
