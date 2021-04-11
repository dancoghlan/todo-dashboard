package com.dancoghlan;

import com.dancoghlan.model.Todo;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Ignore // Running against live service
public class TestClass {

    @Test
    public void test() {
        WebClient webClient = getWebClient();

        List<Todo> todos = webClient
                .get()
                .uri("/get/{userName}", "in28Minutes")
                .headers(headers -> headers.setBearerAuth("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkFQWFR2RHpxRS1tUEYwRFZwempOVyJ9.eyJpc3MiOiJodHRwczovL2Rldi03dmtqNTMtMS5ldS5hdXRoMC5jb20vIiwic3ViIjoidElwMW5nVWNMdnltUFMzY2Z4WXRWRjVQb2d6aTZHTDBAY2xpZW50cyIsImF1ZCI6Imh0dHBzOi8vdG9kby1hcGkuZXhhbXBsZS5jb20iLCJpYXQiOjE2MTc4OTY4MTIsImV4cCI6MTYxNzk4MzIxMiwiYXpwIjoidElwMW5nVWNMdnltUFMzY2Z4WXRWRjVQb2d6aTZHTDAiLCJndHkiOiJjbGllbnQtY3JlZGVudGlhbHMifQ.YyVCFoJTFEpMyGGfcfO1uXfRsZTbzG8dS3MNzn51_-UPmTxrtK9gaeCdFNMzczuYqR2UpvgOMudvw5beGucsRzYDVnG3KcA09UMsyN-D0kpek-EzGngDA86Yuw18EqP-aUJjFnQ6Wp4KBglxbI8mNqA0-f6uxzl-dl4HUp8EQlfWFRhrxuxCE_Hy1FzybGlMcex34Rjs-3Xi2AKaXwoF_7cDTpSAw3GLwjqvijt7QOdEcoOetjI8StrKHLqEqyz0_oYM2pNjh4jEB0tvw9j50AP1Bc82Wkm6_t8AfPWsks-DDkysUeFq-KbSWbqmP3iNQv5cEusTnmr2U3Ec9Rqs1g"))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new RuntimeException()))
                .bodyToFlux(Todo.class)
                .collectList()
                .block();

        System.out.println("\n\n" + todos + "\n\n");
    }

    private WebClient getWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl("http://localhost:8082")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
