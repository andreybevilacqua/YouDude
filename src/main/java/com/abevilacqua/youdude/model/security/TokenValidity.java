package com.abevilacqua.youdude.model.security;

import lombok.Value;

@Value
public class TokenValidity {

  boolean active;
  long expirationTime;
  String username, scope;
}
