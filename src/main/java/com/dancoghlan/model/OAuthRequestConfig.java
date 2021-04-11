package com.dancoghlan.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("oauth")
public class OAuthRequestConfig extends OAuthRequest {


}
