package com.abevilacqua.youdude.service.security;

import com.abevilacqua.youdude.exception.InvalidTokenException;
import com.abevilacqua.youdude.model.security.TokenValidity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.POST;

@Service
@Slf4j
public class SecurityService {

  @Value("${oauth2.endpoint}")
  private String authUrl;

  private final RestTemplate restTemplate;

  public SecurityService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public void processClientRequest(String token) {
    if(token != null && !token.isBlank()) {
      ResponseEntity<TokenValidity> responseEntity = requestTokenValidation(token);
      if(!responseEntity.getStatusCode().is2xxSuccessful()) throw new RuntimeException("An error happened validating token");
    } else throw new IllegalArgumentException("Illegal request: missing token");
  }

  private ResponseEntity<TokenValidity> requestTokenValidation(String token) {
    HttpEntity<TokenValidity> httpEntity = getHttpEntity(token);
    try {
      return restTemplate.exchange(authUrl, POST, httpEntity, TokenValidity.class);
    } catch (HttpClientErrorException e) {
      if(e.getStatusCode().value() == 403) {
        log.error("Invalid token for this HTTP request");
        throw new InvalidTokenException("Invalid token");
      } else throw new RuntimeException("HTTP Error while validating token response");
    }
  }

  private HttpEntity<TokenValidity> getHttpEntity(String token) {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.add("token", token);
    return new HttpEntity<>(headers);
  }
}
