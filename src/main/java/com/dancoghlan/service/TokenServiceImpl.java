package com.dancoghlan.service;

import com.dancoghlan.model.OAuthResponse;
import com.dancoghlan.rest.TokenRestClient;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class TokenServiceImpl implements TokenService {
    private static final Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
    private final TokenRestClient tokenRestClient;

    @Autowired
    public TokenServiceImpl(TokenRestClient tokenRestClient) {
        this.tokenRestClient = tokenRestClient;
    }

    @Override
    @Cacheable(value = "token")
    public String getBearerToken() {
        Mono<OAuthResponse> mono = tokenRestClient.getBearerToken();
        String token = mono
                .map(oAuthResponse -> oAuthResponse.getAccessToken())
                .block();
        logger.info("Retrieved bearer token [{}]", token);
        return token;
    }

}
