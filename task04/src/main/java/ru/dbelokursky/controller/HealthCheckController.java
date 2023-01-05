package ru.dbelokursky.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

  @GetMapping("/health")
  ru.dbelokursky.rest.model.ResponseStatus getStatus() {
    return new ru.dbelokursky.rest.model.ResponseStatus().status("OK");
  }
}
