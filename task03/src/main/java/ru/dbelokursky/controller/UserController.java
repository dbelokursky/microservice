package ru.dbelokursky.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.rest.model.User;

@RestController
public class UserController implements ru.dbelokursky.rest.api.UserApi {
  @Override
  public ResponseEntity<Void> userIdDelete(String id) {
    return null;
  }

  @Override
  public ResponseEntity<User> userIdGet(String id) {
    return null;
  }

  @Override
  public ResponseEntity<Void> userIdPut(String id, User user) {
    return null;
  }

  @Override
  public ResponseEntity<User> userPost(User user) {
    return null;
  }
}
