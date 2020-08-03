package com.abevilacqua.yoududeauth.model;

public class TokenValidity {

  private final boolean active;
  private final long expirationTime;
  private final String username, scope;

  public static TokenValidity invalidToken() {
    return new TokenValidity(false);
  }

  public static TokenValidity validToken(long expirationTime,
                                         String username,
                                         String scope) {
    return new TokenValidity(true, expirationTime, username, scope);
  }

  private TokenValidity(boolean active) {
    this.active = active;
    this.expirationTime = -1;
    this.username = null;
    this.scope = null;
  }

  private TokenValidity(boolean active, long expirationTime, String username, String scope) {
    this.active = active;
    this.expirationTime = expirationTime;
    this.username = username;
    this.scope = scope;
  }

  @SuppressWarnings("unused")
  public boolean isActive() {
    return active;
  }

  @SuppressWarnings("unused")
  public long getExpirationTime() {
    return expirationTime;
  }

  @SuppressWarnings("unused")
  public String getUsername() {
    return username;
  }

  @SuppressWarnings("unused")
  public String getScope() {
    return scope;
  }
}
