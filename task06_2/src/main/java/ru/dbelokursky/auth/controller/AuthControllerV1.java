package ru.dbelokursky.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.dbelokursky.auth.dto.AuthenticationRequestDto;
import ru.dbelokursky.auth.dto.UserRegisterRequest;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.security.JwtTokenProvider;
import ru.dbelokursky.auth.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  @PostMapping(value = "/login")
  public ResponseEntity<Void> login(@RequestBody AuthenticationRequestDto request) {
    try {
      String login = request.login();
      String password = request.password();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
      User user = userService.findByLogin(login);
      String token = jwtTokenProvider.createToken(login, userService.getUserRoles(user));

      HttpHeaders headers = new HttpHeaders();
      headers.add("Authorization", token);
      headers.add("App-Username", user.getLogin());

      return new ResponseEntity<>(headers, HttpStatus.OK);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  @GetMapping(value = "/validate")
  public ResponseEntity<Void> validate() {
    return new ResponseEntity<>(HttpStatus.OK);
  }


  @PostMapping("/register")
  public ResponseEntity<Void> register(@RequestBody UserRegisterRequest request) {
    userService.register(request);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
