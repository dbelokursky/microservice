package ru.dbelokursky.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.dbelokursky.auth.dto.AuthenticationRequestDto;
import ru.dbelokursky.auth.dto.AuthenticationResponseDto;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.security.JwtTokenProvider;
import ru.dbelokursky.auth.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthControllerV1 {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  @PostMapping(value = "/login")
  public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto request) {
    try {
      String login = request.login();
      String password = request.password();
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
      User user = userService.findByLogin(login);
      String token = jwtTokenProvider.createToken(login, userService.getUserRoles(user));

      return ResponseEntity.ok(new AuthenticationResponseDto(login, token));
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }

  @GetMapping(value = "/test")
  public String test() {
    return "WORKS " + UUID.randomUUID();
  }
}
