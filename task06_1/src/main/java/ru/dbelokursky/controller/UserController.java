package ru.dbelokursky.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dbelokursky.rest.model.User;
import ru.dbelokursky.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
public class UserController implements ru.dbelokursky.rest.api.UserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<Void> userIdDelete(Long id) {
    userService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<User> userIdGet(Long id) {
    return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> userPost(User user) {
    userService.create(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> userPut(User user) {
    userService.update(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/profile")
  public ResponseEntity<User> getProfile(HttpServletRequest request) {
    String username = request.getHeader("App-Username");
    User user = userService.findByUsername(username);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }
}
