package ru.dbelokursky.microservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.microservice.dtos.ResponseStatusDto;

@RestController
public class HealthCheckController {

  @GetMapping("/health")
  ResponseStatusDto getStatus() {
    return new ResponseStatusDto("OK");
  }
}
