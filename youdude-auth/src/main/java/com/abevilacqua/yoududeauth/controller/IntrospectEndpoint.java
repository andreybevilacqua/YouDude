package com.abevilacqua.yoududeauth.controller;

import com.abevilacqua.yoududeauth.model.TokenValidity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.abevilacqua.yoududeauth.model.TokenValidity.invalidToken;
import static com.abevilacqua.yoududeauth.model.TokenValidity.validToken;

@RestController
public class  IntrospectEndpoint {

  private final TokenStore tokenStore;

  public IntrospectEndpoint(TokenStore tokenStore) {
    this.tokenStore = tokenStore;
  }

  @PostMapping("/oauth/introspect")
  public ResponseEntity<TokenValidity> introspect(@RequestParam("token") String token) {
    OAuth2AccessToken accessToken = this.tokenStore.readAccessToken(token);
    if(accessToken == null || accessToken.isExpired()) return new ResponseEntity<>(invalidToken(), HttpStatus.FORBIDDEN);

    OAuth2Authentication authentication = this.tokenStore.readAuthentication(token);

    return new ResponseEntity<>(validToken(
        accessToken.getExpiration().getTime(),
        authentication.getName(),
        String.join(" ", accessToken.getScope())
    ), HttpStatus.OK);
  }

}
