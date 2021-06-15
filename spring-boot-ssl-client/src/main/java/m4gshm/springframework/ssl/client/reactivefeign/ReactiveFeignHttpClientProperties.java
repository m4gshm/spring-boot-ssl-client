package m4gshm.springframework.ssl.client.reactivefeign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(ReactiveFeignHttpClientProperties.REACTIVEFEIGN_HTTPCLIENT)
public class ReactiveFeignHttpClientProperties {
    public static final String REACTIVEFEIGN_HTTPCLIENT = "reactivefeign.httpclient";
    public static final String REACTIVEFEIGN_HTTPCLIENT_USE_CLIENT_SSL = REACTIVEFEIGN_HTTPCLIENT + ".use-client-ssl";
    private boolean useClientSsl = true;
}
