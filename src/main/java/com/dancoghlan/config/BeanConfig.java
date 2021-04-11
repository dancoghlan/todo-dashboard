package com.dancoghlan.config;

import com.dancoghlan.model.OAuthRequest;
import com.dancoghlan.model.OAuthRequestConfig;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {

    @Autowired
    private OAuthRequestConfig oAuthRequestConfig;

    @Value("${token.url}")
    private String tokenUrl;

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public OAuthRequest oAuthRequest() {
        return new OAuthRequest(
                oAuthRequestConfig.getClientId(),
                oAuthRequestConfig.getClientSecret(),
                oAuthRequestConfig.getAudience(),
                oAuthRequestConfig.getGrantType());
    }

    @Bean
    @Qualifier("todoWebClient")
    public WebClient todoWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(gatewayUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    @Qualifier("tokenWebClient")
    public WebClient tokenWebClient() {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .responseTimeout(Duration.ofMillis(5000))
                .doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                        .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        return WebClient.builder()
                .baseUrl(tokenUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

}
