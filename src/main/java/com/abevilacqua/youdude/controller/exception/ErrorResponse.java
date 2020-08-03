package com.abevilacqua.youdude.controller.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

public class ErrorResponse {

  @JsonProperty("code")
  @SuppressWarnings("unused")
  private final int code;

  @JsonProperty("reason")
  @SuppressWarnings("unused")
  private final String reason;

  public ErrorResponse(final HttpStatus status, final String reason) {
    this.code = status.value();
    this.reason = reason;
  }
}
