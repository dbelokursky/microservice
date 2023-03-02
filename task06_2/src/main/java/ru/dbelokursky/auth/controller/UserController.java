package ru.dbelokursky.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.service.UserService;
import ru.dbelokursky.auth.service.UserServiceImpl;

import java.util.UUID;

@RestController("/v1/")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("test")
  void test() {
    User user = new User()
        .setLogin(UUID.randomUUID().toString())
        .setPassword(UUID.randomUUID().toString())
        .setStatus(1);
    userService.create(user);
  }


}
