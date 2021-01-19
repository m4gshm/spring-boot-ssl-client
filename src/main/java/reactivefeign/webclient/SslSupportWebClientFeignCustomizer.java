package reactivefeign.webclient;

import io.netty.handler.ssl.SslContextBuilder;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.function.Consumer;

public class SslSupportWebClientFeignCustomizer implements WebClientFeignCustomizer {
    final Consumer<WebClient.Builder> builderConsumer;

    public SslSupportWebClientFeignCustomizer(SslContextBuilder contextBuilder) {
        builderConsumer = contextBuilder != null
                ? b -> b.clientConnector(new ReactorClientHttpConnector(getHttpClient(getConnector(b))
                .secure(sslContextSpec -> sslContextSpec.sslContext(contextBuilder))))
                : b -> {
        };
    }

    @Override
    public void accept(WebClient.Builder builder) {
        builder.apply(builderConsumer);
    }

    @SneakyThrows
    private ReactorClientHttpConnector getConnector(WebClient.Builder builder) {
        val field = builder.getClass().getDeclaredField("connector");
        field.setAccessible(true);
        return (ReactorClientHttpConnector) field.get(builder);
    }

    @SneakyThrows
    private HttpClient getHttpClient(ReactorClientHttpConnector reactorClientHttpConnector) {
        val field = reactorClientHttpConnector.getClass().getDeclaredField("httpClient");
        field.setAccessible(true);
        return (HttpClient) field.get(reactorClientHttpConnector);
    }
}
