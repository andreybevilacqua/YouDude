package com.abevilacqua.yoududeauth.config.controller;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class  IntrospectEndpoint {

  private final TokenStore tokenStore;

  public IntrospectEndpoint(TokenStore tokenStore) {
    this.tokenStore = tokenStore;
  }

  @PostMapping("/oauth/introspect")
  public Map<String, Object>  introspect(@RequestParam("token") String token) {
    OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
    if(accessToken == null || accessToken.isExpired()) return Map.of("active", false);

    OAuth2Authentication authentication = this.tokenStore.readAuthentication(token);

    return Map
        .of("active", true,
            "exp", accessToken.getExpiration().getTime(),
            "scope", String.join(" ", accessToken.getScope()),
            "sub", authentication.getName());
  }

}
