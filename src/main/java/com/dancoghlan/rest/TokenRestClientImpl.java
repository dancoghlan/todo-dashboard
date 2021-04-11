package com.dancoghlan.rest;

import com.dancoghlan.model.OAuthRequest;
import com.dancoghlan.model.OAuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TokenRestClientImpl implements TokenRestClient {
    private static final Logger logger = LoggerFactory.getLogger(TodoRestClientImpl.class);
    private final WebClient webClient;
    private final OAuthRequest oAuthRequest;

    @Autowired
    public TokenRestClientImpl(WebClient tokenWebClient, OAuthRequest oAuthRequest) {
        this.webClient = tokenWebClient;
        this.oAuthRequest = oAuthRequest;
    }

    @Override
    public Mono<OAuthResponse> getBearerToken() {
        Mono<OAuthResponse> bearerToken = webClient
                .post()
                .uri("/oauth/token")
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_JSON))
                .body(BodyInserters.fromValue(oAuthRequest))
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new RuntimeException()))
                .onStatus(HttpStatus::is5xxServerError, serverResponse -> Mono.error(new RuntimeException()))
                .bodyToMono(OAuthResponse.class);
        return bearerToken;
    }

}
