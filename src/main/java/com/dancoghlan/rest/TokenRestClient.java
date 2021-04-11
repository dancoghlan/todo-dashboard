package com.dancoghlan.rest;

import com.dancoghlan.model.OAuthResponse;
import reactor.core.publisher.Mono;

public interface TokenRestClient {

    Mono<OAuthResponse> getBearerToken();
}
