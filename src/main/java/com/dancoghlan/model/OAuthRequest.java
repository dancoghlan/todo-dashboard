package com.dancoghlan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
@EqualsAndHashCode
public class OAuthRequest {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("audience")
    private String audience;

    @JsonProperty("grant_type")
    private String grantType;

}
