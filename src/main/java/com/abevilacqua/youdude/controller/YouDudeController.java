package com.abevilacqua.youdude.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class YouDudeController {

  @GetMapping
  public ResponseEntity<String> index() {
    return new ResponseEntity<>("Hello there from YouDude!", HttpStatus.OK);
  }
}
