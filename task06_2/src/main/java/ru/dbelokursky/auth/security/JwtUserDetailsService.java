package ru.dbelokursky.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.repository.UserRepository;
import ru.dbelokursky.auth.service.UserService;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.findByLogin(username);

    if (user == null) {
      throw new UsernameNotFoundException(String.format("User with name %s not found", username));
    }

    return JwtUserFactory.create(user, userService.getUserRoles(user));
  }
}
