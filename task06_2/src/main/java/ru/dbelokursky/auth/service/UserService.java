package ru.dbelokursky.auth.service;

import ru.dbelokursky.auth.entity.Role;
import ru.dbelokursky.auth.entity.User;

import java.util.List;

public interface UserService {

  void create(User user);
  User findByLogin(String login);

  User findById(long id);

  List<Role> getUserRoles(User user);
}
