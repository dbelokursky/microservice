package ru.dbelokursky.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController implements ru.dbelokursky.rest.api.ErrorApi {

  @Override
  public ResponseEntity<Void> errorGet() {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
