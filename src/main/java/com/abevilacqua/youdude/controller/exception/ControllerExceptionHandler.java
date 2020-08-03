package com.abevilacqua.youdude.controller.exception;

import com.abevilacqua.youdude.exception.InvalidTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Component
@Slf4j
public class ControllerExceptionHandler {

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handle(InvalidTokenException exception) {
    log.error("Invalid token", exception);
    return new ErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage());
  }

  @ExceptionHandler
  @ResponseBody
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ErrorResponse handle(IllegalArgumentException exception) {
    log.error("Illegal request", exception);
    return new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
  }
}
