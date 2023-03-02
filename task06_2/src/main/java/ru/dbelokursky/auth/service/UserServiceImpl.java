package ru.dbelokursky.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dbelokursky.auth.entity.Role;
import ru.dbelokursky.auth.entity.RoleRef;
import ru.dbelokursky.auth.entity.User;
import ru.dbelokursky.auth.repository.RoleRepository;
import ru.dbelokursky.auth.repository.UserRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final BCryptPasswordEncoder passwordEncoder;

  private final RoleRepository roleRepository;

  @Override
  public void create(User user) {
    Role defaultRole = roleRepository.findByName("ROLE_USER");
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    user.setRoleIds(Set.of(new RoleRef(defaultRole.getId())));
    user.setStatus(1);
    userRepository.save(user);
  }

  @Override
  public User findByLogin(String login) {
    return userRepository.findByLogin(login);
  }

  @Override
  public User findById(long id) {
    return userRepository.findById(id).orElseThrow();
  }

  @Override
  public List<Role> getUserRoles(User user) {
    List<Long> roleIds = user.getRoleIds().stream().map(RoleRef::getRoleId).toList();
    return roleRepository.findAllByIdIn(roleIds);
  }
}
