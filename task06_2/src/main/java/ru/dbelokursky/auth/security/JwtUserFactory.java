package ru.dbelokursky.auth.security;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.dbelokursky.auth.entity.Role;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.service.UserService;

import java.util.List;

import static ru.dbelokursky.auth.security.StatusEnum.ACTIVE;

@RequiredArgsConstructor
public final class JwtUserFactory {

  private final UserService userService;

  public static JwtUser create(User user, List<Role> roles) {
    return new JwtUser(
        user.getId(),
        user.getLogin(),
        user.getPassword(),
        user.getStatus() == ACTIVE.code,
        user.getUpdated(),
        mapToGrantedAuthority(roles)
    );
  }

  private static List<? extends GrantedAuthority> mapToGrantedAuthority(List<Role> roles) {
    return roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName()))
        .toList();
  }
}
