package com.dancoghlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OAuthRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("audience")
    private String audience;

    @JsonProperty("grant_type")
    private String grantType;

    public OAuthRequest() {

    }

    public OAuthRequest(String clientId, String clientSecret, String audience, String grantType) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.audience = audience;
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

}
